package com.examplerm.rmdemo.services.interfaces;


import org.springframework.stereotype.Service;

import com.examplerm.rmdemo.controllers.dtos.request.CreateArtistRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateArtistRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;

@Service
public interface IArtistService {
    
    BaseResponse create(CreateArtistRequest request);

    void delete(Long id);

    BaseResponse list();

    BaseResponse update(Long id, UpdateArtistRequest request);

    BaseResponse get(Long id);

}
