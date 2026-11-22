package org.zadints.zadintsapi.service;

import org.springframework.stereotype.Service;
import org.zadints.zadintsapi.config.DataSourceConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class Portafolio {

    private final DataSource dataSource;

    public Portafolio(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean reloadRepositories(){
        
    }


    public String[] getProjects(){
        String sql = "SELECT * FROM ";

        try(Connection conn = dataSource.getConnection();
             Statement statement = conn.createStatement()) {

            ResultSet result = statement.executeQuery(sql);

            String[] links

            while (!result.next()){

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
