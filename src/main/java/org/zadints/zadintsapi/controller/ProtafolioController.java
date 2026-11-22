package org.zadints.zadintsapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zadints.zadintsapi.model.entitie.Project;

@RestController
@RequestMapping("/portafolio")
public class ProtafolioController {

    @GetMapping
    public String listar() {
        return "¡Mi primera API REST con Spring Boot!";
    }

    @GetMapping("/privada")
    public String privada() {
        return "Accediste a una ruta protegida";
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<Project> getUser(@PathVariable String id) {

        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
