package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateChapterRequest {
    
    private String title;

    private String description;

    private String duration;

    private Long podcastId;

    private String chapterUrl;

    private String photoUrl;

}
