import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ActeurCreationDTO, ActeurDTO, ActeurDetailDTO } from './models/acteur.model';

@Injectable({
  providedIn: 'root'
})
export class ActeurService {
  constructor(private httpClient: HttpClient) { }

  getAllActeurs(): Observable<ActeurDTO[]> {
    return this.httpClient.get<ActeurDTO[]>('/api/acteurs');
  }

  getActeur(id: number): Observable<ActeurDetailDTO> {
    return this.httpClient.get<ActeurDetailDTO>(`/api/acteurs/${id}`);
  }

  createActeur(acteur: ActeurCreationDTO): Observable<void> {
    return this.httpClient.post<void>('/api/acteurs', acteur);
  }
}
