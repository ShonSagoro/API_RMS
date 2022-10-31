package com.examplerm.rmdemo.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlaylistResponse {

    private Long id;

    private String name;

    private String description;

    private String duration;

    private String creationDate;
    
}
