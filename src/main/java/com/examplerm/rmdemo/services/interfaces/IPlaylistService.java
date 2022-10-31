package com.examplerm.rmdemo.services.interfaces;

import org.springframework.stereotype.Service;

import com.examplerm.rmdemo.controllers.dtos.request.CreatePlaylistRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdatePlaylistRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.entities.Playlist;

@Service
public interface IPlaylistService {
    
    BaseResponse create(CreatePlaylistRequest request);
    
    BaseResponse update(Long id, UpdatePlaylistRequest request);
    
    BaseResponse get(Long id);
    
    BaseResponse list();

    void delete(Long id);

    Playlist findById(Long id);
    
}