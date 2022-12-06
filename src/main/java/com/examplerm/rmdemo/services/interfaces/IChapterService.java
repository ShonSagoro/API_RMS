package com.examplerm.rmdemo.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import com.examplerm.rmdemo.controllers.dtos.request.CreateChapterRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateChapterRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.entities.Chapter;


public interface IChapterService {
    BaseResponse create(CreateChapterRequest request);

    BaseResponse uploadPhoto(MultipartFile file);

    BaseResponse uploadChapter(MultipartFile file);
    
    BaseResponse update(Long id, UpdateChapterRequest request);
    
    BaseResponse get(Long id);

    BaseResponse get(String name);

    BaseResponse getChapters(Long id);
    
    BaseResponse list();
    
    void delete(Long id);
    
    Chapter findById(Long id);

    Chapter findByName(String name);
}
