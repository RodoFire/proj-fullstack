package org.polytech.filmapi.dto;

import java.util.List;

public record ActeurDetailDTO(long id, String nom, List<FilmDTO> films) {
}
