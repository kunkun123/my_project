CREATE DATABASE netstore;
USE netstore;
CREATE TABLE catagories(
   id VARCHAR(100) PRIMARY KEY,
   name VARCHAR(100) NOT NULL UNIQUE,
   description VARCHAR(255)
);
CREATE TABLE books(
	id VARCHAR(100) PRIMARY KEY,
	name VARCHAR(100) NOT NULL UNIQUE,
	author VARCHAR(100),
	price FLOAT(8,2),
	path VARCHAR(100),
	photoFileName VARCHAR(100),
	description VARCHAR(255),
	categoryId VARCHAR(100),
	CONSTRAINT category_id_fk FOREIGN KEY(categoryId) REFERENCES catagories(ID)
);
