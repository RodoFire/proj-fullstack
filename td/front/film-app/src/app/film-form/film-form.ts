import { Component, effect, input, output, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FilmCreationDTO, FilmDTO } from '../models/film.model';
import { FilmType } from '../models/film-type.model';

@Component({
  selector: 'app-film-form',
  imports: [FormsModule],
  templateUrl: './film-form.html',
})
export class FilmForm {
  film = input<FilmDTO>();
  label = input('Enregistrer');
  saved = output<FilmCreationDTO>();

  genres: FilmType[] = ['COMEDY', 'DRAMA', 'ACTION', 'SCIENCE_FICTION', 'FANTASY'];
  titre = signal('');
  realisateur = signal('');
  dateSortie = signal('');
  genre = signal<FilmType>('COMEDY');

  constructor() {
    effect(() => {
      const film = this.film();
      if (film) this.fill(film);
    });
  }

  submit(): void {
    this.saved.emit({
      titre: this.titre(),
      realisateur: this.realisateur(),
      dateSortie: this.dateSortie(),
      genre: this.genre(),
    });
  }

  reset(): void {
    this.fill({ titre: '', realisateur: '', dateSortie: '', genre: 'COMEDY' });
  }

  private fill(film: FilmCreationDTO): void {
    this.titre.set(film.titre);
    this.realisateur.set(film.realisateur);
    this.dateSortie.set(film.dateSortie);
    this.genre.set(film.genre);
  }
}
