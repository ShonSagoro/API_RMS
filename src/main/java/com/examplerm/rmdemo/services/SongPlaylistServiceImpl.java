package com.examplerm.rmdemo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.examplerm.rmdemo.controllers.dtos.request.CreateSongPlaylistRequest;
import com.examplerm.rmdemo.controllers.dtos.response.AlbumResponse;
import com.examplerm.rmdemo.controllers.dtos.response.ArtistResponse;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.controllers.dtos.response.GetSongPlaylistResponse;
import com.examplerm.rmdemo.controllers.dtos.response.PlaylistResponse;
import com.examplerm.rmdemo.controllers.dtos.response.SongResponse;
import com.examplerm.rmdemo.entities.Album;
import com.examplerm.rmdemo.entities.Artist;
import com.examplerm.rmdemo.entities.Playlist;
import com.examplerm.rmdemo.entities.Song;
import com.examplerm.rmdemo.entities.pivots.SongPlaylist;
import com.examplerm.rmdemo.entities.projections.PlaylistProjection;
import com.examplerm.rmdemo.entities.projections.SongProjection;
import com.examplerm.rmdemo.repositories.ISongPlaylistRepository;
import com.examplerm.rmdemo.services.interfaces.IPlaylistService;
import com.examplerm.rmdemo.services.interfaces.ISongPlaylistService;
import com.examplerm.rmdemo.services.interfaces.ISongService;


@Service
public class SongPlaylistServiceImpl implements ISongPlaylistService{

    @Autowired
    private ISongPlaylistRepository repository;

    @Autowired
    private ISongService songService;

    @Autowired
    private IPlaylistService playlistService;

    @Override
    public BaseResponse create(CreateSongPlaylistRequest request) {
        SongPlaylist songPlaylist= from(request);
        GetSongPlaylistResponse response = from(repository.save(songPlaylist));
        return BaseResponse.builder()
            .data(response)
            .message("Relation between Song and Playlist has been created correctly")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.CREATED).build();
    }

    private GetSongPlaylistResponse from(SongPlaylist songPlaylist){
        GetSongPlaylistResponse response= new GetSongPlaylistResponse();
        response.setId(songPlaylist.getId());
        response.setPlaylist(from(songPlaylist.getPlaylist()));
        response.setSong(from(songPlaylist.getSong()));
        return response;

    }

    private SongPlaylist from(CreateSongPlaylistRequest request){
        SongPlaylist songPlaylist= new SongPlaylist();
        songPlaylist.setPlaylist(playlistService.findById(request.getIdPlaylist()));
        songPlaylist.setSong(songService.findById(request.getIdSong()));
        return songPlaylist;
    }

    private PlaylistResponse from(Playlist playlist){
        PlaylistResponse response= new PlaylistResponse();
        response.setId(playlist.getId());
        response.setName(playlist.getName());
        response.setCreationDate(playlist.getDateCreation());
        response.setDescription(playlist.getDescription());
        return response;
    }

    private SongResponse from(Song song){
        SongResponse response = new SongResponse();
        response.setId(song.getId());
        response.setName(song.getName());
        response.setDuration(song.getDuration());
        response.setSongUrl(song.getSongUrl());
        response.setAlbum(from(song.getAlbum()));
        response.setArtist(from(song.getArtist()));
        return response;
    }
    
    private AlbumResponse from(Album album){
        AlbumResponse response= new AlbumResponse();
        response.setId(album.getId());
        response.setName(album.getName());
        response.setDuration(album.getDuration());
        response.setCreationDate(album.getCreationDate());
        response.setDescription(album.getDescription());
        response.setArtist(from(album.getArtist()));
        return response;

    }
    
    private ArtistResponse from(Artist artist){
        ArtistResponse response= new ArtistResponse();
        response.setId(artist.getId());
        response.setName(artist.getName());
        response.setListener(artist.getListener());
        return response;
    }

    @Override
    public BaseResponse listAllSongByIdPlaylist(Long playlistId) {
        List<SongProjection> songs= repository.listAllSongByIdPlaylist(playlistId);
        List<SongResponse> response=songs.stream()
            .map(this::from)
            .collect(Collectors.toList());
        return BaseResponse.builder()
            .data(response)
            .message("Songs list by playlist id")
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
        response.setCreationDate(song.getCreationDate());
        response.setDuration(song.getDuration());
        response.setSongUrl(song.getSongUrl());
        response.setAlbum(from(song.getAlbum()));
        response.setArtist(from(song.getArtist()));
        return response;
    }

    @Override
<<<<<<< HEAD
    public void deleteSongByIdSongAndPlaylist(Long songId, Long playlistId) {
        repository.deleteSongByIdSongAndPlaylist(songId, playlistId);   
    }

   
    
=======
    public BaseResponse listAllPlaylistByIdSong(Long songId) {
        List<PlaylistProjection> playlist= repository.listAllPlaylistByIdSong(songId);
        List<PlaylistResponse> response= playlist.stream()
        .map(this::from)
        .collect(Collectors.toList());
        return BaseResponse.builder()
         .data(response)
            .message("Playlists list by song id")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    private PlaylistResponse from(PlaylistProjection playlist){
        PlaylistResponse response= new PlaylistResponse();
        response.setId(playlist.getId());
        response.setName(playlist.getName());
        response.setDuration(playlist.getDuration());
        response.setDescription(playlist.getDescription());
        response.setCreationDate(playlist.getCreationDate());
        return response;
    }
    @Override
    public void deleteSongFromUserByTheirIds(Long songId, Long playlistId) {
        repository.deleteSongFromUserByTheirIds(songId, playlistId);         
    }

>>>>>>> kari
}
