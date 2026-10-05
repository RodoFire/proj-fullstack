package org.polytech.filmapi.dto;

import org.polytech.filmapi.Film;

import java.time.LocalDate;
import java.util.List;

public record FilmDetailDTO(
        long id, String titre, String realisateur,
        LocalDate dateSortie, Film.FilmType genre,
        List<ActeurDTO> acteurs
) {
}
