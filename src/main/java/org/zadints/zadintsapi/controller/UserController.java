package org.zadints.zadintsapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping
    public String listar() {
        return "¡Mi primera API REST con Spring Boot!";
    }

    @GetMapping("/privada")
    public String privada() {
        return "Accediste a una ruta protegida";
    }
}
