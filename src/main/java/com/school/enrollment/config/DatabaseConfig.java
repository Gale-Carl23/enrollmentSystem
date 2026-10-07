package com.school.enrollment.config;

public final class DatabaseConfig {

    private DatabaseConfig() {
    }

    public static String getUrl() {
        return AppConfig.get("db.url");
    }

    public static String getUsername() {
        return AppConfig.get("db.username");
    }

    public static String getPassword() {
        return AppConfig.get("db.password");
    }
}