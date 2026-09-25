package org.polytech.filmapi;

public class FilmNotFoundException extends RuntimeException {
    public FilmNotFoundException(long id) {
        super("Le film avec l'id " + id + " n'a pas été trouvé ");
    }
}
