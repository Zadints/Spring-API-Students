package org.zadints.zadintsapi.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(
            @Value("${spring.datasource.url}") String url) {

        HikariDataSource ds = new HikariDataSource();

        ds.setJdbcUrl(url);
        ds.setDriverClassName("org.sqlite.JDBC");
        ds.setConnectionInitSql("PRAGMA foreign_keys=ON");

        return ds;
    }
}
