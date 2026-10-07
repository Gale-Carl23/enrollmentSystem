package com.school.enrollment.config;

import org.flywaydb.core.Flyway;

public final class FlywayConfig {

    private FlywayConfig() {
    }

    public static Flyway create() {
        return Flyway.configure()
                .dataSource(
                        DatabaseConfig.getUrl(),
                        DatabaseConfig.getUsername(),
                        DatabaseConfig.getPassword()
                )
                .locations("filesystem:database/migrations")
                .load();
    }
}