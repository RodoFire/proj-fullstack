package org.polytech.filmapi;

import org.polytech.filmapi.dto.*;

import java.util.List;

public class FilmMapper {

    public static Film toEntity(FilmCreationDTO dto) {
        return new Film(dto.titre(), dto.realisateur(), dto.dateSortie(), dto.genre());
    }

    public static Film update(Film film, FilmUpdateDTO dto) {
        return film.updateFromDTO(dto);
    }

    public static FilmDTO toDTO(Film film) {
        return new FilmDTO(film.id(), film.titre(), film.realisateur(), film.dateSortie(), film.genre());
    }

    public static List<FilmDTO> toDTOs(List<Film> films) {
        return films.stream().map(FilmMapper::toDTO).toList();
    }

    public static FilmDetailDTO toDetailDTO(Film film) {
        return new FilmDetailDTO(
                film.id(), film.titre(), film.realisateur(), film.dateSortie(), film.genre(),
                toActeurDTOs(film.acteurs()));
    }

    public static ActeurDTO toDTO(Acteur acteur) {
        return new ActeurDTO(acteur.id(), acteur.nom());
    }

    public static List<ActeurDTO> toActeurDTOs(List<Acteur> acteurs) {
        return acteurs.stream().map(FilmMapper::toDTO).toList();
    }
}
