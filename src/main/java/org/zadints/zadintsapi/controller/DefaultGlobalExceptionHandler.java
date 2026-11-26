package org.zadints.zadintsapi.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.zadints.zadintsapi.dto.project.response.ApiResponse;

@RestControllerAdvice
public class DefaultGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(
            NoResourceFoundException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        return new ResponseEntity<>(
                ApiResponse.error(
                        status.value(),
                        "La ruta solicitada no existe"
                ),
                headers,
                status
        );
    }
}
