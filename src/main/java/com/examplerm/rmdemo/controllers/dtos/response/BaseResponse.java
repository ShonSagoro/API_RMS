package com.examplerm.rmdemo.controllers.dtos.response;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BaseResponse {
    private Object data;
    private String message;
    private Boolean success;
    private HttpStatus httpStatus;
}
