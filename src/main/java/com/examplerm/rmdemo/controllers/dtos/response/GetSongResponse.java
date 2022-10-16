package com.examplerm.rmdemo.controllers.dtos.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
public class GetSongResponse {
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String duration;

}
