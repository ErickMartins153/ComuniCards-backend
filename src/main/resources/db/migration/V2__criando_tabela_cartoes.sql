CREATE TYPE categoria_enum as ENUM (
    'EMOCOES',
    'NECESSIDADES',
    'INTERACOES_SOCIAIS',
    'ATIVIDADES_DIARIAS',
    'SITUACOES_ESPECIFICAS'
    );


CREATE TABLE cartoes
(
    id        UUID PRIMARY KEY,
    titulo    VARCHAR(255)   NOT NULL,
    categoria categoria_enum NOT NULL,
    descricao TEXT,
    url_audio VARCHAR(255)   NOT NULL UNIQUE
);