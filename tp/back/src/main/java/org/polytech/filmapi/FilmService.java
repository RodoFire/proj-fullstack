package org.polytech.filmapi;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FilmService {

    private final FilmStore store;

    public FilmService(FilmStore store) {
        this.store = store;
    }

    Collection<Film> getAll() {
        return store.getAll();
    }

    Film get(long id) throws FilmNotFoundException {
        return store.get(id);
    }

    void save(Film film) {
        store.save(film);
    }

    boolean delete(long id) {
        return store.delete(id);
    }

    public long createNewFilm(FilmView film) {
        if (film.titre() == null || film.titre().isEmpty()) return -1;
        if (film.realisateur() == null || film.realisateur().isEmpty()) return -1;
        if (film.dateSortie() == null) return -1;
        if (film.genre() == null) return -1;

        return store.create(film);
    }

    public void updateFilm(FilmView film, long id) {
        store.update(film, id);
    }
}
