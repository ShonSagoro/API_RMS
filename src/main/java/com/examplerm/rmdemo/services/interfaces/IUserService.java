package com.examplerm.rmdemo.services.interfaces;

import com.examplerm.rmdemo.controllers.dtos.request.CreateUserRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateUserRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.controllers.dtos.response.GetUserResponse;
import com.examplerm.rmdemo.entities.User;
import org.springframework.stereotype.Service;


public interface IUserService {


    BaseResponse update(Long id, UpdateUserRequest request);


    User findOneAndEnsureExist(Long id);

    void delete(Long id);

    User save(User user);


    BaseResponse get(Long id);

    BaseResponse create(CreateUserRequest request);
}
