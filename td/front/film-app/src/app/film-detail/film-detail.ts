import { Component, DestroyRef, OnInit, computed, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { ActeurService } from '../acteur.service';
import { messageErreur } from '../erreur';
import { FilmForm } from '../film-form/film-form';
import { FilmService } from '../film.service';
import { ActeurDTO } from '../models/acteur.model';
import { FilmCreationDTO, FilmDetailDTO } from '../models/film.model';

@Component({
  selector: 'app-film-detail',
  imports: [RouterLink, FormsModule, FilmForm],
  templateUrl: './film-detail.html',
})
export class FilmDetail implements OnInit {
  film = signal<FilmDetailDTO | undefined>(undefined);
  acteurs = signal<ActeurDTO[]>([]);
  error = signal('');
  selectedId = signal<number | undefined>(undefined);
  id = 0;

  disponibles = computed(() => {
    const lies = this.film()?.acteurs.map(a => a.id) ?? [];
    return this.acteurs().filter(a => !lies.includes(a.id));
  });

  constructor(
    private route: ActivatedRoute,
    private service: FilmService,
    private acteurService: ActeurService,
    private destroyRef: DestroyRef,
  ) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.loadFilm();
    this.acteurService.getAllActeurs().pipe(takeUntilDestroyed(this.destroyRef)).subscribe({
      next: result => this.acteurs.set(result),
      error: err => this.error.set(messageErreur(err)),
    });
  }

  loadFilm(): void {
    this.service.getFilm(this.id).pipe(takeUntilDestroyed(this.destroyRef)).subscribe({
      next: result => this.film.set(result),
      error: err => this.error.set(messageErreur(err)),
    });
  }

  updateFilm(film: FilmCreationDTO): void {
    this.error.set('');
    this.service.updateFilm(this.id, film).pipe(takeUntilDestroyed(this.destroyRef)).subscribe({
      next: () => this.loadFilm(),
      error: err => this.error.set(messageErreur(err)),
    });
  }

  addActeur(): void {
    const acteurId = this.selectedId();
    if (acteurId === undefined) return;
    this.error.set('');
    this.service.addActeur(this.id, acteurId).pipe(takeUntilDestroyed(this.destroyRef)).subscribe({
      next: () => {
        this.selectedId.set(undefined);
        this.loadFilm();
      },
      error: err => this.error.set(messageErreur(err)),
    });
  }

  removeActeur(acteurId: number): void {
    this.error.set('');
    this.service.removeActeur(this.id, acteurId).pipe(takeUntilDestroyed(this.destroyRef)).subscribe({
      next: () => this.loadFilm(),
      error: err => this.error.set(messageErreur(err)),
    });
  }
}
