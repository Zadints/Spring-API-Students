package org.zadints.zadintsapi.model.entitie;

import java.time.LocalDateTime;
import java.util.List;

public class Project {

    String name;
    String problem;
    String solution;
    String result;
    List<String> stack;
    String repository;
    byte[] image;

    List<Collaborator> collaboratorList;
    String enterprise;

    LocalDateTime createAt;
    LocalDateTime lastCommit;
    int countCommits;
    int countPullRequest;


}
