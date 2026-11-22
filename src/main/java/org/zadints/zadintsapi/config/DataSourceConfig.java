package org.zadints.zadintsapi.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {

        HikariDataSource ds = new HikariDataSource();

        ds.setJdbcUrl(
                "jdbc:sqlite:database/Servinet.db;"
                        + "databaseName=Servinet;"
                        + "encrypt=true;"
                        + "trustServerCertificate=true"
        );
        //ds.setUsername("root");
        //ds.setPassword("password");
        return ds;
    }
}
