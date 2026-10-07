package com.school.enrollment.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class TestDatabaseConnection {

    private TestDatabaseConnection() {
    }

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                AppConfig.get("test.db.url"),
                AppConfig.get("test.db.username"),
                AppConfig.get("test.db.password")
        );
    }
}