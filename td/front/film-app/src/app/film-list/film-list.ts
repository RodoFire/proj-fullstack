import { Component, DestroyRef, OnInit, signal, viewChild } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { RouterLink } from '@angular/router';
import { messageErreur } from '../erreur';
import { FilmForm } from '../film-form/film-form';
import { FilmService } from '../film.service';
import { FilmCreationDTO, FilmDTO } from '../models/film.model';

@Component({
  selector: 'app-film-list',
  imports: [RouterLink, FilmForm],
  templateUrl: './film-list.html',
})
export class FilmList implements OnInit {
  films = signal<FilmDTO[]>([]);
  error = signal('');
  form = viewChild(FilmForm);

  constructor(private service: FilmService, private destroyRef: DestroyRef) { }

  ngOnInit(): void {
    this.loadFilms();
  }

  loadFilms(): void {
    this.service.getAllFilms().pipe(takeUntilDestroyed(this.destroyRef)).subscribe({
      next: result => this.films.set(result),
      error: err => this.error.set(messageErreur(err)),
    });
  }

  addFilm(film: FilmCreationDTO): void {
    this.error.set('');
    this.service.createFilm(film).pipe(takeUntilDestroyed(this.destroyRef)).subscribe({
      next: () => {
        this.form()?.reset();
        this.loadFilms();
      },
      error: err => this.error.set(messageErreur(err)),
    });
  }

  deleteFilm(id: number): void {
    this.error.set('');
    this.service.deleteFilm(id).pipe(takeUntilDestroyed(this.destroyRef)).subscribe({
      next: () => this.loadFilms(),
      error: err => this.error.set(messageErreur(err)),
    });
  }
}
