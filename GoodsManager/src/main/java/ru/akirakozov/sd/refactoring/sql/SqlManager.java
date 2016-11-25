package ru.akirakozov.sd.refactoring.sql;

import java.sql.*;
import java.util.function.BiFunction;

/**
 * Created by dmitry on 28.10.16.
 */
public class SqlManager {
    private Connection connection;
    private Statement statement;

    private <T> T applySql(String sqlCode, BiFunction<Statement, String, T> executable) {
        String sqlAddress = "jdbc:sqlite:test.db";
        try {
            connection = DriverManager.getConnection(sqlAddress);
            statement = connection.createStatement();
            return executable.apply(statement, sqlCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void finish() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeUpdate(String sqlCode) {
        applySql(sqlCode, (statement, code) -> {
            try {
                return statement.executeUpdate(code);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    ResultSet executeQuery(String sqlCode) {
        return applySql(sqlCode, (statement, code) -> {
            try {
                return statement.executeQuery(code);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
