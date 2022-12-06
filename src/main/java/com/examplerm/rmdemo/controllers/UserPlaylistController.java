package com.examplerm.rmdemo.controllers;

import com.examplerm.rmdemo.controllers.dtos.request.CreateUserPlaylistRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.services.interfaces.IUserPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user-playlist")
public class UserPlaylistController {

    @Autowired
    private IUserPlaylistService service;

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateUserPlaylistRequest request){
        BaseResponse baseResponse= service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("playlists/user/{userId}")
    public ResponseEntity<BaseResponse> listAllPlaylistsByUserId(@PathVariable Long userId){
        BaseResponse baseResponse=service.listAllPlaylistsByUserId(userId);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping("users/playlist/{playlistId}")
    public ResponseEntity<BaseResponse> listAllUsersByPlaylistId(@PathVariable Long playlistId){
        BaseResponse baseResponse=service.listAllUsersByPlaylistId(playlistId);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @DeleteMapping("playlists/{playlistId}/user/{userId}")
    public void deletePlaylistFromUserByThierIds(@PathVariable Long playlistId,@PathVariable Long userId){
        service.deletePlaylistFromUserByThierIds(playlistId,userId);
    }
    @DeleteMapping("playlists/user/{userId}")
    public void deletePlaylistsByIdUser(@PathVariable Long userId){
        service.deletePlaylistsByIdUser(userId);
    }
    @GetMapping("health")
    public String health() {
        return "Ok";
    }

}
