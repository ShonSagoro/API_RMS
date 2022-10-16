package com.examplerm.rmdemo.services.interfaces;


import org.springframework.stereotype.Service;

import com.examplerm.rmdemo.controllers.dtos.request.CreatePodcastRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdatePodcastRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;

@Service
public interface IPodcastService {
    BaseResponse create(CreatePodcastRequest request);
    
    void delete(Long id);
    
    BaseResponse update(Long id, UpdatePodcastRequest request);
   
    BaseResponse get(Long id);
    
    BaseResponse list();
}
