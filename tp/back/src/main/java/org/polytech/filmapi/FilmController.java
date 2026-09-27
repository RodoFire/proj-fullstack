package org.polytech.filmapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
public class FilmController {

    private final FilmService service;

    public FilmController(FilmService service) {
        this.service = service;
    }

    @GetMapping("/films")
    public Collection<Film> getAll() {
        return service.getAll();
    }

    @GetMapping("/films/{id}")
    public Film get(@PathVariable long id) {
        return service.get(id);
    }

    @PostMapping("/films")
    public ResponseEntity<String> createNewFilm(@RequestBody FilmView film) {
        long newFilmId = service.createNewFilm(film);
        if (newFilmId == -1) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.created(URI.create("http://localhost:8080/films/" + newFilmId))
                .build();
    }

    @PutMapping("/films/{id}")
    public ResponseEntity<String> updateFilm(@RequestBody FilmView film, @PathVariable long id) {
        service.updateFilm(film, id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/films/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        boolean deleted = service.delete(id);
        if (!deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok()
                .build();
    }

    @ExceptionHandler(FilmNotFoundException.class)
    public ResponseEntity<String> handle(FilmNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
