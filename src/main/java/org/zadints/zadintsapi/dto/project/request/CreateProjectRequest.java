package org.zadints.zadintsapi.dto.project.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.zadints.zadintsapi.model.enums.ProjectStatus;
import org.zadints.zadintsapi.model.enums.ProjectType;

public record CreateProjectRequest(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100)
        String name,

        @NotNull(message = "El estado es obligatorio")
        ProjectStatus status,

        @NotNull(message = "El tipo es obligatorio")
        ProjectType type,

        @NotBlank(message = "El problema es obligatorio")
        String problem,

        @NotBlank(message = "La solución es obligatoria")
        String solution,

        @NotBlank(message = "El resultado es obligatorio")
        String result,

        @NotBlank(message = "El repositorio es obligatorio")
        String repository
) {
}
