package com.examplerm.rmdemo.entities.projections;

public interface SongProjection {
    Long getId();

    String getName();

    String getDuration();

    String getCategory();

    String getCreationDate();

    Long getAlbumId();
    
    Long getArtistId();
}
