package org.polytech.filmapi;

import org.polytech.filmapi.dto.ActeurDTO;
import org.polytech.filmapi.dto.FilmCreationDTO;
import org.polytech.filmapi.dto.FilmDTO;
import org.polytech.filmapi.dto.FilmDetailDTO;
import org.polytech.filmapi.dto.FilmUpdateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/api/films")
public class FilmController {

    private final FilmService service;

    public FilmController(FilmService service) {
        this.service = service;
    }

    @GetMapping()
    public Collection<FilmDTO> getAll(@RequestParam(required = false) String titre, @RequestParam(required = false) Film.FilmType genre) {
        if(titre != null && genre != null) {
            return FilmMapper.toDTOs(service.getByTitreNByGenre(titre, genre));
        }

        if (titre != null) {
            return FilmMapper.toDTOs(service.getByTitre(titre));
        }

        if (genre != null) {
            return FilmMapper.toDTOs(service.getByGenre(genre));
        }

        return service.getAll().stream().map(FilmMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public FilmDetailDTO get(@PathVariable long id) {
        return FilmMapper.toDetailDTO(service.get(id));
    }

    @PostMapping()
    public ResponseEntity<String> createNewFilm(@RequestBody FilmCreationDTO dto) {
        long newFilmId = service.createNewFilm(FilmMapper.toEntity(dto));
        if (newFilmId == -1) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.created(URI.create("http://localhost:8080/films/" + newFilmId))
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFilm(@RequestBody FilmUpdateDTO dto, @PathVariable long id) {
        Film film = service.get(id);
        service.save(FilmMapper.update(film, dto));

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/{id}/acteurs")
    public List<ActeurDTO> getActeurs(@PathVariable long id) {
        return FilmMapper.toActeurDTOs(service.getActeurs(id));
    }

    @PostMapping("/{id}/acteurs/{acteurId}")
    public ResponseEntity<Void> addActeur(@PathVariable long id, @PathVariable long acteurId) {
        service.addActeur(id, acteurId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/acteurs/{acteurId}")
    public ResponseEntity<Void> removeActeur(@PathVariable long id, @PathVariable long acteurId) {
        service.removeActeur(id, acteurId);
        return ResponseEntity.noContent().build();
    }

}
