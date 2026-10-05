package org.polytech.filmapi;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.polytech.filmapi.dto.ActeurUpdateDTO;

import java.util.ArrayList;
import java.util.List;

@Accessors(fluent = true)
@Getter
@Entity
public class Acteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nom;

    @ManyToMany(mappedBy = "acteurs")
    private List<Film> films = new ArrayList<>();

    protected Acteur() {
    }

    public Acteur(String nom) {
        this.nom = nom;
    }

    public Acteur updateFromDTO(ActeurUpdateDTO dto) {
        if (dto.nom() != null) this.nom = dto.nom();
        return this;
    }
}
