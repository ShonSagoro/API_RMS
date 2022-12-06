package com.examplerm.rmdemo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.Artist;

@Repository
public interface IArtistRepository extends JpaRepository<Artist, Long> {
    @Query(value = "SELECT * FROM artists WHERE name=:name", nativeQuery = true)
    Optional<Artist> findByName(String name);
}
