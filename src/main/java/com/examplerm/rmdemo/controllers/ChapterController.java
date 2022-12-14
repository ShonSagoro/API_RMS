package com.examplerm.rmdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.examplerm.rmdemo.controllers.dtos.request.CreateChapterRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateChapterRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.services.interfaces.IChapterService;

@RestController
@RequestMapping("chapter")
public class ChapterController {

    @Autowired
    private IChapterService service;

    @GetMapping
    public ResponseEntity<BaseResponse> list(){
        BaseResponse baseResponse= service.list();
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable long id){
        BaseResponse baseResponse= service.get(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("name/{name}")
    public ResponseEntity<BaseResponse> get(@PathVariable String name){
        BaseResponse baseResponse= service.get(name);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateChapterRequest request){
        BaseResponse baseResponse= service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }
    @PostMapping("upload/chapter")
    public ResponseEntity<BaseResponse> uploadChapter(@RequestParam MultipartFile file){
        BaseResponse baseResponse= service.uploadChapter(file);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping("upload/photo")
    public ResponseEntity<BaseResponse> uploadPhoto(@RequestParam MultipartFile file){
        BaseResponse baseResponse= service.uploadPhoto(file);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable Long id, @RequestBody UpdateChapterRequest request){
        BaseResponse baseResponse= service.update(id, request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @GetMapping("health")
    public String health() {
        return "Ok";
    }

}
