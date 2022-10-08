package com.examplerm.rmdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examplerm.rmdemo.entities.Song;

public interface ISongRepostitory extends JpaRepository<Song, Long>{
    //aqui iran los Joins, si es que hacemos algun Join
}
