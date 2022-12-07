package com.examplerm.rmdemo.controllers;

import com.amazonaws.services.kms.model.DisabledException;
import com.examplerm.rmdemo.controllers.dtos.request.CreateUserRequest;
import com.examplerm.rmdemo.controllers.dtos.request.LoginRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateUserRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.controllers.dtos.response.LoginResponse;
import com.examplerm.rmdemo.security.TokenUtils;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.examplerm.rmdemo.services.interfaces.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService service;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("register")
    public ResponseEntity<BaseResponse> create(@RequestBody CreateUserRequest request) {
        BaseResponse baseResponse= service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());

    }

    @PostMapping("upload/photo")
    public ResponseEntity<BaseResponse> uploadPhoto(@RequestParam MultipartFile file){
        BaseResponse baseResponse= service.uploadPhoto(file);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());

    }

    @PostMapping("login")
    public ResponseEntity<BaseResponse> login(@RequestBody LoginRequest request){
        BaseResponse baseResponse= service.login(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());

    }

    @GetMapping("name/{name}")
    public ResponseEntity<BaseResponse> get(@PathVariable String name){
        BaseResponse baseResponse= service.get(name);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse>  update(@RequestBody UpdateUserRequest request, @PathVariable Long id){
        BaseResponse baseResponse= service.update(id,request);
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


    @PostMapping("/login/token-generated")
    public ResponseEntity<?> generarToken(@RequestBody LoginRequest loginRequest) throws Exception {
        try{
            autenticar(loginRequest.getEmail(),loginRequest.getPassword());
        }catch (Exception exception){
            exception.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }

        UserDetails userDetails =  this.userDetailsService.loadUserByUsername(loginRequest.getEmail());
        String token = TokenUtils.createToken(userDetails.getUsername(), userDetails.getPassword());
        return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);
    }

    private void autenticar(String username,String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException exception){
            throw  new Exception("USUARIO DESHABILITADO " + exception.getMessage());
        }catch (BadCredentialsException e){
            throw  new Exception("Credenciales invalidas " + e.getMessage());
        }
    }

}
