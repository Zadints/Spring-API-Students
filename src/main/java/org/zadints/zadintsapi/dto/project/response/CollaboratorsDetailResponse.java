package org.zadints.zadintsapi.dto.project.response;

public record CollaboratorsDetailResponse(
        String collaborator_uuid,
        String name,
        String link,
        String description,
        byte[] image
) {
}
