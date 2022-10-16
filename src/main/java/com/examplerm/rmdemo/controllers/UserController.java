package com.examplerm.rmdemo.controllers;

import com.examplerm.rmdemo.controllers.dtos.request.CreateUserRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateUserRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;

import com.examplerm.rmdemo.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService service;

    @PostMapping
    public BaseResponse create(@RequestBody CreateUserRequest request) {
        return service.create(request);
    }

    @GetMapping("{id}")
    public BaseResponse get(@PathVariable Long id){
            return service.get(id);
    }

    @PutMapping("{id}")
    public BaseResponse update(@RequestBody UpdateUserRequest request, @PathVariable Long id){
        return service.update(id,request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
