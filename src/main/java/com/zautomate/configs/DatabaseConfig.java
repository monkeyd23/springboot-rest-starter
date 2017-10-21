package com.zautomate.configs;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


@Configuration
public class DatabaseConfig {

    @SuppressWarnings("unchecked")
    private <T> T createDataSource(DataSourceProperties properties, Class<? extends DataSource> type) {
        return (T) properties.initializeDataSourceBuilder().type(type).build();
    }

    /**
     *
     * @param properties Autowired from application.yml
     * @return HikariDataSource Object
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariDataSource dataSource(DataSourceProperties properties) {
        return createDataSource(properties, HikariDataSource.class);
    }
}
