package com.examplerm.rmdemo.controllers;

import com.examplerm.rmdemo.controllers.dtos.request.CreateUserRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateUserRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;

import com.examplerm.rmdemo.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService service;

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateUserRequest request) {
        BaseResponse baseResponse= service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());

    }

    @PostMapping("upload/photo")
    public ResponseEntity<BaseResponse> uploadPhoto(@RequestParam MultipartFile file){
        BaseResponse baseResponse= service.uploadPhoto(file);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());

    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable Long id){
        BaseResponse baseResponse= service.get(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());

    }

    @GetMapping("name/{name}")
    public ResponseEntity<BaseResponse> get(@PathVariable String name){
        BaseResponse baseResponse= service.get(name);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse>  update(@RequestBody UpdateUserRequest request, @PathVariable Long id){
        BaseResponse baseResponse= service.update(id,request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());

    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
