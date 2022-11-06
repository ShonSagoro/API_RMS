package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CreateUserRequest {

    private String name;
    
    private String password;

    private String email;

    private String photoUrl;

}
