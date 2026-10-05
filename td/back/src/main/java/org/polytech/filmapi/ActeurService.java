package org.polytech.filmapi;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ActeurService {

    private final ActeurRepository acteurRepo;

    List<Acteur> getAll() {
        return acteurRepo.findAll();
    }

    Acteur get(long id) throws ActeurNotFoundException {
        return acteurRepo.findById(id)
                .orElseThrow(() -> new ActeurNotFoundException(id));
    }

    void save(Acteur acteur) {
        acteurRepo.save(acteur);
    }

    public long createNewActeur(Acteur acteur) {
        if (acteur.nom() == null || acteur.nom().isBlank()) return -1;

        acteurRepo.save(acteur);

        return acteur.id();
    }

    @Transactional
    public void delete(long id) {
        Acteur acteur = get(id);
        acteur.films().forEach(film -> film.removeActeur(acteur));
        acteurRepo.delete(acteur);
    }
}
