package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
public class CreateChapterRequest {
    
    @NonNull
    private String title;

    private String description;

    @NonNull
    private String duration;

    private Long podcastId;
}
