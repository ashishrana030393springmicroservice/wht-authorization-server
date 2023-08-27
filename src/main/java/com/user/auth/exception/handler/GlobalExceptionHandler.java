package com.user.auth.exception.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.nimbusds.jose.shaded.gson.JsonObject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
//@Validated
public class GlobalExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Map<String, List<String>> handleValidationException(ConstraintViolationException ex){
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        Map<String, List<String>> errors = new HashMap<>();
        for(ConstraintViolation violation : violations){
            if(errors.get(violation.getPropertyPath().toString()) ==null){
                errors.put(violation.getPropertyPath().toString(), new ArrayList<>());
            }
            errors.get(violation.getPropertyPath().toString()).add(violation.getMessage());
        }
        return errors;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Map<String,List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, List<String>> errors = new HashMap<>();
        for(FieldError error: bindingResult.getFieldErrors()){
            if(errors.get(error.getField()) ==null){
                errors.put(error.getField(),new ArrayList<>());
            }
            errors.get(error.getField()).add(error.getDefaultMessage());
        }
        return errors;
    }

}
