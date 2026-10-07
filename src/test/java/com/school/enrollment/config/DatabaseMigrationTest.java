package com.school.enrollment.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DatabaseMigrationTest {

    @Test
    void shouldRunDatabaseMigrations() {

        assertDoesNotThrow(DatabaseMigration::migrate);
    }
}