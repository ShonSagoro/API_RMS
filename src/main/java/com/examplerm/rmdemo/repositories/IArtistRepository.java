package com.examplerm.rmdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examplerm.rmdemo.entities.Artist;

public interface IArtistRepository extends JpaRepository<Artist, Long> {

}
