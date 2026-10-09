package org.polytech.filmapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query("""
            select f from Film f
            where f.genre = :genre
            """)
    List<Film> findByGenre(@Param("genre") Film.FilmType genre);

}
