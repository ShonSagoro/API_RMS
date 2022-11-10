package com.examplerm.rmdemo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.services.interfaces.ILibraryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("library")
public class LibraryController {
    @Autowired
    private ILibraryService service;
    
    @GetMapping
    public ResponseEntity<BaseResponse> list(){
        BaseResponse baseResponse= service.list();
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("health")
    public String health() {
        return "Ok";
    }
    
}
