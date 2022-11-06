package com.examplerm.rmdemo.services.interfaces;


import org.springframework.web.multipart.MultipartFile;

import com.examplerm.rmdemo.controllers.dtos.request.CreatePodcastRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdatePodcastRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.entities.Podcast;


public interface IPodcastService {

    BaseResponse create(CreatePodcastRequest request);

    BaseResponse uploadPhoto(MultipartFile file);
    
    BaseResponse update(Long id, UpdatePodcastRequest request);
    
    BaseResponse get(Long id);
    
    BaseResponse get(String name);

    BaseResponse list();

    void delete(Long id);

    Podcast findById(Long id);

    Podcast findByName(String name);
}
