package com.examplerm.rmdemo.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChapterResponse {

    private Long id;

    private String title;

    private String description;

    private String creationDate;

    private String duration;

    private String chapterUrl;

    private PodcastResponse podcast;

    private String photoUrl;
    
}
