package com.examplerm.rmdemo.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetPodcastResponse {

    private Long id;

    private String name;

    private String description;

    private String category;

    private String creationDate;

    private String photoUrl;

}
