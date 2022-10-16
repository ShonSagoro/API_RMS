package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UpdateUserRequest {
    private String name;
    private String password;

}
