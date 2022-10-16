package com.examplerm.rmdemo.controllers;

import com.examplerm.rmdemo.controllers.dtos.request.CreateSongRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateSongRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.services.interfaces.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("song")
public class SongController {
    @Autowired
    private ISongService service;

    @PostMapping
    public BaseResponse create(@RequestBody CreateSongRequest request){
        return service.create(request);
    }

    @GetMapping
    public BaseResponse list(){
        return service.list();
    }

    @GetMapping("{id}")
    public BaseResponse get(@PathVariable long id){
        return service.get(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
    @PutMapping("{id}")
    public BaseResponse update(@PathVariable Long id, @RequestBody UpdateSongRequest request){
        return service.update(id, request);
    }

}
