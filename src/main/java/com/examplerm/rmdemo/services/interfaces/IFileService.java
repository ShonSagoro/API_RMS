package com.examplerm.rmdemo.services.interfaces;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IFileService {
    String upload(MultipartFile multipartFile);
    
}
