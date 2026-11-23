package org.zadints.zadintsapi.dto.project.response;

import java.util.List;

public class ProjectDetailResponse {

    private final String uuid;
    private String name;
    private String status;
    private String type;

    private String problem;
    private String solution;
    private String result;

    private String repository;
    private byte[] image;

    private String create_by;
    private String createAt;
    private int commits;

    private List<String> stack;
    private List<CollaboratorsDetailResponse> collaboratorList;


    public ProjectDetailResponse(String uuid, String name, String status, String type, String problem, String solution, String result, String repository, byte[] image, String create_by, String createAt, int commits) {
        this.uuid = uuid;
        this.name = name;
        this.status = status;
        this.type = type;
        this.problem = problem;
        this.solution = solution;
        this.result = result;
        this.repository = repository;
        this.image = image;
        this.create_by = create_by;
        this.createAt = createAt;
        this.commits = commits;
    }

    public void setStack(List<String> stack) {
        this.stack = stack;
    }

    public void setCollaboratorList(List<CollaboratorsDetailResponse> collaboratorList) {
        this.collaboratorList = collaboratorList;
    }

    public String getUuid() {
        return uuid;
    }
}
