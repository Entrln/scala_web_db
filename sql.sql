CREATE Database starterdb;

CREATE TABLE IF NOT EXISTS Movie ("movie_id" BIGSERIAL NOT NULL PRIMARY KEY,"name" VARCHAR NOT NULL,"director" VARCHAR NOT NULL);

INSERT INTO Movie (name, director) VALUES ('Movie 1', 'director name 1');
INSERT INTO Movie (name, director) VALUES ('Movie 2', 'director name 2');
INSERT INTO Movie (name, director) VALUES ('Movie 3', 'director name 3');
INSERT INTO Movie (name, director) VALUES ('Movie 4', 'director name 4');
INSERT INTO Movie (name, director) VALUES ('Movie 5', 'director name 5');
