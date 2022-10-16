package com.examplerm.rmdemo.services;

import com.examplerm.rmdemo.controllers.dtos.request.CreateSongRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateSongRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.controllers.dtos.response.GetSongResponse;
import com.examplerm.rmdemo.entities.Song;
import com.examplerm.rmdemo.repositories.ISongRepository;
import com.examplerm.rmdemo.services.interfaces.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements ISongService {

    @Autowired
    private ISongRepository repository;

    @Override
    public BaseResponse get(Long id) {
        GetSongResponse response=from(id);

        return BaseResponse.builder()
                .data(response)
                .message("Song has been found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateSongRequest request) {
        Song song = from(request);
        return BaseResponse.builder()
                .data(from(repository.save(song)))
                .message("Song created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
    }
    @Override
    public BaseResponse list() {
        List<GetSongResponse> response= repository.findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());

        return BaseResponse.builder()
                .data(response)
                .message("Songs have been found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdateSongRequest request) {
        Song song = findOneAndEnsureExist(id);
        song = update(song, request);
        GetSongResponse response=from(song);

        return BaseResponse.builder()
                .data(response)
                .message("Song has been updated")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public Song findOneAndEnsureExist(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("The song does not exist"));
    }
    @Override
    public void delete(Long id) {

        repository.deleteById(id);

    }
    @Override
    public Song save(Song song) {
        return repository.save(song);
    }


    private Song update(Song song, UpdateSongRequest request) {
        song.setName(request.getName());
        song.setDuration(request.getDuration());
        return repository.save(song);
    }

    private Song from(CreateSongRequest request) {

        Song song = new Song();
        song.setName(request.getName());
        song.setDuration(request.getDuration());
        return song;
    }

    private GetSongResponse from(Song song) {
        GetSongResponse response = new GetSongResponse();
        response.setId(song.getId());
        response.setName(song.getName());
        response.setDuration(song.getDuration());
        return response;
    }

    private GetSongResponse from(Long idSong) {
        return repository.findById(idSong)
                .map(this::from)
                .orElseThrow(() -> new RuntimeException("The song does not exist"));
    }

}
