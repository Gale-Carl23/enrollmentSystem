package com.school.enrollment.config;

import org.flywaydb.core.Flyway;

public final class DatabaseMigration {

    private DatabaseMigration() {
    }

    public static void migrate() {

        Flyway flyway = FlywayConfig.create();

        flyway.migrate();
    }
}