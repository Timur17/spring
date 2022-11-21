--DROP TABLE IF EXISTS BOOKS;
--DROP TABLE IF EXISTS AUTHORS;
--DROP TABLE IF EXISTS GENRES;
--CREATE TABLE BOOKS(ID BIGINT PRIMARY KEY auto_increment, TITLE VARCHAR(255), AUTHOR_ID BIGINT, GENRE_ID BIGINT);
--CREATE TABLE AUTHORS(ID BIGINT PRIMARY KEY auto_increment, AUTHOR VARCHAR(255));
--CREATE TABLE GENRES(ID BIGINT PRIMARY KEY auto_increment, GENRE VARCHAR(255));

    drop table if exists authors CASCADE;

    drop table if exists books CASCADE;

    drop table if exists genres CASCADE;

  create table authors (
       id bigint generated by default as identity,
        author varchar(255),
        primary key (id)
    );

    create table books (
       id bigint generated by default as identity,
        title varchar(255),
        author_id bigint,
        genre_id bigint,
        primary key (id)
    );

    create table genres (
       id bigint generated by default as identity,
        genre varchar(255),
        primary key (id)
    );

    create table comments (
       id bigint generated by default as identity,
        comment varchar(255),
        book_id bigint,
        primary key (id)
    )