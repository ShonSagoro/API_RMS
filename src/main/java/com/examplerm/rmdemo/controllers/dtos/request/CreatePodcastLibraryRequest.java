package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreatePodcastLibraryRequest {

    private Long idPodcast;
    
    private Long idLibrary;

}
