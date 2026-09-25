package org.polytech.filmapi;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Primary
public class FilmDatabase implements FilmStore {

    private final Map<Long, Film> films = new HashMap<>();
    private long lastId = 0;

    @PostConstruct
    public void init() {
        FilmView lotr = new FilmView(
                "Le seigneur des anneaux - La communauté de l'anneau", "Peter Jackson", LocalDate.of(2001, 12, 10), Film.FilmType.FANTASY
        );
        FilmView vbt = new FilmView(
                "Very Bad Trip", "Todd Philips", LocalDate.of(2009, 6, 24), Film.FilmType.COMEDY
        );
        create(lotr);
        create(vbt);
    }

    @Override
    public Collection<Film> getAll() {
        return films.values();
    }

    @Override
    public Film get(long id) throws FilmNotFoundException {
        if (!films.containsKey(id)) throw new FilmNotFoundException(id);
        return films.get(id);
    }

    @Override
    public void save(Film film) {
        this.films.put(film.id(), film);
    }

    @Override
    public boolean delete(long id) {
        if (!films.containsKey(id)) return false;
        films.remove(id);
        return true;
    }

    @Override
    public long create(FilmView view) {
        Film film = Film.fromView(lastId, view);
        films.put(film.id(), film);
        return lastId++;
    }
}
