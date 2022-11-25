package com.examplerm.rmdemo.controllers.dtos.request;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter @Getter
public class CreateAlbumRequest {

    @NonNull
    private String name;

    @NonNull
    private String duration;

    private String description;
    
    @NonNull
    private Long idArtist;

    private String photoUrl;

}
