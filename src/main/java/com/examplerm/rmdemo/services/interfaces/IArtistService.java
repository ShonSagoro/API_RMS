package com.examplerm.rmdemo.services.interfaces;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.examplerm.rmdemo.controllers.dtos.request.CreateArtistRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateArtistRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.entities.Artist;

@Service
public interface IArtistService {
    
    BaseResponse create(CreateArtistRequest request);

    BaseResponse uploadPhoto(MultipartFile file);

    BaseResponse get(Long id);
    
    BaseResponse get(String name);
    
    BaseResponse list();
    
    BaseResponse update(Long id, UpdateArtistRequest request);
    
    void delete(Long id);

    Artist findById(Long id);

    Artist findByName(String name);

}
