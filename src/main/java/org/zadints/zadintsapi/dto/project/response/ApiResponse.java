package org.zadints.zadintsapi.dto.project.response;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public record ApiResponse<T>(
        boolean success,
        int status,
        String message,
        T data,
        Map<String, List<String>> errors,
        Instant timestamp
) {

    public static <T> ApiResponse<T> success(
            int status,
            String message,
            T data) {

        return new ApiResponse<>(
                true,
                status,
                message,
                data,
                null,
                Instant.now()
        );
    }

    public static ApiResponse<Void> error(
            int status,
            String message) {

        return new ApiResponse<>(
                false,
                status,
                message,
                null,
                null,
                Instant.now()
        );
    }

    public static ApiResponse<Void> error(
            int status,
            String message,
            Map<String, List<String>> errors) {

        return new ApiResponse<>(
                false,
                status,
                message,
                null,
                errors,
                Instant.now()
        );
    }
}
