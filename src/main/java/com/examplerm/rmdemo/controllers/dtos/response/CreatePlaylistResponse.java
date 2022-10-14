package com.examplerm.rmdemo.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CreatePlaylistResponse {

    private Long id;

    private String name;

    private String description;

    private String dateCreation;
}