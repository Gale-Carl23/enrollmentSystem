package com.school.enrollment.config;

import org.flywaydb.core.Flyway;

public final class TestDatabaseMigration {

    private TestDatabaseMigration() {
    }

    public static void migrate() {

        Flyway.configure()
                .dataSource(
                        AppConfig.get("test.db.url"),
                        AppConfig.get("test.db.username"),
                        AppConfig.get("test.db.password")
                )
                .locations("filesystem:database/migrations")
                .load()
                .migrate();
    }
}