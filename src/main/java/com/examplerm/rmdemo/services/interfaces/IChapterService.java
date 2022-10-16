package com.examplerm.rmdemo.services.interfaces;

import org.springframework.stereotype.Service;

import com.examplerm.rmdemo.controllers.dtos.request.CreateChapterRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateChapterRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;

@Service
public interface IChapterService {
    BaseResponse create(CreateChapterRequest request);
    
    void delete(Long id);
    
    BaseResponse update(Long id, UpdateChapterRequest request);
   
    BaseResponse get(Long id);
    
    BaseResponse list();
    
}
