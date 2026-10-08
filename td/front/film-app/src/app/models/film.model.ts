import { ActeurDTO } from './acteur.model';
import { FilmType } from './film-type.model';

export interface FilmDTO {
  id: number;
  titre: string;
  realisateur: string;
  dateSortie: string;
  genre: FilmType;
}

export interface FilmDetailDTO extends FilmDTO {
  acteurs: ActeurDTO[];
}

export interface FilmListDTO {
  films: FilmDTO[];
}

export interface FilmCreationDTO {
  titre: string;
  realisateur: string;
  dateSortie: string;
  genre: FilmType;
}

export type FilmUpdateDTO = Partial<FilmCreationDTO>;
