package com.school.enrollment.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TestDatabaseMigrationTest {

    @Test
    void shouldRunTestDatabaseMigrations() {

        assertDoesNotThrow(
                TestDatabaseMigration::migrate
        );
    }
}