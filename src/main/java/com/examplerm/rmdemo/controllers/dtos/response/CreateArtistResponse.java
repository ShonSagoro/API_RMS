package com.examplerm.rmdemo.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CreateArtistResponse {
    
    private Long id;

    private String name;

    private String discography;

    private String listener;

}
