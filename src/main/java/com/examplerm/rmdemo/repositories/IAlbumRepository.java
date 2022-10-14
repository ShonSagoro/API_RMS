package com.examplerm.rmdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examplerm.rmdemo.entities.Album;

public interface IAlbumRepository extends JpaRepository<Album, Long> {

}
