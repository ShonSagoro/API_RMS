package com.examplerm.rmdemo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.examplerm.rmdemo.entities.pivots.Podcast_Library;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "podcasts")
@Getter @Setter
public class Podcast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String creationDate;

    @OneToMany(mappedBy = "podcast")  
    private List<Podcast_Library> podcast_Libraries; 

    @OneToMany(mappedBy = "podcast")
    private List<Chapter> chapters;
}
