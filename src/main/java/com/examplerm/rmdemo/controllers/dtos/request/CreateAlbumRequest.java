package com.examplerm.rmdemo.controllers.dtos.request;


import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CreateAlbumRequest {

    private String name;

    private String duration;

    private String description;

    private Long idArtist;

    private String photoUrl;

}
