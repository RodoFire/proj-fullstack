package org.polytech.filmapi;

public class ActeurNotFoundException extends RuntimeException {
    public ActeurNotFoundException(long id) {
        super("L'acteur avec l'id " + id + " n'a pas été trouvé ");
    }
}
