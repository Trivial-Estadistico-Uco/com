-- Tabla de Usuario
DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(64) NOT NULL,
    apellido VARCHAR(64) NOT NULL,
    correo VARCHAR(64) NOT NULL,
    contrasena VARCHAR(64) NOT NULL,
    admin BOOL
);

-- Tabla de Quiz
DROP TABLE IF EXISTS quiz;
CREATE TABLE quiz (
    idQuiz INT PRIMARY KEY AUTO_INCREMENT,
    tematica VARCHAR(255) NOT NULL
);

-- Tabla de Pregunta
DROP TABLE IF EXISTS pregunta;
CREATE TABLE pregunta (
    idPregunta INT PRIMARY KEY AUTO_INCREMENT,
    texto VARCHAR( 255 ) NOT NULL ,
    Opcion1 VARCHAR( 64 ) NOT NULL ,
    Opcion2 VARCHAR( 64 ) NOT NULL ,
    Opcion3 VARCHAR( 64 ) NOT NULL ,
    Opcion4 VARCHAR( 64 ) NOT NULL ,
    respuestaCorrecta VARCHAR( 64 ) NOT NULL,
    categoria VARCHAR( 64 ) NOT NULL
);

-- Tabla intermedia para la relación Muchos a Muchos entre Quiz y Pregunta
DROP TABLE IF EXISTS quiz_pregunta;
CREATE TABLE quiz_pregunta (
    idquiz_pregunta INT PRIMARY KEY AUTO_INCREMENT,
    idQuiz INT NOT NULL,
    idPregunta INT NOT NULL,
    CONSTRAINT FK_idQuiz FOREIGN KEY (idQuiz) REFERENCES quiz(idQuiz),
    CONSTRAINT FK_idPregunta FOREIGN KEY (idPregunta) REFERENCES pregunta(idPregunta)
);

-- Tabla de Resultado
DROP TABLE IF EXISTS resultado;
CREATE TABLE resultado (
    idResultado INT PRIMARY KEY AUTO_INCREMENT,
    puntuacion INT,
    idUsuario INT NOT NULL,
    idQuiz INT NOT NULL,
    CONSTRAINT FK_idUsuario FOREIGN KEY (idUsuario) REFERENCES usuario(id),
    CONSTRAINT FK_idQuiz FOREIGN KEY (idQuiz) REFERENCES quiz(idQuiz)
);