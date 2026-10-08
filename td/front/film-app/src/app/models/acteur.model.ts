import { FilmDTO } from './film.model';

export interface ActeurDTO {
  id: number;
  nom: string;
}

export interface ActeurDetailDTO extends ActeurDTO {
  films: FilmDTO[];
}

export interface ActeurCreationDTO {
  nom: string;
}

export type ActeurUpdateDTO = Partial<ActeurCreationDTO>;
