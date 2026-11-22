package org.zadints.zadintsapi.model.entitie;

import java.time.LocalDateTime;
import java.util.List;

public class Project {

    private final String uuid;
    private String name;
    private String problem;
    private String solution;
    private String result;
    private List<String> stack;
    private String repository;
    private byte[] image;

    List<Collaborator> collaboratorList;
    String enterprise;

    LocalDateTime createAt;
    LocalDateTime lastCommit;
    int countCommits;
    int countPullRequest;


    public Project(String uuid, String name, String problem, String solution, String result, List<String> stack, String repository, byte[] image, List<Collaborator> collaboratorList, String enterprise, LocalDateTime createAt, LocalDateTime lastCommit, int countCommits, int countPullRequest) {
        this.uuid = uuid;
        this.name = name;
        this.problem = problem;
        this.solution = solution;
        this.result = result;
        this.stack = stack;
        this.repository = repository;
        this.image = image;
        this.collaboratorList = collaboratorList;
        this.enterprise = enterprise;
        this.createAt = createAt;
        this.lastCommit = lastCommit;
        this.countCommits = countCommits;
        this.countPullRequest = countPullRequest;
    }
}
