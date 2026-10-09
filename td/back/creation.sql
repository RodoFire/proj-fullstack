-- Acteurs
INSERT INTO acteur (id, nom) VALUES
(1, 'Leonardo DiCaprio'),
(2, 'Joseph Gordon-Levitt'),
(3, 'Keanu Reeves'),
(4, 'Carrie-Anne Moss'),
(5, 'Elijah Wood'),
(6, 'Ian McKellen'),
(7, 'Harrison Ford'),
(8, 'Mark Hamill'),
(9, 'Robert Downey Jr.'),
(10, 'Chris Evans'),
(11, 'Jim Carrey'),
(12, 'Tom Hanks');

-- Films
INSERT INTO films (id, titre, realisateur, date_sortie, genre) VALUES
(1, 'Inception', 'Christopher Nolan', '2010-07-21', 'SCIENCE_FICTION'),
(2, 'Matrix', 'Lana Wachowski', '1999-06-23', 'SCIENCE_FICTION'),
(3, 'Le Seigneur des Anneaux : La Communauté de l''Anneau', 'Peter Jackson', '2001-12-19', 'FANTASY'),
(4, 'Star Wars : Un nouvel espoir', 'George Lucas', '1977-10-19', 'SCIENCE_FICTION'),
(5, 'Avengers', 'Joss Whedon', '2012-04-25', 'ACTION'),
(6, 'The Truman Show', 'Peter Weir', '1998-10-14', 'COMEDY'),
(7, 'Forrest Gump', 'Robert Zemeckis', '1994-10-05', 'DRAMA'),
(8, 'Le Hobbit : Un voyage inattendu', 'Peter Jackson', '2012-12-12', 'FANTASY'),
(9, 'Indiana Jones : Les Aventuriers de l''arche perdue', 'Steven Spielberg', '1981-09-16', 'ACTION'),
(10, 'Le Loup de Wall Street', 'Martin Scorsese', '2014-01-15', 'DRAMA');

-- Liaison films <-> acteurs
INSERT INTO acteur_id (id_films, id_acteurs) VALUES
(1, 1), (1, 2),
(2, 3), (2, 4),
(3, 5), (3, 6),
(4, 7), (4, 8),
(5, 9), (5, 10),
(6, 11),
(7, 12),
(8, 6),
(9, 7),
(10, 1);
