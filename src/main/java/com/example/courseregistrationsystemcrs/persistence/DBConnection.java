package com.example.courseregistrationsystemcrs.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {

    private static final String PROPERTIES_FILE = "db.properties";

    private static Properties properties;

    private static Properties loadProperties() {
        if (properties == null) {
            try (InputStream input = DBConnection.class
                    .getClassLoader()
                    .getResourceAsStream(PROPERTIES_FILE)) {

                if (input == null) {
                    throw new RuntimeException("db.properties file not found");
                }

                properties = new Properties();
                properties.load(input);

                Class.forName(properties.getProperty("db.driver"));

            } catch (Exception e) {
                throw new RuntimeException("Failed to load DB configuration", e);
            }
        }
        return properties;
    }

    public static Connection getConnection() throws SQLException {
        Properties props = loadProperties();

        return DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.username"),
                props.getProperty("db.password")
        );
    }
}
