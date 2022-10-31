package com.examplerm.rmdemo.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetPodcastLibraryResponse {

    private Long id;

    private PodcastResponse podcast;

    private LibraryResponse library;
    
}
