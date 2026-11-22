package org.zadints.zadintsapi.service;

public class GithubSyncService {

    private final ProjectRepository repository;

    public GithubSyncService(ProjectRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void sync(GithubPushDto dto) {
        repository.updateFromGithub(dto);
    }
}
