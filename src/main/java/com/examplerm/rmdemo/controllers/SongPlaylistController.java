package com.examplerm.rmdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.services.interfaces.ISongPlaylistService;

@RestController
@RequestMapping("song-playlist")
public class SongPlaylistController {

    @Autowired
    private ISongPlaylistService service;

    @GetMapping("songs/playlist/{playlistId}")
    public ResponseEntity<BaseResponse> listAllSongByIdPlaylist(@PathVariable Long playlistId){
        BaseResponse baseResponse= service.listAllSongByIdPlaylist(playlistId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("songs/playlist/{playlistId}")
    public void deleteSongsByIdPlaylist(@PathVariable Long playlistId){
        service.deleteSongsByIdPlaylist(playlistId);
    }    

    @DeleteMapping("song/{songId}/playlist/{playlistId}")
    public void deleteSongByIdSongAndPlaylist(@PathVariable Long songId,@PathVariable Long playlistId){
        service.deleteSongByIdSongAndPlaylist(songId, playlistId);
    }
}
