package org.polytech.filmapi;

import lombok.AllArgsConstructor;
import org.polytech.filmapi.dto.FilmDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class FilmService {

    private final FilmRepository filmRepo;
    private final ActeurRepository acteurRepo;

    Collection<Film> getAll() {
        return filmRepo.findAll();
    }

    Film get(long id) throws FilmNotFoundException {
        return filmRepo.findById(id)
                .orElseThrow(() -> new FilmNotFoundException(id));
    }

    void save(Film film) {
        filmRepo.save(film);
    }

    void delete(long id) {
        filmRepo.delete(get(id));
    }

    public long createNewFilm(Film film) {
        if (film.titre() == null || film.titre().isEmpty()) return -1;
        if (film.realisateur() == null || film.realisateur().isEmpty()) return -1;
        if (film.dateSortie() == null) return -1;
        if (film.genre() == null) return -1;

        filmRepo.save(film);

        return film.id();
    }

    @Transactional(readOnly = true)
    public List<Acteur> getActeurs(long filmId) {
        return get(filmId).acteurs();
    }

    @Transactional
    public void addActeur(long filmId, long acteurId) {
        Film film = get(filmId);
        film.addActeur(getActeur(acteurId));
        filmRepo.save(film);
    }

    @Transactional
    public void removeActeur(long filmId, long acteurId) {
        Film film = get(filmId);
        film.removeActeur(getActeur(acteurId));
        filmRepo.save(film);
    }

    private Acteur getActeur(long id) {
        return acteurRepo.findById(id)
                .orElseThrow(() -> new ActeurNotFoundException(id));
    }

    public List<Film> getByGenre(Film.FilmType genre) {
        return filmRepo.findByGenre(genre);
    }
}
