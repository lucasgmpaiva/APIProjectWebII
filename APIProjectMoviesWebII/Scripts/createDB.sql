CREATE SCHEMA IF NOT EXISTS moviesDB;

use moviesDB;

create table movie_entity (id bigint not null auto_increment, director varchar(140) not null, gender varchar(140) not null, release_date varchar(140) not null, title varchar(140) not null, writer varchar(140) not null, primary key (id));

create table serie_entity (id bigint not null auto_increment, broadcaster varchar(140) not null, gender varchar(140) not null, release_date varchar(140) not null, title varchar(140) not null, primary key (id));