package com.examplerm.rmdemo.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import com.examplerm.rmdemo.controllers.dtos.request.CreateUserRequest;
import com.examplerm.rmdemo.controllers.dtos.request.LoginRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateUserRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.entities.User;


public interface IUserService {

    BaseResponse update(Long id, UpdateUserRequest request);
    
    BaseResponse get(LoginRequest request);

    BaseResponse get(String name);

    BaseResponse uploadPhoto(MultipartFile file);
    
    BaseResponse create(CreateUserRequest request);

    User findById(Long id);

    void delete(Long id);

    User findByName(String name);
}
