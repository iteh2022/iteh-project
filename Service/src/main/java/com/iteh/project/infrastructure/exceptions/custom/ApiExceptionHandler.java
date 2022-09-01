package com.iteh.project.infrastructure.exceptions.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<CustomException> onNotFoundException(RuntimeException ex) {
        CustomException customException = new CustomException(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(customException, HttpStatus.BAD_REQUEST);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class CustomException {
    private String message;
    private HttpStatus httpStatus;
    private ZonedDateTime dateTime;
}
