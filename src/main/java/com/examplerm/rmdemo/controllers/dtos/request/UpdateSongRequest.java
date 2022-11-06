package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UpdateSongRequest {

    private String name;
    
    private String duration;

    private String photoUrl;

}
