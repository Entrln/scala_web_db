CREATE Database starterdb;



create table if not exists Movie ("movie_id" BIGSERIAL NOT NULL PRIMARY KEY,"name" VARCHAR NOT NULL,"release_date" DATE NOT NULL);

insert into Movie (name, release_date) values ('Movie 1', '2023-01-23');
insert into Movie (name, release_date) values ('Movie 2', '2023-01-23');
insert into Movie (name, release_date) values ('Movie 3', '2023-01-23');
insert into Movie (name, release_date) values ('Movie 4', '1987-11-03');
insert into Movie (name, release_date) values ('Movie 5', '2010-01-23');


INSERT INTO BasicBlock (time_stamp, previous_hash) VALUES ('2024-01-18T12:34:56Z'::TIMESTAMPTZ, 'previousHashValue1');
INSERT INTO BasicBlock (time_stamp, previous_hash) VALUES ('2024-01-18T13:45:00Z'::TIMESTAMPTZ, 'previousHashValue2');
INSERT INTO BasicBlock (time_stamp, previous_hash) VALUES ('2024-01-18T12:34:56Z'::TIMESTAMPTZ,, 'previousHashValue3');
INSERT INTO BasicBlock (time_stamp, previous_hash) VALUES ('2024-01-18T14:56:00Z'::TIMESTAMPTZ, NULL);
INSERT INTO BasicBlock (time_stamp, previous_hash) VALUES ('2024-01-18T15:30:00Z'::TIMESTAMPTZ, 'previousHashValue4');
