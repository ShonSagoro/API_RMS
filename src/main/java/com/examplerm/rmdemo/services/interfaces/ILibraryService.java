package com.examplerm.rmdemo.services.interfaces;


import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.entities.Library;

public interface ILibraryService {

    Library create();

    BaseResponse get(Long id);

    void delete(Long id);

    Library findById(Long id);
}
