package com.examplerm.rmdemo.repositories;

import com.examplerm.rmdemo.entities.Album;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlbumRepository extends JpaRepository<Album,Long> {

    @Query(value = "SELECT * FROM albums WHERE name=:name", nativeQuery = true)
    Optional<Album> findByName(String name);
}
