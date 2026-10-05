package org.polytech.filmapi.dto;

import org.polytech.filmapi.Film;

import java.time.LocalDate;

public record FilmDTO(
        long id, String titre, String realisateur,
        LocalDate dateSortie, Film.FilmType genre
) {
}
