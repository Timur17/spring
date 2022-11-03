DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS AUTHORS;
DROP TABLE IF EXISTS GENRES;
CREATE TABLE BOOKS(ID BIGINT PRIMARY KEY auto_increment, TITLE VARCHAR(255), AUTHOR_ID BIGINT, GENRE_ID BIGINT);
CREATE TABLE AUTHORS(ID BIGINT PRIMARY KEY auto_increment, AUTHOR VARCHAR(255));
CREATE TABLE GENRES(ID BIGINT PRIMARY KEY auto_increment, GENRE VARCHAR(255));
