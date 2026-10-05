package org.polytech.filmapi.dto;

import org.polytech.filmapi.Film;

import java.time.LocalDate;

public record FilmCreationDTO(
        String titre, String realisateur,
        LocalDate dateSortie, Film.FilmType genre
) {
}
