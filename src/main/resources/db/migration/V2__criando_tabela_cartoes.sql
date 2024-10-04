
CREATE TABLE cartoes
(
    id        UUID PRIMARY KEY,
    titulo    VARCHAR(255)   NOT NULL,
    categoria VARCHAR(64) NOT NULL,
    frase TEXT,
    url_audio VARCHAR(255)   NOT NULL UNIQUE,
    url_imagem VARCHAR(255)   NOT NULL UNIQUE
);
