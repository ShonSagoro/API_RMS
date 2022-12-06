package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UpdateChapterRequest {

    private String title;

    private String description;

    private String photoUrl;
    
}
