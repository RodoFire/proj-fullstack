import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FilmType } from './models/film-type.model';
import { FilmCreationDTO, FilmDTO, FilmDetailDTO, FilmUpdateDTO } from './models/film.model';

@Injectable({
  providedIn: 'root'
})
export class FilmService {
  constructor(private httpClient: HttpClient) { }

  getAllFilms(genre?: FilmType, titre?: string): Observable<FilmDTO[]> {
    let httpParams = new HttpParams();
    if(genre != null) {
      httpParams = httpParams.set('genre', genre);
    }
    if(titre) {
      httpParams = httpParams.set('titre', titre);
    }
    const params = httpParams
    return this.httpClient.get<FilmDTO[]>('/api/films', { params });
  }

  getFilm(id: number): Observable<FilmDetailDTO> {
    return this.httpClient.get<FilmDetailDTO>(`/api/films/${id}`);
  }

  createFilm(film: FilmCreationDTO): Observable<void> {
    return this.httpClient.post<void>('/api/films', film);
  }

  updateFilm(id: number, film: FilmUpdateDTO): Observable<void> {
    return this.httpClient.put<void>(`/api/films/${id}`, film);
  }

  deleteFilm(id: number): Observable<void> {
    return this.httpClient.delete<void>(`/api/films/${id}`);
  }

  addActeur(filmId: number, acteurId: number): Observable<void> {
    return this.httpClient.post<void>(`/api/films/${filmId}/acteurs/${acteurId}`, null);
  }

  removeActeur(filmId: number, acteurId: number): Observable<void> {
    return this.httpClient.delete<void>(`/api/films/${filmId}/acteurs/${acteurId}`);
  }
}
