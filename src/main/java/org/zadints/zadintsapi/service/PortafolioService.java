package org.zadints.zadintsapi.service;

import org.springframework.stereotype.Service;
import org.zadints.zadintsapi.model.dto.GithubRepositoryDto;
import org.zadints.zadintsapi.model.entitie.Project;
import org.zadints.zadintsapi.repository.github.GithubRepository;
import org.zadints.zadintsapi.repository.sqlite.SqlitePortafolioRepository;

import java.util.List;
import java.util.Set;

@Service
public class PortafolioService {

    private final GithubRepository githubRepository;
    private final SqlitePortafolioRepository sqlitePortafolioRepository;

    public PortafolioService(GithubRepository githubRepository, SqlitePortafolioRepository sqlitePortafolioRepository) {
        this.githubRepository = githubRepository;
        this.sqlitePortafolioRepository = sqlitePortafolioRepository;
    }


    public void reloadRepositories(){
        List<GithubRepositoryDto> repositories = githubRepository.getRepositories();
        if (repositories.isEmpty()) return;

        Set<Project> projects = sqlitePortafolioRepository.getProjects();
    }


}
