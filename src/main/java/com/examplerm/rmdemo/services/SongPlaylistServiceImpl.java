package com.examplerm.rmdemo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.controllers.dtos.response.SongResponse;
import com.examplerm.rmdemo.entities.projections.SongProjection;
import com.examplerm.rmdemo.repositories.ISongPlaylistRepository;
import com.examplerm.rmdemo.services.interfaces.ISongPlaylistService;

public class SongPlaylistServiceImpl implements ISongPlaylistService{

    @Autowired
    private ISongPlaylistRepository repository;

    @Override
    public BaseResponse listAllSongByIdPlaylist(Long playlistId) {
        List<SongProjection> songs= repository.listAllSongByIdPlaylist(playlistId);
        List<SongResponse> response=songs.stream()
            .map(this::from)
            .collect(Collectors.toList());
        return BaseResponse.builder()
            .data(response)
            .message("Song list by playlist id")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void deleteSongsByIdPlaylist(Long playlistId) {
        repository.deleteSongsByIdPlaylist(playlistId);        
    }

    private SongResponse from(SongProjection song){
        SongResponse response= new SongResponse();
        response.setId(song.getId());
        response.setName(song.getName());
        response.setCategory(song.getCategory());
        response.setCreationDate(song.getCreationDate());
        response.setDuration(song.getDuration());
        response.setAlbumId(song.getAlbumId());
        response.setArtistId(song.getArtistId());
        return response;
    }
    
}
