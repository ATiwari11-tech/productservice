package dev.abhishek.productservice.exceptions;

import dev.abhishek.productservice.dtos.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ControllerAdvices {
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionDTO> handleNotFoundException(NotFoundException notFoundException){
        System.out.println("Not Found Exception happened");
        return new ResponseEntity(
                new ExceptionDTO(HttpStatus.NOT_FOUND,notFoundException.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
    @ExceptionHandler(NullPointerException.class)
    private ResponseEntity<ExceptionDTO> handleNullPointerException(NullPointerException nullPointerException){
        System.out.println("Null Pointer Exception");
        return new ResponseEntity(
                new ExceptionDTO(HttpStatus.NOT_FOUND,nullPointerException.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
