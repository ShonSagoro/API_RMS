package com.examplerm.rmdemo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.examplerm.rmdemo.entities.pivots.AlbumLibrary;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="albums")
@Setter @Getter
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String duration;
    
    private String description;
    
    @Column(nullable = false)
    private String dateCreation;
    
    @ManyToOne
    private Artist artist;
    
    @OneToMany(mappedBy = "album")  
    private List<AlbumLibrary> album_library;

    @OneToMany(mappedBy = "album")
    private List<Song> songs;
    
}
