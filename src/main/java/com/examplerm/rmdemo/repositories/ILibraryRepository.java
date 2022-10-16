package com.examplerm.rmdemo.repositories;

import com.examplerm.rmdemo.entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILibraryRepository extends JpaRepository<Library,Long> {
}
