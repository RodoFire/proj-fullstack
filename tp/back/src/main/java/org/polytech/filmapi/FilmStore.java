package org.polytech.filmapi;

import java.util.Collection;

public interface FilmStore {

    Collection<Film> getAll();

    Film get(long id) throws FilmNotFoundException;

    void save(Film film);

    boolean delete(long id);

    long create(FilmView film);
}
