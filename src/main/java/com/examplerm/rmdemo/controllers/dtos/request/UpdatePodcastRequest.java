package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UpdatePodcastRequest {

    private String name;

    private String description;

    private String category;
    
}
