package com.examplerm.rmdemo.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateChapterResponse {
    
    private Long id;

    private String title;

    private String description;

    private String duration;

}
