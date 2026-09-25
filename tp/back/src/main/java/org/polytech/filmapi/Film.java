package org.polytech.filmapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
@Setter
public class Film {

    private final long id;
    private String titre;
    private String realisateur;
    private LocalDate dateSortie;
    private FilmType genre;

    public static Film fromView(long id, FilmView view) {
        return new Film(id, view.titre(), view.realisateur(), view.dateSortie(), view.genre());
    }

    public enum FilmType {
        COMEDY,
        DRAMA,
        ACTION,
        SCIENCE_FICTION,
        FANTASY
    }
}
