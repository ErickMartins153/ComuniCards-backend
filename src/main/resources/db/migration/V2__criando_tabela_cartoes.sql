
CREATE TABLE cartoes
(
    id        UUID PRIMARY KEY,
    titulo    VARCHAR(255)   NOT NULL,
    categoria VARCHAR(64) NOT NULL,
    descricao TEXT,
    url_audio VARCHAR(255)   NOT NULL UNIQUE
);