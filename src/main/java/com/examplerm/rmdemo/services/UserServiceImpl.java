package com.examplerm.rmdemo.services;

import com.examplerm.rmdemo.controllers.dtos.request.CreateUserRequest;
import com.examplerm.rmdemo.controllers.dtos.request.LoginRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateUserRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.controllers.dtos.response.GetUserResponse;
import com.examplerm.rmdemo.controllers.dtos.response.LibraryResponse;
import com.examplerm.rmdemo.entities.Library;
import com.examplerm.rmdemo.entities.User;
import com.examplerm.rmdemo.repositories.IUserRepository;
import com.examplerm.rmdemo.services.interfaces.IFileService;
import com.examplerm.rmdemo.services.interfaces.ILibraryService;
import com.examplerm.rmdemo.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IFileService fileService;

    @Autowired
    private IUserRepository repository;

    @Autowired
    private ILibraryService libraryService;

    @Override
    public BaseResponse login(LoginRequest request) {
        GetUserResponse response=from(request);
        return BaseResponse.builder()
                .data(response)
                .message("You are Logged in")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }
    
    @Override
    public BaseResponse get(String name) {
        GetUserResponse response=from(name);
        return BaseResponse.builder()
                .data(response)
                .message("User has been found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public BaseResponse create(CreateUserRequest request) {
        Library library=libraryService.create();
        User user = from(request,library);
        return BaseResponse.builder()
                .data(from(repository.save(user)))
                .message("User created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse update(Long id, UpdateUserRequest request) {

        User user = repository.findById(id)
            .orElseThrow(()-> new RuntimeException("The User does not exist"));
        user = update(user, request);
        GetUserResponse response=from(user);

        return BaseResponse.builder()
                .data(response)
                .message("The user has been updated")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public void delete(Long id) {
        User user=findById(id);
        repository.deleteById(id);
        libraryService.delete(user.getLibrary().getId());
    }

    private User update(User user, UpdateUserRequest request) {
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setPhotoUrl(request.getPhotoUrl());
        return repository.save(user);
    }

    private User from(CreateUserRequest request,Library library) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhotoUrl(request.getPhotoUrl());
        user.setLibrary(library);
        user.setAdmin(request.getAdmin());
        return user;
    }

    private GetUserResponse from(User user) {
        GetUserResponse response = new GetUserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setLibrary(from(user.getLibrary()));
        response.setPhotoUrl(user.getPhotoUrl());
        response.setAdmin(user.getAdmin());
        return response;
    }

    private LibraryResponse from(Library library){
        LibraryResponse response=new LibraryResponse();
        response.setId(library.getId());
        return response;
    }

    private GetUserResponse from(LoginRequest request) {
        String name=request.getName();
        String password=request.getPassword();
        return repository.findByNameAndPassword(name, password)
                .map(this::from)
                .orElseThrow(() -> new RuntimeException("Incorrect sesion"));
    }



    private GetUserResponse from(String name) {
        return repository.findByName(name)
                .map(this::from)
                .orElseThrow(() -> new RuntimeException("The user does not exist"));
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("The user does not exist"));
    }

    @Override
    public User findByName(String name) {
        return repository.findByName(name)
            .orElseThrow(() -> new RuntimeException("The user does not exist"));
    }

    @Override
    public BaseResponse uploadPhoto(MultipartFile file){
        String photoUrl= fileService.upload(file);
        return BaseResponse.builder()
                .data(photoUrl)
                .message("The photo of user uploaded correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
    }
}
