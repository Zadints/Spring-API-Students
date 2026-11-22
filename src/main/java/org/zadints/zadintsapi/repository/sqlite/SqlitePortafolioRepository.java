package org.zadints.zadintsapi.repository.sqlite;

import org.springframework.stereotype.Repository;
import org.zadints.zadintsapi.model.entitie.Project;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Repository
public class SqlitePortafolioRepository {
    private final DataSource dataSource;

    public SqlitePortafolioRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public Set<Project> getProjects(){
        String sql = "SELECT * FROM projects";

        try(Connection conn = dataSource.getConnection();
            Statement statement = conn.createStatement()) {

            ResultSet result = statement.executeQuery(sql);

            Set<Project> projects = new HashSet<>();

            while (!result.next()){

                projects.add(new Project(
                        result.getString("project_uuid"),
                        result.getString("project_name"),
                        result.getString("project_status"),
                        result.getString("project_type"),
                        result.getString("project_problem"),
                        result.getString("project_solution"),
                        result.getString("project_result"),
                        result.getString("project_repository"),
                        result.getBytes("project_image"),
                        result.getString("project_created"),
                        result.getInt("project_commits")

                ));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Set.of();
    }
}
