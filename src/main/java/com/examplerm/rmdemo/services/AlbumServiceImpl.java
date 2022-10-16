package com.examplerm.rmdemo.services;

import com.examplerm.rmdemo.controllers.dtos.request.CreateAlbumRequest;
import com.examplerm.rmdemo.controllers.dtos.request.UpdateAlbumRequest;
import com.examplerm.rmdemo.controllers.dtos.response.BaseResponse;
import com.examplerm.rmdemo.controllers.dtos.response.GetAlbumResponse;
import com.examplerm.rmdemo.entities.Album;
import com.examplerm.rmdemo.repositories.IAlbumRepository;
import com.examplerm.rmdemo.services.interfaces.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements IAlbumService {

    @Autowired
    private IAlbumRepository repository;

    @Override
    public BaseResponse get(Long id) {
        GetAlbumResponse response=from(id);
        return BaseResponse.builder()
                .data(response)
                .message("Album has been getted")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();

    }
    @Override
    public BaseResponse create(CreateAlbumRequest request) {

        Album album = from(request);
        return BaseResponse.builder()
                .data(from(repository.save(album)))
                .message("Album created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse update(Long id, UpdateAlbumRequest request) {
        Album album = findOneAndEnsureExist(id);
        album = update(album, request);
        GetAlbumResponse response=from(album);

        return BaseResponse.builder()
                .data(response)
                .message("Album has been updated correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }
    @Override
    public BaseResponse list() {
        List<GetAlbumResponse> response= repository.findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());

        return BaseResponse.builder()
                .data(response)
                .message("Albums have been getted")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public Album findOneAndEnsureExist(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("The album does not exist"));
    }
    @Override
    public void delete(Long id) {

        repository.deleteById(id);

    }
    @Override
    public Album save(Album album) {
        return repository.save(album);
    }


    private Album update(Album album, UpdateAlbumRequest request) {
        album.setName(request.getName());
        album.setDiscography(request.getDiscography());
        album.setDateCreation(request.getDateCreation());
        album.setDescription(request.getDescription());
        album.setDuration(request.getDuration());
        return repository.save(album);
    }

    private Album from(CreateAlbumRequest request) {

        Album album = new Album();
        album.setName(request.getName());
        album.setDiscography(request.getDiscography());
        album.setDescription(request.getDescription());
        album.setDateCreation(request.getDateCreation());
        album.setDuration(request.getDuration());
        return repository.save(album);
    }

    private GetAlbumResponse from(Album album) {
        GetAlbumResponse response = new GetAlbumResponse();
        response.setId(album.getId());
        response.setName(album.getName());
        response.setDuration(album.getDuration());
        response.setDateCreation(album.getDateCreation());
        response.setDescription(album.getDescription());
        response.setDiscography(album.getDiscography());
        return response;
    }

    private GetAlbumResponse from(Long idAlbum) {
        return repository.findById(idAlbum)
                .map(this::from)
                .orElseThrow(() -> new RuntimeException("The album does not exist"));
    }

}
