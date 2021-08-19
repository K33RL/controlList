package com.orangeteam.Control_List.dao;

import com.orangeteam.Control_List.exception.EmptyIdException;
import com.orangeteam.Control_List.exception.UserUniqueViolationException;
import com.orangeteam.Control_List.model.User;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    private static final String TABLE_NAME = "users";

    private Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /* ================================
     UserDAO implementation
    ================================ */

    @Override
    public List<User> getAll() {
        final String query = "SELECT * FROM " + TABLE_NAME + ";";
        List<User> userList = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()){
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                User nextUser = makeQueriedUser(rs);
                userList.add(nextUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public Optional<User> getById(int userId) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?;";
        User user = null;
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setLong(1, userId);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = makeQueriedUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    // https://www.postgresql.org/docs/current/errcodes-appendix.html
    // Class 23 — Integrity Constraint Violation
    // 23505	unique_violation
    private final static int PSQL_UNIQUE_VIOLATION = 23505;

    @Override
    public Optional<User> add(@NotNull User user) throws UserUniqueViolationException {
        final String query = "INSERT INTO " + TABLE_NAME + "(name, surname) VALUES (?, ?);";
        User addedUser = null;
        try (PreparedStatement statement = this.connection.prepareStatement(query,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurName());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted == 1) {
                ResultSet idsRs = statement.getGeneratedKeys();
                if (idsRs.next()) {
                    int insertedId = idsRs.getInt(1);
                    addedUser = new User(insertedId, user.getName(), user.getSurName());
                }
            }
        } catch (SQLException e) {
            if (PSQL_UNIQUE_VIOLATION == Integer.parseInt(e.getSQLState())) {
                throw new UserUniqueViolationException();
            }
            e.printStackTrace();
        }
        return Optional.ofNullable(addedUser);
    }

    @Override
    public int update(@NotNull User user) throws EmptyIdException, UserUniqueViolationException {
        final String query = "UPDATE " + TABLE_NAME + " SET name = ? , surname = ? WHERE id = ?";
        int updatedRowsCount = 0;
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurName());
            if (user.getId() == 0) {
                throw new EmptyIdException();
            }
            statement.setLong(4, user.getId());

            updatedRowsCount = statement.executeUpdate();
        } catch (SQLException e) {
            if (PSQL_UNIQUE_VIOLATION ==  Integer.parseInt(e.getSQLState())) {
                throw new UserUniqueViolationException();
            }
            e.printStackTrace();
        }
        return updatedRowsCount;
    }

    @Override
    public int remove(int userId) throws EmptyIdException {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE id = ?;";
        int deletedRowsCount = 0;
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setLong(1, userId);

            deletedRowsCount = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deletedRowsCount;
    }

    /* ================================
     static methods
    ================================ */

    private static User makeQueriedUser(@NotNull ResultSet rs) throws SQLException {
        int userId = rs.getInt("id");
        String userName = rs.getString("name");
        String userSurname = rs.getString("surname");
        return new User(userId, userName, userSurname);
    }
}
