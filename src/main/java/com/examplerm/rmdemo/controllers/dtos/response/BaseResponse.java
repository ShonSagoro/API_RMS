package com.examplerm.rmdemo.controllers.dtos.response;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Builder @Getter @Setter
public class BaseResponse {
    private Object data;
    private String message;
    private Boolean success;
    private HttpStatus httpStatus;
}
