package org.polytech.filmapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query("""
            select f from Film f
            where f.genre = :genre
            """)
    List<Film> findByGenre(@Param("genre") Film.FilmType genre);

    @Query("""
            select f from Film f
            where lower(f.titre) like lower(concat('%', :titre, '%'))
            """)
    List<Film> findByTitre(@Param("titre") String titre);

    @Query("""
            select f from Film f
            where lower(f.titre) like lower(concat('%', :titre, '%'))
                        and f.genre = :genre
            """)
    List<Film> findByTitreNByGenre(@Param("titre") String titre, @Param("genre") Film.FilmType genre);

}
