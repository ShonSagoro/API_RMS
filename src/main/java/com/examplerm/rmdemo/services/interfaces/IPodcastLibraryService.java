package com.examplerm.rmdemo.services.interfaces;

import org.springframework.stereotype.Service;

import com.examplerm.rmdemo.controllers.dtos.request.CreatePodcastLibraryRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;

@Service
public interface IPodcastLibraryService {

    BaseResponse create(CreatePodcastLibraryRequest request);

    BaseResponse listAllPodcastsByIdLibrary(Long librarytId);
    
    void deletePodcastsByIdLibrary(Long libraryId);
}
