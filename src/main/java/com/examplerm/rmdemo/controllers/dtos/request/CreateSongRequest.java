package com.examplerm.rmdemo.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CreateSongRequest {
   private String name;
   private String duration;
}