package com.canclini.finalLaboIII.Handlers;

import com.canclini.finalLaboIII.Business.Dtos.ResponseDtoJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDtoJson> handleValidationException(MethodArgumentNotValidException ex) {
        String mensaje = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        return ResponseEntity.badRequest().body(new ResponseDtoJson(HttpStatus.BAD_REQUEST, mensaje,null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDtoJson> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(new ResponseDtoJson(HttpStatus.BAD_REQUEST, "Ha Ocurrido un Error!",null));
    }
}
