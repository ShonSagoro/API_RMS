package com.examplerm.rmdemo.services;

import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.entities.Library;
import com.examplerm.rmdemo.repositories.ILibraryRepository;
import com.examplerm.rmdemo.services.interfaces.ILibraryService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.examplerm.rmdemo.controllers.dtos.response.GetLibraryResponse;

@Service
public class LibraryServiceImpl implements ILibraryService {

    @Autowired
    private ILibraryRepository repository;

    @Override
    public Library create() {
        Library library = new Library();
        return repository.save(library);
    }

    @Override
    public BaseResponse get(Long id) {
        GetLibraryResponse response=from(id);
        return BaseResponse.builder()
                .data(response)
                .message("Library founded")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private GetLibraryResponse from(Library library) {
        GetLibraryResponse response = new GetLibraryResponse();
        response.setId(library.getId());
        return response;
    }

    private GetLibraryResponse from(Long id) {
        return repository.findById(id)
                .map(this::from)
                .orElseThrow(() -> new RuntimeException("The user does not exist"));
    }

    @Override
    public Library findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("The user does not exist"));
    }

    @Override
    public BaseResponse list() {
        List<GetLibraryResponse> response=repository.findAll()
            .stream()
            .map(this::from)
            .collect(Collectors.toList());             
       
            return BaseResponse.builder()
            .data(response)
            .message("Libraries founded")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK)
            .build();
    }
    
}
