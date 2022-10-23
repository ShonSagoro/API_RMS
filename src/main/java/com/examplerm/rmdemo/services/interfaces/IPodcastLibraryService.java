package com.examplerm.rmdemo.services.interfaces;

import org.springframework.stereotype.Service;

import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;

@Service
public interface IPodcastLibraryService {

    //http://localhost:8080/Podcast_Library/
    BaseResponse listAllPodcastsByIdLibrary(Long librarytId);
    
    void deletePodcastsByIdLibrary(Long libraryId);
}
