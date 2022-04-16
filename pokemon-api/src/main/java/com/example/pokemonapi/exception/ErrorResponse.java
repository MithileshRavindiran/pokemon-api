package com.example.pokemonapi.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO containing error information.
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private final String code;
    private final String message;
    private  final List<FieldError> fields = new ArrayList<>();
    private  final List<ErrorResponse> subErrors = new ArrayList<>();


    private void addValidationError(FieldError fieldError) {
        fields.add(fieldError);
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

}
