package com.examplerm.rmdemo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.examplerm.rmdemo.entities.pivots.Artist_Library;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "artists")
@Getter @Setter
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String listener;
    
    @Column(nullable = false)
    private String discography;

    @OneToMany(mappedBy = "artist")  
    private List<Artist_Library> artist_Libraries; 

    @OneToMany(mappedBy = "artist")
    private List<Song> songs;

    @OneToMany(mappedBy = "artist")
    private List<Album> album;

    
    
}
