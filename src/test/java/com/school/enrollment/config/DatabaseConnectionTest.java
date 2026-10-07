package com.school.enrollment.config;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DatabaseConnectionTest {

    @Test
    void shouldConnectToDatabase() throws Exception {

        try (Connection connection =
                     DatabaseConnection.getConnection()) {

            assertNotNull(connection);
        }
    }
}