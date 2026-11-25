package org.zadints.zadintsapi.service;

import org.springframework.stereotype.Service;
import org.zadints.zadintsapi.dto.project.request.GithubRepositoryDto;
import org.zadints.zadintsapi.dto.project.request.project.CreateProjectRequest;
import org.zadints.zadintsapi.dto.project.response.CollaboratorsDetailResponse;
import org.zadints.zadintsapi.dto.project.response.ProjectDetailResponse;
import org.zadints.zadintsapi.exceptions.ProjectNotFoundException;
import org.zadints.zadintsapi.repository.github.GithubRepository;
import org.zadints.zadintsapi.repository.sqlite.SqlitePortafolioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
//@ActiveProfiles("test")
public class PortafolioService {

    private final GithubRepository githubRepository;
    private final SqlitePortafolioRepository sqlitePortafolioRepository;

    public PortafolioService(GithubRepository githubRepository, SqlitePortafolioRepository sqlitePortafolioRepository) {
        this.githubRepository = githubRepository;
        this.sqlitePortafolioRepository = sqlitePortafolioRepository;
    }


    public List<ProjectDetailResponse> getProjects(){
        List<GithubRepositoryDto> repositories = githubRepository.getRepositories();
        if (repositories.isEmpty()) return new ArrayList<>();


        List<ProjectDetailResponse> projects = sqlitePortafolioRepository.getProjects();
        Map<String, List<String>>  stacks = sqlitePortafolioRepository.getStackProject();
        Map<String, List<CollaboratorsDetailResponse>> collaborators = sqlitePortafolioRepository.getCollaborator();


        for (ProjectDetailResponse p : projects){
            p.setStack(stacks.get(p.getUuid()));
            p.setCollaboratorList(collaborators.get(p.getUuid()));
        }

        return projects;
    }


    public ProjectDetailResponse getProject(String uuid){
        Optional<ProjectDetailResponse> project= sqlitePortafolioRepository.getProject(uuid);
        if (project.isEmpty()) throw new ProjectNotFoundException("Project " + uuid + " no exist.");
        return project.get();
    }


    public void createProject(CreateProjectRequest project){

    }

}
