package com.school.enrollment.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class AppConfig {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = AppConfig.class
                .getResourceAsStream("/config/application.properties")) {

            if (input == null) {
                throw new IllegalStateException(
                        "application.properties not found"
                );
            }

            PROPERTIES.load(input);

        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private AppConfig() {
    }

    public static String get(String key) {
        String value = PROPERTIES.getProperty(key);

        if (value == null) {
            throw new IllegalArgumentException(
                    "Missing application property: " + key
            );
        }

        return value;
    }
}