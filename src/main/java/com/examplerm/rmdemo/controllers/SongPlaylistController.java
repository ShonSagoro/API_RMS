package com.examplerm.rmdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examplerm.rmdemo.controllers.dtos.request.CreateSongPlaylistRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.services.interfaces.ISongPlaylistService;

@RestController
@RequestMapping("song-playlist")
public class SongPlaylistController {

    @Autowired
    private ISongPlaylistService service;

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateSongPlaylistRequest request){
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("songs/playlist/{playlistId}")
    public ResponseEntity<BaseResponse> listAllSongsByIdPlaylist(@PathVariable Long playlistId){
        BaseResponse baseResponse= service.listAllSongsByIdPlaylist(playlistId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }
    @GetMapping("playlists/song/{songId}")
    public ResponseEntity<BaseResponse> listAllPlaylistByIdSong(@PathVariable Long songId){
        BaseResponse baseResponse= service.listAllPlaylistByIdSong(songId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("songs/playlist/{playlistId}")
    public void deleteSongsByIdPlaylist(@PathVariable Long playlistId){
        service.deleteSongsByIdPlaylist(playlistId);
    }
    @DeleteMapping("song/{songId}/playlist/{playlistId}")
    public void deleteSongsByIdPlaylist(@PathVariable Long songId, @PathVariable Long playlistId){
        service.deleteSongFromUserByTheirIds(songId, playlistId);
    }

}
