package com.examplerm.rmdemo.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResponse {

    private Long id;

    private String name;

    private String email;

    private Long library;

    private String photoUrl;

    private Boolean admin;
}
