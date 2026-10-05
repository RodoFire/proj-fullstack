package org.polytech.filmapi;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.polytech.filmapi.dto.FilmCreationDTO;
import org.polytech.filmapi.dto.FilmUpdateDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Accessors(fluent = true)
@Entity
@Table(name = "films")
@AllArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "titre", nullable = false)
    private String titre;

    @Column(name = "realisateur", nullable = false)
    private String realisateur;

    @ManyToMany()
    @JoinTable(name = "acteur_id",
            joinColumns = @JoinColumn(name = "id_films"),
            inverseJoinColumns = @JoinColumn(name = "id_acteurs"))
    private List<Acteur> acteurs = new ArrayList<>();

    @Column(name = "date_sortie", nullable = false)
    private LocalDate dateSortie;

    @Enumerated(EnumType.STRING)
    private FilmType genre;

    protected Film() {

    }

    public Film(String titre, String realisateur, LocalDate date, FilmType genre) {
        this.titre = titre;
        this.realisateur = realisateur;
        this.dateSortie = date;
        this.genre = genre;
    }

    public static Film fromView(FilmView view) {
        return new Film(view.titre(), view.realisateur(), view.dateSortie(), view.genre());
    }

    public Film updateFromDTO(FilmUpdateDTO dto) {
        if (dto.titre() != null) this.titre = dto.titre();
        if (dto.realisateur() != null) this.realisateur = dto.realisateur();
        if (dto.dateSortie() != null) this.dateSortie = dto.dateSortie();
        if (dto.genre() != null) this.genre = dto.genre();
        return this;
    }

    public void addActeur(Acteur acteur) {
        if (!acteurs.contains(acteur)) acteurs.add(acteur);
    }

    public void removeActeur(Acteur acteur) {
        acteurs.remove(acteur);
    }

    public enum FilmType {
        COMEDY,
        DRAMA,
        ACTION,
        SCIENCE_FICTION,
        FANTASY
    }
}
