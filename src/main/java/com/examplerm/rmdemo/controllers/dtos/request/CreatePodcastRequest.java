package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreatePodcastRequest {
    
    private String name;

    private String description;

    private String category;

    private String photoUrl;


}
