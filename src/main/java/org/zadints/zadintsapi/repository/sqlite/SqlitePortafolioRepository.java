package org.zadints.zadintsapi.repository.sqlite;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.zadints.zadintsapi.dto.project.response.CollaboratorsDetailResponse;
import org.zadints.zadintsapi.dto.project.response.ProjectDetailResponse;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Repository
public class SqlitePortafolioRepository {
    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public SqlitePortafolioRepository(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, List<String>> getStackProject() {

        String sql = """
        SELECT project_uuid, stack_name
        FROM stack
        """;

        return jdbcTemplate.query(sql, rs -> {

            Map<String, List<String>> stacks = new HashMap<>();

            while (rs.next()) {
                String uuid = rs.getString("project_uuid");
                String stack = rs.getString("stack_name");

                stacks.computeIfAbsent(
                        uuid,
                        key -> new ArrayList<>()
                ).add(stack);
            }

            return stacks;
        });
    }

    public Map<String,List<CollaboratorsDetailResponse>> getCollaborator(){
        String sql = "SELECT * FROM collaborators";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            ResultSet result = pstm.executeQuery();
            Map<String,List<CollaboratorsDetailResponse>> collaborators = new HashMap<>();

            while (result.next()){

                String uuid = result.getString("collaborator_uuid");

                var colab = new CollaboratorsDetailResponse(
                        uuid,
                        result.getString("name"),
                        result.getString("link"),
                        result.getString("description"),
                        result.getBytes("image")
                );

                collaborators.computeIfAbsent(
                        uuid,
                        key -> new ArrayList<>()
                ).add(colab);

            }

            return collaborators;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProjectDetailResponse> getProjects(){
        String sql = "SELECT * FROM projects";

        try(Connection conn = dataSource.getConnection();
            Statement statement = conn.createStatement()) {

            ResultSet result = statement.executeQuery(sql);

            List<ProjectDetailResponse> projects = new ArrayList<>();

            while (result.next()){
                String uuid = result.getString("project_uuid");
                projects.add(new ProjectDetailResponse(
                        uuid,
                        result.getString("project_name"),
                        result.getString("project_status"),
                        result.getString("project_type"),
                        result.getString("project_problem"),
                        result.getString("project_solution"),
                        result.getString("project_result"),
                        result.getString("project_repository"),
                        result.getBytes("project_image"),
                        result.getString("project_create_by"),
                        result.getString("project_created"),
                        result.getInt("project_commits")
                ));
            }

            return projects;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
