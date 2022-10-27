package com.examplerm.rmdemo.entities.pivots;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.examplerm.rmdemo.entities.Library;
import com.examplerm.rmdemo.entities.Podcast;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "podcast_library")
@Getter @Setter
public class PodcastLibrary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne 
    private Podcast podcast;

    @ManyToOne
    private Library library;
}
