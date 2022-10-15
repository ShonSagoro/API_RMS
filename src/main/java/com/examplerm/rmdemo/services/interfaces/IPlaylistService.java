package com.examplerm.rmdemo.services.interfaces;

import org.springframework.stereotype.Service;

import com.examplerm.rmdemo.controllers.dtos.request.CreatePlaylistRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdatePlaylistRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;

@Service
public interface IPlaylistService {
    
    BaseResponse create(CreatePlaylistRequest request);
    
    void delete(Long id);
    
    BaseResponse update(Long id, UpdatePlaylistRequest request);
   
    BaseResponse get(Long id);
    
    BaseResponse list();
    
}