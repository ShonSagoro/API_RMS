package com.examplerm.rmdemo.services.interfaces;

import org.springframework.stereotype.Service;

import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;

@Service
public interface IPodcastLibraryService {

    //http://localhost:8080/user/id_user/id_library/id_podcast

    BaseResponse Create(Long library_id, Long podcast_id);

    BaseResponse lisAllPodcastsByIdLibrary();

    BaseResponse getPodcastByIdLibrary();

    void deletePodcastByIdLibrary();

    void deleteByIdPodcastOfLibrary();

}
