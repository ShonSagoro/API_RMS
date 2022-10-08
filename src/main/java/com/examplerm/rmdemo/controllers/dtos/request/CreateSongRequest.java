package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
public class CreateSongRequest {
    @NonNull
    private String name;
    @NonNull
    private String duration;
    @NonNull
    private String ArtistName;
    private String Album;
}
