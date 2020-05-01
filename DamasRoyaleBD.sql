CREATE DATABASE damasroyale;

CREATE USER 'DamasRoyale'@'localhost' IDENTIFIED BY 'NoSeJugarAlAjedrez';

GRANT SELECT, INSERT, UPDATE, DELETE ON damasroyale.* TO 'DamasRoyale'@'localhost';

USE damasroyale;

CREATE TABLE USUARIOS (
    ID INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    NOMBRE VARCHAR(32) NOT NULL,
    EMAIL VARCHAR(64) NOT NULL,
    CONTRASENYA VARCHAR(64) NOT NULL,
    IMAGEN VARCHAR(64) NOT NULL,
    REGISTRO DATETIME NOT NULL,
    ESTADO BOOLEAN NOT NULL
);

CREATE TABLE ACTIVACIONES (
    ID INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    IDUSUARIO INT(11) NOT NULL,
    CODIGO VARCHAR(15) NOT NULL,
    FOREIGN KEY (IDUSUARIO)
        REFERENCES USUARIOS (ID)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE PARTIDAS (
    ID INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    IDUSUARIO_A INT(11) NOT NULL,
    IDUSUARIO_B INT(11),
    FINALIZADA BOOLEAN NOT NULL,
    FOREIGN KEY (IDUSUARIO_A)
        REFERENCES USUARIOS (ID)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (IDUSUARIO_B)
        REFERENCES USUARIOS (ID)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE RESULTADOS (
    ID INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    IDPARTIDA INT(11) NOT NULL,
    FECHA_HORA DATETIME NOT NULL,
    TABLAS BOOLEAN NOT NULL,
    GANADOR INT(11),
    PERDEDOR INT(11),
    FOREIGN KEY (IDPARTIDA)
        REFERENCES PARTIDAS (ID)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (GANADOR)
        REFERENCES USUARIOS (ID)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (PERDEDOR)
        REFERENCES USUARIOS (ID)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE MOVIMIENTOS (
    ID INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    IDPARTIDA INT(11) NOT NULL,
    IDUSUARIO INT(11) NOT NULL,
    POS_INICIAL INT(4) NOT NULL,
    POS_FINAL INT(4) NOT NULL,
    FOREIGN KEY (IDPARTIDA)
        REFERENCES PARTIDAS (ID)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (IDUSUARIO)
        REFERENCES USUARIOS (ID)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE MENSAJES (
    ID INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    IDPARTIDA INT(11) NOT NULL,
    IDUSUARIO INT(11) NOT NULL,
    HORA TIME NOT NULL,
    TEXTO VARCHAR(255) NOT NULL,
    FOREIGN KEY (IDPARTIDA)
        REFERENCES PARTIDAS (ID)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (IDUSUARIO)
        REFERENCES USUARIOS (ID)
        ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO USUARIOS (NOMBRE, EMAIL, CONTRASENYA, IMAGEN, REGISTRO, ESTADO) VALUES 
('Usuario01', 'Usuario01@gmail.com', '1234','usuario.jpg', now(), 1), 
('Usuario02', 'Usuario02@gmail.com', '1234','usuario.jpg', now(), 1),
('Usuario03', 'Usuario03@gmail.com', '1234','usuario.jpg', now(), 1), 
('Usuario04', 'Usuario04@gmail.com', '1234','usuario.jpg', now(), 1), 
('Usuario05', 'Usuario05@gmail.com', '1234','usuario.jpg', now(), 1), 
('Usuario06', 'Usuario06@gmail.com', '1234','usuario.jpg', now(), 1), 
('Usuario07', 'Usuario07@gmail.com', '1234','usuario.jpg', now(), 1), 
('Usuario08', 'Usuario08@gmail.com', '1234','usuario.jpg', now(), 1), 
('Usuario09', 'Usuario09@gmail.com', '1234','usuario.jpg', now(), 1),
('Usuario10', 'Usuario010@gmail.com', '1234','usuario.jpg', now(), 1);


INSERT INTO PARTIDAS (IDUSUARIO_A, IDUSUARIO_B, FINALIZADA) 
VALUES 
(1,2,1),
(2,1,1),
(3,4,1),
(4,3,1),
(5,6,1),
(6,5,1),
(7,8,1),
(8,7,1),
(9,10,1),
(10,9,1);

INSERT INTO PARTIDAS (IDUSUARIO_A, IDUSUARIO_B, FINALIZADA) 
VALUES 
(1,2,1),(1,2,1),(1,2,1),(1,2,1),(1,2,1),(1,2,1),(1,2,1),(1,2,1),(1,2,1),(1,2,1),
(2,1,1),(2,1,1),(2,1,1),(2,1,1),(2,1,1),(2,1,1),(2,1,1),(2,1,1),(2,1,1),
(3,4,1),(3,4,1),(3,4,1),(3,4,1),(3,4,1),(3,4,1),(3,4,1),(3,4,1),
(4,3,1),(4,3,1),(4,3,1),(4,3,1),(4,3,1),(4,3,1),(4,3,1),
(5,6,1),(5,6,1),(5,6,1),(5,6,1),(5,6,1),(5,6,1),
(6,5,1),(6,5,1),(6,5,1),(6,5,1),(6,5,1),
(7,8,1),(7,8,1),(7,8,1),(7,8,1),
(8,7,1),(8,7,1),(8,7,1),
(9,10,1),(9,10,1),
(10,9,1);


INSERT INTO RESULTADOS(IDPARTIDA,FECHA_HORA,GANADOR, PERDEDOR,TABLAS) 
VALUES 
(11,now(),1,2,0),(12,now(),1,2,0),(13,now(),1,2,0),(14,now(),1,2,0),(15,now(),1,2,0),(16,now(),1,2,0),(17,now(),1,2,0),(18,now(),1,2,0),(19,now(),1,2,0),(20,now(),1,2,0),
(21,now(),2,1,0),(22,now(),2,1,0),(23,now(),2,1,0),(24,now(),2,1,0),(25,now(),2,1,0),(26,now(),2,1,0),(27,now(),2,1,0),(28,now(),2,1,0),(29,now(),2,1,0),
(30,now(),3,4,0),(31,now(),3,4,0),(32,now(),3,4,0),(33,now(),3,4,0),(34,now(),3,4,0),(35,now(),3,4,0),(36,now(),3,4,0),(37,now(),3,4,0),
(38,now(),4,3,0),(39,now(),4,3,0),(40,now(),4,3,0),(41,now(),4,3,0),(42,now(),4,3,0),(43,now(),4,3,0),(44,now(),4,3,0),
(45,now(),5,6,0),(46,now(),5,6,0),(47,now(),5,6,0),(48,now(),5,6,0),(49,now(),5,6,0),(50,now(),5,6,0),
(51,now(),6,5,0),(52,now(),6,5,0),(53,now(),6,5,0),(54,now(),6,5,0),(55,now(),6,5,0),
(56,now(),7,8,0),(57,now(),7,8,0),(58,now(),7,8,0),(59,now(),7,8,0),
(60,now(),8,7,0),(61,now(),8,7,0),(62,now(),8,7,0),
(63,now(),9,10,0),(64,now(),9,10,0),
(65,now(),10,9,0);