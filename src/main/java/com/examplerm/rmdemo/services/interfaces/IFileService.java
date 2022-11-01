package com.examplerm.rmdemo.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    
    String upload(MultipartFile multipartFile);
    
}
