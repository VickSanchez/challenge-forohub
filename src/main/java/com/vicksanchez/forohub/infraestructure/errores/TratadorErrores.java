package com.vicksanchez.forohub.infraestructure.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //middleware para todas las excepciones
public class TratadorErrores {

    @ExceptionHandler(EntityNotFoundException.class) // Especificamos que maneje la excepcion del tipo que esta dentro de los parentesis
    public ResponseEntity error404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException exception){
        var errores = exception.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity error400(IllegalArgumentException exception){
        var error = exception.getMessage();
        return ResponseEntity.badRequest().body(error);
    }

    private record DatosErrorValidacion(String campo, String error){
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
