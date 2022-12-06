package com.examplerm.rmdemo.services.interfaces;


import com.examplerm.rmdemo.controllers.dtos.request.CreatePodcastLibraryRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;


public interface IPodcastLibraryService {

    BaseResponse create(CreatePodcastLibraryRequest request);

    BaseResponse listAllPodcastsByIdLibrary(Long librarytId);
    
    void deletePodcastFromLibraryByThierIds(Long podcastId, Long libraryId);
}
