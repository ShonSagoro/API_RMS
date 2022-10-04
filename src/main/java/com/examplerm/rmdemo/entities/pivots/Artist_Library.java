package com.examplerm.rmdemo.entities.pivots;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.examplerm.rmdemo.entities.Artist;
import com.examplerm.rmdemo.entities.Library;

@Entity
@Table(name = "artist_library")
public class Artist_Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Artist artist;

    @ManyToOne
    private Library library;
}

