package org.polytech.filmapi;

import org.polytech.filmapi.dto.*;

import java.util.List;

public class ActeurMapper {

    public static Acteur toEntity(ActeurCreationDTO dto) {
        return new Acteur(dto.nom());
    }

    public static Acteur update(Acteur acteur, ActeurUpdateDTO dto) {
        return acteur.updateFromDTO(dto);
    }

    public static ActeurDTO toDTO(Acteur acteur) {
        return new ActeurDTO(acteur.id(), acteur.nom());
    }

    public static ActeurDetailDTO toDetailDTO(Acteur acteur) {
        return new ActeurDetailDTO(acteur.id(), acteur.nom(), toFilmDTOs(acteur.films()));
    }

    public static List<FilmDTO> toFilmDTOs(List<Film> films) {
        return films.stream().map(FilmMapper::toDTO).toList();
    }
}
