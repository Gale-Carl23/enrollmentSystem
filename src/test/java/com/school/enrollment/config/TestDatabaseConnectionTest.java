package com.school.enrollment.config;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestDatabaseConnectionTest {

    @Test
    void shouldConnectToTestDatabase() throws Exception {

        try (Connection connection =
                     TestDatabaseConnection.getConnection()) {

            assertNotNull(connection);
        }
    }
}