package com.examplerm.rmdemo.controllers;

import com.examplerm.rmdemo.controllers.dtos.request.CreateAlbumRequest;

import com.examplerm.rmdemo.controllers.dtos.request.UpdateAlbumRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;

import com.examplerm.rmdemo.services.interfaces.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("album")
public class AlbumController {

    @Autowired
    private IAlbumService service;

    @GetMapping
    public ResponseEntity<BaseResponse> list(){
        BaseResponse baseResponse= service.list();
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateAlbumRequest request){
        BaseResponse baseResponse= service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());

    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable long id){
        BaseResponse baseResponse= service.get(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());

    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable Long id, @RequestBody UpdateAlbumRequest request){
        BaseResponse baseResponse=service.update(id, request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());

    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}

