package com.proyecto.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HikariCpConfig {

    @Value("${DB_FRALUSE_URL}")
    private String dbFraluseUrl;

    @Value("${DB_FRALUSE_USER}")
    private String dbFraluseUser;

    @Value("${DB_FRALUSE_PASS}")
    private String dbFralusePass;

    @Value("${DB_FRALUSE_DRIVER}")
    private String dbFraluseDriver;

    @Bean
    public HikariDataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(dbFraluseUrl);
        config.setUsername(dbFraluseUser);
        config.setPassword(dbFralusePass);
        config.setDriverClassName(dbFraluseDriver);

        config.setMaximumPoolSize(20);   // Número máximo de conexiones
        config.setMinimumIdle(5);         // Número mínimo de conexiones inactivas
        config.setIdleTimeout(300000);    // Tiempo máximo para eliminar conexiones inactivas (5 minutos)
        config.setConnectionTimeout(30000); // Tiempo máximo para conectarse a la base de datos (30 segundos)

        System.out.println("###### HikariCP initialized ######");
        return new HikariDataSource(config);
    }
}