package org.polytech.filmapi.dto;

import org.polytech.filmapi.Film;

import java.time.LocalDate;

/** Corps du POST /api/films. Les acteurs s'associent ensuite via POST /api/films/{id}/acteurs/{acteurId}. */
public record FilmCreationDTO(
        String titre, String realisateur,
        LocalDate dateSortie, Film.FilmType genre
) {
}
