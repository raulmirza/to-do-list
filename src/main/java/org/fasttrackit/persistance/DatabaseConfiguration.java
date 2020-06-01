package org.fasttrackit.persistance;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfiguration {

    public static Connection getConnection() throws SQLException, IOException {
        String environment = System.getProperty("environment");

        InputStream inputStream = DatabaseConfiguration.class.getClassLoader()
                .getResourceAsStream("db.properties");

        if (inputStream == null) {
            throw new RuntimeException("Failed to read db config file.");
        }
        try {

            Properties properties = new Properties();
            properties.load(inputStream);


            return DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));
        } finally {
            inputStream.close();

        }
    }
}