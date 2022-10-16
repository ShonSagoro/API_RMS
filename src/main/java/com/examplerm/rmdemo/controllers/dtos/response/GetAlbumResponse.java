package com.examplerm.rmdemo.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class GetAlbumResponse {

    private Long id;

    private String name;

    private String duration;

    private String dateCreation;

    private String discography;

    private String description;
}
