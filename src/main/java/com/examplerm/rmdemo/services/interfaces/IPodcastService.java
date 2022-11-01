package com.examplerm.rmdemo.services.interfaces;


import com.examplerm.rmdemo.controllers.dtos.request.CreatePodcastRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdatePodcastRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.entities.Podcast;


public interface IPodcastService {

    BaseResponse create(CreatePodcastRequest request);
    
    BaseResponse update(Long id, UpdatePodcastRequest request);
    
    BaseResponse get(Long id);
    
    BaseResponse list();

    void delete(Long id);

    Podcast findById(Long id);
}
