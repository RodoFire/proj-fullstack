package org.polytech.filmapi;

import java.time.LocalDate;

public record FilmView(
        String titre, String realisateur,
        LocalDate dateSortie, Film.FilmType genre
) {
}
