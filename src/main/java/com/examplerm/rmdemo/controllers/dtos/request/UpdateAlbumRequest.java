package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UpdateAlbumRequest {

    private String name;

    private String duration;

    private String dateCreation;

    private String discography;

    private String description;
}
