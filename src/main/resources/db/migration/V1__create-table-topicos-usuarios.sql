CREATE TABLE medicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fechaDeCreacion TIMESTAMP  NOT NULL,
    estatus tinyint NOT NULL,
    autor VARCHAR(255) NOT NULL,
    curso VARCHAR(255) NOT NULL
);

create table usuarios(
    id bigint auto_increment primary key,
    login varchar(100) not null unique,
    clave varchar(300) not null
);
