package com.examplerm.rmdemo.repositories;

import com.examplerm.rmdemo.entities.pivots.ArtistLibrary;
import com.examplerm.rmdemo.entities.projections.ArtistProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArtistLibraryRepository extends JpaRepository<ArtistLibrary,Long> {
    @Query(value = "select artists.* from artist_library " +
            "inner join artists on artist_library.artist_id = artists.id " +
            "inner join libraries on artist_library.library_id = libraries.id " +
            "where artist_library.library_id = :libraryId", nativeQuery = true)
    List<ArtistProjection> listAllArtistsByIdLibrary(Long libraryId);


    @Query(value = "DELETE FROM artist_library WHERE library_id= :libraryId AND artist_id= :artistId", nativeQuery= true)
    void deleteArtistbyIdUserId(Long libraryId, Long artistId);
}
