package org.polytech.filmapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public class Film {

    private final long id;
    private String titre;
    private String realisateur;
    private LocalDate dateSortie;
    private FilmType genre;

    public static Film fromView(long id, FilmView view) {
        return new Film(id, view.titre(), view.realisateur(), view.dateSortie(), view.genre());
    }

    public Film updateFromView(FilmView view) {
        if (view.titre() != null) this.titre = view.titre();
        if (view.realisateur() != null) this.realisateur = view.realisateur();
        if (view.dateSortie() != null) this.dateSortie = view.dateSortie();
        if (view.genre() != null) this.genre = view.genre();
        return this;
    }

    public enum FilmType {
        COMEDY,
        DRAMA,
        ACTION,
        SCIENCE_FICTION,
        FANTASY
    }
}
