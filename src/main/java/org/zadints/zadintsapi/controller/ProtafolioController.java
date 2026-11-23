package org.zadints.zadintsapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zadints.zadintsapi.dto.project.response.ProjectDetailResponse;
import org.zadints.zadintsapi.repository.sqlite.SqlitePortafolioRepository;

import java.util.List;

@RestController
@RequestMapping("/portafolio")
public class ProtafolioController {

    private final SqlitePortafolioRepository sqlitePortafolioRepository;

    public ProtafolioController(SqlitePortafolioRepository sqlitePortafolioRepository) {
        this.sqlitePortafolioRepository = sqlitePortafolioRepository;
    }


    @GetMapping("/projects")
    public ResponseEntity<List<ProjectDetailResponse>> getProjects() {
        List<ProjectDetailResponse> value = sqlitePortafolioRepository.getProjects();
        return ResponseEntity.ok(value);
    }

    @PutMapping("/projects")
    public String privada() {
        return "Accediste a una ruta protegida";
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<ProjectDetailResponse> getUser(@PathVariable String id) {

        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
