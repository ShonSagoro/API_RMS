package com.examplerm.rmdemo.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class GetChapterResponse {
    
    private Long id;

    private String title;

    private String description;

    private String creationDate;

    private String duration;

    private String chapterUrl;

    private PodcastResponse podcast;

}
