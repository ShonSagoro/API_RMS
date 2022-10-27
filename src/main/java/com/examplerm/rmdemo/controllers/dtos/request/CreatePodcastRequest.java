package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
public class CreatePodcastRequest {
    
    @NonNull
    private String name;

    private String description;

    private String category;

}
