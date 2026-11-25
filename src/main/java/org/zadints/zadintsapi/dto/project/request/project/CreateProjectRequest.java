package org.zadints.zadintsapi.dto.project.request.project;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.zadints.zadintsapi.dto.project.response.CollaboratorsDetailResponse;
import org.zadints.zadintsapi.model.enums.ProjectStatus;
import org.zadints.zadintsapi.model.enums.ProjectType;

import java.time.LocalDate;
import java.util.List;

public record CreateProjectRequest(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100)
        String name,

        @NotNull(message = "El estado es obligatorio")
        ProjectStatus status,

        @NotNull(message = "El tipo es obligatorio")
        ProjectType type,

        @NotBlank(message = "El problema es obligatorio")
        @Size(max = 500)
        String problem,

        @NotBlank(message = "La solución es obligatoria")
        @Size(max = 500)
        String solution,

        @NotBlank(message = "El resultado es obligatorio")
        @Size(max = 500)
        String result,

        @NotBlank(message = "El repositorio de github es obligatorio")
        @NotNull(message = "La imagen es obligatoria")
        @Size(min = 1, message = "La imagen no puede estar vacía")
        byte[] image,

        @NotBlank(message = "El creador es obligatorio")
        @Size(max = 100)
        String create_by,

        @NotNull(message = "La fecha de creación es obligatoria")
        @PastOrPresent(message = "La fecha no puede ser futura")
        LocalDate createAt,

        @PositiveOrZero(message = "Los commits no pueden ser negativos")
        int commits,

        @NotEmpty(message = "Debe registrar al menos una tecnología")
        List<@NotBlank @Size(max = 50) String> stack,

        @NotNull(message = "La lista de colaboradores es obligatoria")
        List<@NotNull @Valid CollaboratorsDetailResponse> collaboratorList

) {
}
