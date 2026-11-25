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


    public Optional<ProjectDetailResponse> getProject(String uuid){
        String sql1 = "SELECT * FROM projects WHERE project_uuid = ?";
        String sql2 = "SELECT * FROM stack WHERE project_uuid = ?";

        String sql3 = """
                SELECT c.collaborator_uuid, c.name, c.link, c.description, c.image
                FROM collaborator_projects AS p
                INNER JOIN collaborators AS c
                    ON p.collaborator_uuid = c.collaborator_uuid
                WHERE project_uuid = ?
                """;

        try(Connection conn = dataSource.getConnection()) {

            conn.setAutoCommit(false);

            try (PreparedStatement stmt1 = conn.prepareStatement(sql1);
                 PreparedStatement stmt2 = conn.prepareStatement(sql2);
                 PreparedStatement stmt3 = conn.prepareStatement(sql3)) {

                stmt1.setString(1,uuid);
                stmt2.setString(1,uuid);
                stmt3.setString(1,uuid);

                ResultSet result1 = stmt1.executeQuery();
                ResultSet result2 = stmt2.executeQuery();
                ResultSet result3 = stmt3.executeQuery();


                if (!result1.next()) {
                    conn.commit();
                    return Optional.empty();
                }

                var project = new ProjectDetailResponse(
                        uuid,
                        result1.getString("project_name"),
                        result1.getString("project_status"),
                        result1.getString("project_type"),
                        result1.getString("project_problem"),
                        result1.getString("project_solution"),
                        result1.getString("project_result"),
                        result1.getString("project_repository"),
                        result1.getBytes("project_image"),
                        result1.getString("project_create_by"),
                        result1.getString("project_created"),
                        result1.getInt("project_commits")
                );

                List<String> stack = new ArrayList<>();
                while (result2.next()){
                    stack.add(result2.getString("stack_name"));
                }
                List<CollaboratorsDetailResponse> col = new ArrayList<>();
                while (result3.next()){
                    col.add(new CollaboratorsDetailResponse(
                            result3.getString("collaborator_uuid"),
                            result3.getString("name"),
                            result3.getString("link"),
                            result3.getString("description"),
                            result3.getBytes("image")
                    ));
                }

                project.setStack(stack);
                project.setCollaboratorList(col);
                conn.commit();
                return Optional.of(project);


            }catch (SQLException | RuntimeException e) {

                try {
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    e.addSuppressed(rollbackException);
                }

                throw e;
            }


        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

}
