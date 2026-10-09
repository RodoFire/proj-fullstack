import { Component, DestroyRef, OnInit, signal, viewChild } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { RouterLink } from '@angular/router';
import { ActeurForm } from '../acteur-form/acteur-form';
import { ActeurService } from '../acteur.service';
import { messageErreur } from '../erreur';
import { ActeurCreationDTO, ActeurDTO } from '../models/acteur.model';

@Component({
  selector: 'app-acteur-list',
  imports: [RouterLink, ActeurForm],
  templateUrl: './acteur-list.html',
})
export class ActeurList implements OnInit {
  acteurs = signal<ActeurDTO[]>([]);
  error = signal('');
  form = viewChild(ActeurForm);

  constructor(private service: ActeurService, private destroyRef: DestroyRef) { }

  ngOnInit(): void {
    this.loadActeurs();
  }

  loadActeurs(): void {
    this.service.getAllActeurs().pipe(takeUntilDestroyed(this.destroyRef)).subscribe({
      next: result => this.acteurs.set(result),
      error: err => this.error.set(messageErreur(err)),
    });
  }

  addActeur(acteur: ActeurCreationDTO): void {
    this.error.set('');
    this.service.createActeur(acteur).pipe(takeUntilDestroyed(this.destroyRef)).subscribe({
      next: () => {
        this.form()?.reset();
        this.loadActeurs();
      },
      error: err => this.error.set(messageErreur(err)),
    });
  }

  deleteActeur(id: number): void {
    this.error.set('');
    this.service.deleteActeur(id).pipe(takeUntilDestroyed(this.destroyRef)).subscribe({
      next: () => this.loadActeurs(),
      error: err => this.error.set(messageErreur(err)),
    });
  }
}
