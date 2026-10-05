package org.polytech.filmapi;

import org.polytech.filmapi.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/acteurs")
public class ActeurController {

    private final ActeurService service;

    public ActeurController(ActeurService service) {
        this.service = service;
    }

    @GetMapping
    public List<ActeurDTO> getAll() {
        return service.getAll().stream().map(ActeurMapper::toDTO).toList();
    }

    @GetMapping("/{id}")
    public ActeurDetailDTO get(@PathVariable long id) {
        return ActeurMapper.toDetailDTO(service.get(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ActeurCreationDTO dto) {
        long newId = service.createNewActeur(ActeurMapper.toEntity(dto));
        if (newId == -1) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.created(URI.create("/api/acteurs/" + newId)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody ActeurUpdateDTO dto, @PathVariable long id) {
        service.save(ActeurMapper.update(service.get(id), dto));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/films")
    public List<FilmDTO> getFilms(@PathVariable long id) {
        return ActeurMapper.toFilmDTOs(service.get(id).films());
    }

}
