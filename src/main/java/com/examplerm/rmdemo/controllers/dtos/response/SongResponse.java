package com.examplerm.rmdemo.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SongResponse {
    private Long id;

    private String name;

    private String duration;

    private String category;

    private String creationDate;

    private Long albumId;

    private Long artistId;
}
