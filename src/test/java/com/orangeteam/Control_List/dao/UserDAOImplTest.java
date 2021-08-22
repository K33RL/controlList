package com.orangeteam.Control_List.dao;

import com.orangeteam.Control_List.db.DBUtils;
import com.orangeteam.Control_List.model.User;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserDAOImplTest {

    private static final String SQL_DDL_INIT = "CREATE TABLE users (" +
            "id serial primary key," +
            "name varchar(100) not null," +
            "surname varchar(100) not null," +
            "constraint u_constraint unique (name, surname)" +
            ");";
//            ");" +
//            "CREATE TABLE activities (" +
//            "   id serial primary key," +
//            "   user_id integer references users not null," +
//            "   duration_min integer CHECK (duration_min > 0)," +
//            "   description varchar(256) NOT NULL CHECK (description <> '')" +

    private static final String SQL_DDL_RESET = "DROP TABLE users;";

    private static final String SQL_POPULATE_USERS = "" +
            "INSERT INTO users(name, surname) VALUES ('Andrew', 'Nemov');" +
            "INSERT INTO users(name, surname) VALUES ('Test', 'User');";

    private static final List<User> POPULATED_USERS = Arrays.asList(
            new User(1, "Andrew", "Nemov"),
            new User(1, "Test", "User")
    );

    private static Connection dbConnection;
    private static UserDAO userDAO;

    @BeforeAll
    public static void setDao() throws SQLException {
        Optional<Connection> optionalConnection = DBUtils.connect();
        if (optionalConnection.isPresent()) {
            dbConnection = optionalConnection.get();
        } else {
            throw new SQLException("Unable connect to db!");
        }
        userDAO = new UserDAOImpl(dbConnection);
    }

    @BeforeEach
    public void initAndPopulateDb() throws SQLException {
        Statement statementCreate = dbConnection.createStatement();
        int res = statementCreate.executeUpdate(SQL_DDL_INIT);
        System.out.println(res);
        statementCreate.close();
//        if (res ) {
//            throw new SQLException("Unable to create tables!");
//        }
        Statement statementInsert = dbConnection.createStatement();
        int insertedUsersRows = statementInsert.executeUpdate(SQL_POPULATE_USERS);
        if (insertedUsersRows != 2) {
            throw new SQLException("Problems occurred while inserting in 'users' table!");
        }
    }

    @AfterEach
    public void resetDb() throws SQLException {
        Statement statement = dbConnection.createStatement();
        boolean res = statement.execute(SQL_DDL_RESET);
        if (!res) {
            throw new SQLException("Unable to drop tables!");
        }
    }

    @Test
    public void getAll_Should_Return_2_Users() {
        List<User> userList = userDAO.getAll();
        Assertions.assertEquals(userList, POPULATED_USERS);
    }

//    @Test
//    public void getAll_Should_Return_2_Users() {
//        List<User> userList = userDAO.getAll();
//        Assertions.assertEquals(userList, POPULATED_USERS);
//    }


}
