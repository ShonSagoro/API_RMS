package com.examplerm.rmdemo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.Playlist;

@Repository
public interface IPlaylistRepository extends JpaRepository<Playlist,Long> {
    @Query(value = "SELECT * FROM playlists WHERE name=:name", nativeQuery = true)
    Optional<Playlist> findByName(String name);
}
