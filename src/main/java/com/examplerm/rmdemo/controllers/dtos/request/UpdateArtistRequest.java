package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UpdateArtistRequest {

    private String name;

    private String listener;

    private String discography;
    
}
