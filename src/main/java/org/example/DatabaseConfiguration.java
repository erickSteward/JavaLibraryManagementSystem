package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfiguration.class);
    private static HikariDataSource dataSource;

    static {
        try{
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:postgresql://localhost:4747/library_db");
            config.setUsername("postgres");
            config.setPassword("1234");
            config.setMaximumPoolSize(10);

            dataSource = new HikariDataSource(config);
            logger.info("Database connection pool initialized successfully.");
        } catch (Exception e){
            logger.error("Failed to initialize database connection pool");
            throw new RuntimeException("Failed to initialize database connection pool", e);
        }
    }

    public static Connection getConnection () throws SQLException {
        return dataSource.getConnection();
    }


}
