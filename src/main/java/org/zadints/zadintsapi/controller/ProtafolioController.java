package org.zadints.zadintsapi.controller;

import jakarta.validation.Valid;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zadints.zadintsapi.dto.project.request.project.CreateProjectRequest;
import org.zadints.zadintsapi.dto.project.response.ProjectDetailResponse;
import org.zadints.zadintsapi.repository.sqlite.SqlitePortafolioRepository;
import org.zadints.zadintsapi.service.PortafolioService;

import java.util.List;

@RestController
@RequestMapping("/portafolio")
public class ProtafolioController {

    private final PortafolioService portafolioService;

    public ProtafolioController(PortafolioService portafolioService) {
        this.portafolioService = portafolioService;
    }


    @GetMapping("/projects")
    public ResponseEntity<List<ProjectDetailResponse>> getProjects() {
        List<ProjectDetailResponse> value = portafolioService.getProjects();
        return ResponseEntity.ok(value);
    }

    @PutMapping("/projects")
    public ResponseEntity<Void> createProjects(@Valid @RequestBody CreateProjectRequest project) {
       portafolioService.createProject(project);
       return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<ProjectDetailResponse> getUser(@PathVariable @UUID String id) {

        return ResponseEntity.ok(portafolioService.getProject(id));
    }

}
