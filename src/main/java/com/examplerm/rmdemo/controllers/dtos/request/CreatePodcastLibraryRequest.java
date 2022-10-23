package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreatePodcastLibraryRequest {

    private Long podcast_id;
    
    private Long library_id;

}
