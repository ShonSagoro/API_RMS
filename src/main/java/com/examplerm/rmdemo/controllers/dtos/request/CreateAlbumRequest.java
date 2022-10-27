package com.examplerm.rmdemo.controllers.dtos.request;


import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CreateAlbumRequest {
    private String name;

    private String duration;

    private String description;

    private Long idArtist;
}
