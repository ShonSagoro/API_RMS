package com.examplerm.rmdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.Album;

@Repository
public interface IAlbumRepository extends JpaRepository<Album, Long> {

}
