package com.examplerm.rmdemo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.examplerm.rmdemo.services.interfaces.IFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private IFileService service;

    @PostMapping
    public String upload(@RequestParam MultipartFile file) {
        return service.upload(file);
    }

    
}
