package com.examplerm.rmdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examplerm.rmdemo.controllers.dtos.request.CreatePodcastRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdatePodcastRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.services.interfaces.IPodcastService;

@RestController
@RequestMapping("podcast")
public class PodcastController {
    @Autowired
    private IPodcastService service;

    @GetMapping
    public BaseResponse list(){
        return service.list();
    }

    @GetMapping("{id}")
    public BaseResponse get(@PathVariable long id){
        return service.get(id);
    }

    @PostMapping
    public BaseResponse create(@RequestBody CreatePodcastRequest request){
        return service.create(request);
    }

    @PutMapping("{id}")
    public BaseResponse update(@PathVariable Long id, @RequestBody UpdatePodcastRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    
}
