package com.examplerm.rmdemo.controllers.advices;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;

public class ValidationHandlerAdvice extends ResponseEntityExceptionHandler{

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors= new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error->{
            String fieldName=((FieldError) error).getField();
            String message= error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        BaseResponse baseResponse = BaseResponse.builder()
            .data(errors)
            .message("Validation failed")
            .success(Boolean.FALSE)
            .httpStatus(HttpStatus.BAD_REQUEST).build();
    
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

}
