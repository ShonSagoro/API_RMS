package com.examplerm.rmdemo.controllers;

import com.examplerm.rmdemo.controllers.dtos.request.CreateAlbumRequest;

import com.examplerm.rmdemo.controllers.dtos.request.UpdateAlbumRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;

import com.examplerm.rmdemo.services.interfaces.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("album")
public class AlbumController {

    @Autowired
    private IAlbumService service;

    @GetMapping
    public BaseResponse list(){
        return service.list();
    }

    @PostMapping
    public BaseResponse create(@RequestBody CreateAlbumRequest request){
        return service.create(request);
    }

    @GetMapping("{id}")
    public BaseResponse get(@PathVariable long id){
        return service.get(id);
    }

    @PutMapping("{id}")
    public BaseResponse update(@PathVariable Long id, @RequestBody UpdateAlbumRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
