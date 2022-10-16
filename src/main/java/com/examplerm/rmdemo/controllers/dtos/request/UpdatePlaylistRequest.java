package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdatePlaylistRequest {

    private String name;

    private String description;

    private String duration;

}
