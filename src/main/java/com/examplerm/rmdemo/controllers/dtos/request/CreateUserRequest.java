package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter@Setter
public class CreateUserRequest {

    @NonNull
    private String name;
    @NonNull
    private String password;


    @NonNull
    private String email;

    private Integer facebook_id;

    private String library_id;
}
