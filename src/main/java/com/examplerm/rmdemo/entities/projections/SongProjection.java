package com.examplerm.rmdemo.entities.projections;

public interface SongProjection {
    
    Long getId();
    
    String getCreation_Date();
    
    String getDuration();
    
    String getName();
    
    String getSong_Url();
    
    Long getArtist_Id();
    
    Long getAlbum_Id();

    String getPhoto_Url();

}
