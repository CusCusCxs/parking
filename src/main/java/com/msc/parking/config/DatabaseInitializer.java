package com.msc.parking.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class DatabaseInitializer {

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public CommandLineRunner initializeDatabase(DriverManagerDataSource dataSource) {
        return args -> {
            try {
                Resource initSchema = new ClassPathResource("db/create-db.sql");
                DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema);
                DatabasePopulatorUtils.execute(databasePopulator, dataSource);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}