package com.examplerm.rmdemo.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.examplerm.rmdemo.entities.pivots.AlbumLibrary;
import com.examplerm.rmdemo.entities.pivots.ArtistLibrary;
import com.examplerm.rmdemo.entities.pivots.PlaylistLibrary;
import com.examplerm.rmdemo.entities.pivots.PodcastLibrary;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
@Table(name = "libraries")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "library")  
    private List<AlbumLibrary> album_library;

    @OneToMany(mappedBy = "library")  
    private List<ArtistLibrary> artist_Libraries; 

    @OneToMany(mappedBy = "library")  
    private List<PodcastLibrary> podcast_Libraries; 

    @OneToMany(mappedBy = "library")  
    private List<PlaylistLibrary> playlist_Libraries;
   
    @OneToOne(mappedBy = "library")
    private User user;
}
