import { Component, effect, input, output, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActeurCreationDTO, ActeurDTO } from '../models/acteur.model';

@Component({
  selector: 'app-acteur-form',
  imports: [FormsModule],
  templateUrl: './acteur-form.html',
})
export class ActeurForm {
  acteur = input<ActeurDTO>();
  label = input('Enregistrer');
  saved = output<ActeurCreationDTO>();

  nom = signal('');

  constructor() {
    effect(() => {
      const acteur = this.acteur();
      if (acteur) this.nom.set(acteur.nom);
    });
  }

  submit(): void {
    this.saved.emit({ nom: this.nom() });
  }

  reset(): void {
    this.nom.set('');
  }
}
