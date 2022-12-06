package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter @Getter
public class CreateArtistRequest {

    @NonNull
    private String name;

    private String photoUrl;


}
