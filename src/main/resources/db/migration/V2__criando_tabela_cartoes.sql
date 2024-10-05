
CREATE TABLE cartoes
(
    id        UUID PRIMARY KEY,
    titulo    VARCHAR(255)   NOT NULL,
    categoria VARCHAR(64) NOT NULL,
    frase TEXT,
    url_imagem VARCHAR(255)   NOT NULL
);
