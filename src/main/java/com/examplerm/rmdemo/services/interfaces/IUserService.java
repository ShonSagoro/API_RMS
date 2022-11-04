package com.examplerm.rmdemo.services.interfaces;

import com.examplerm.rmdemo.controllers.dtos.request.CreateUserRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateUserRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.entities.User;


public interface IUserService {

    BaseResponse update(Long id, UpdateUserRequest request);
    
    BaseResponse get(Long id);
    
    BaseResponse create(CreateUserRequest request);

    User findById(Long id);

    void delete(Long id);

    BaseResponse upload(MultipartFile file);
}
