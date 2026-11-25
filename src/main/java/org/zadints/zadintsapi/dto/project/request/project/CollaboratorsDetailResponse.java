package org.zadints.zadintsapi.dto.project.request.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.UUID;

public record CollaboratorsDetailResponse(
        @NotBlank(message = "El UUID es obligatorio")
        @UUID(message = "El formato del UUID no es válido")
        String uuid,

        @NotBlank
        @Size(max = 100)
        String name,

        @NotBlank
        @URL
        String link,

        @NotBlank
        @Size(max = 500)
        String description,

        @NotEmpty
        byte[] image
) {


}
