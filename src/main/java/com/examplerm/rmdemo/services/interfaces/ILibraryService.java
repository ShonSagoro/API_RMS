package com.examplerm.rmdemo.services.interfaces;

import org.springframework.stereotype.Service;

import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.entities.Library;

@Service
public interface ILibraryService {
    Library create();

    BaseResponse get(Long id);

    void delete(Long id);
}
