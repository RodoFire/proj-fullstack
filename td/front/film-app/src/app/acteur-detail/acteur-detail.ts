import { Component, DestroyRef, OnInit, computed, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { ActeurForm } from '../acteur-form/acteur-form';
import { ActeurService } from '../acteur.service';
import { messageErreur } from '../erreur';
import { FilmService } from '../film.service';
import { ActeurCreationDTO, ActeurDetailDTO } from '../models/acteur.model';
import { FilmDTO } from '../models/film.model';

@Component({
  selector: 'app-acteur-detail',
  imports: [RouterLink, FormsModule, ActeurForm],
  templateUrl: './acteur-detail.html',
})
export class ActeurDetail implements OnInit {
  acteur = signal<ActeurDetailDTO | undefined>(undefined);
  films = signal<FilmDTO[]>([]);
  error = signal('');
  selectedId = signal<number | undefined>(undefined);
  id = 0;

  disponibles = computed(() => {
    const lies = this.acteur()?.films.map(f => f.id) ?? [];
    return this.films().filter(f => !lies.includes(f.id));
  });

  constructor(
    private route: ActivatedRoute,
    private service: ActeurService,
    private filmService: FilmService,
    private destroyRef: DestroyRef,
  ) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.loadActeur();
    this.filmService.getAllFilms().pipe(takeUntilDestroyed(this.destroyRef)).subscribe({
      next: result => this.films.set(result),
      error: err => this.error.set(messageErreur(err)),
    });
  }

  loadActeur(): void {
    this.service.getActeur(this.id).pipe(takeUntilDestroyed(this.destroyRef)).subscribe({
      next: result => this.acteur.set(result),
      error: err => this.error.set(messageErreur(err)),
    });
  }

  updateActeur(acteur: ActeurCreationDTO): void {
    this.error.set('');
    this.service.updateActeur(this.id, acteur).pipe(takeUntilDestroyed(this.destroyRef)).subscribe({
      next: () => this.loadActeur(),
      error: err => this.error.set(messageErreur(err)),
    });
  }

  addFilm(): void {
    const filmId = this.selectedId();
    if (filmId === undefined) return;
    this.error.set('');
    this.filmService.addActeur(filmId, this.id).pipe(takeUntilDestroyed(this.destroyRef)).subscribe({
      next: () => {
        this.selectedId.set(undefined);
        this.loadActeur();
      },
      error: err => this.error.set(messageErreur(err)),
    });
  }

  removeFilm(filmId: number): void {
    this.error.set('');
    this.filmService.removeActeur(filmId, this.id).pipe(takeUntilDestroyed(this.destroyRef)).subscribe({
      next: () => this.loadActeur(),
      error: err => this.error.set(messageErreur(err)),
    });
  }
}
