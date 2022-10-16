package com.examplerm.rmdemo.repositories;

import com.examplerm.rmdemo.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlbumRepository extends JpaRepository<Album,Long> {
}
