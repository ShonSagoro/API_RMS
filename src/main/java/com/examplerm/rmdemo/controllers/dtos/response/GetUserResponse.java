package com.examplerm.rmdemo.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetUserResponse {

    private Long id;
    private String name;
    private String password;
    private String email;
    private Long library_id;
}
