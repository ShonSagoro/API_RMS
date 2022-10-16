package com.examplerm.rmdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examplerm.rmdemo.entities.Artist;

@Repository
public interface IArtistRepository extends JpaRepository<Artist, Long> {

}
