DROP TABLE IF EXISTS LANGUAGES;

CREATE TABLE LANGUAGES (
                           id VARCHAR(100)  PRIMARY KEY,
                           name VARCHAR(100) NOT NULL,
                           author VARCHAR(100) NOT NULL,
                           fileExtension VARCHAR(100) NOT NULL
);