package com.examplerm.rmdemo.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ArtistResponse {
    
    private Long id;

    private String name;

    private Integer listener;

    private String photoUrl;

}
