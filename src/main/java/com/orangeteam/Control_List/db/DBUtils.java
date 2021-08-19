package com.orangeteam.Control_List.db;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class DBUtils {
    private final static String POSTGRES_USER = "controlList";
    private final static String POSTGRES_PASSWORD = "pass123";

    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/controlList";

    public static Optional<Connection> connect() {
        Optional<Connection> optionalConnection = Optional.empty();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, POSTGRES_USER, POSTGRES_PASSWORD);
            connection.setAutoCommit(true);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            optionalConnection = Optional.of(connection);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return optionalConnection;
    }

    public static void disconnect(@NotNull Connection connection) {
        try {
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
