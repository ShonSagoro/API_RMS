package com.examplerm.rmdemo.services;

import com.examplerm.rmdemo.controllers.dtos.request.CreateUserRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateUserRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.controllers.dtos.response.GetUserResponse;
import com.examplerm.rmdemo.controllers.dtos.response.LibraryResponse;
import com.examplerm.rmdemo.entities.Library;
import com.examplerm.rmdemo.entities.User;
import com.examplerm.rmdemo.repositories.IUserRepository;
import com.examplerm.rmdemo.services.interfaces.ILibraryService;
import com.examplerm.rmdemo.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Autowired
    private ILibraryService libraryService;

    @Override
    public BaseResponse get(Long id) {
        GetUserResponse response=from(id);
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
        return repository.save(user);
    }

    private User from(CreateUserRequest request,Library library) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setLibrary(library);
        return user;
    }

    private GetUserResponse from(User user) {
        GetUserResponse response = new GetUserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setLibrary(from(user.getLibrary()));
        return response;
    }

    private LibraryResponse from(Library library){
        LibraryResponse response=new LibraryResponse();
        response.setId(library.getId());
        return response;
    }

    private GetUserResponse from(Long idUser) {
        return repository.findById(idUser)
                .map(this::from)
                .orElseThrow(() -> new RuntimeException("The user does not exist"));
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("The user does not exist"));
    }
}
