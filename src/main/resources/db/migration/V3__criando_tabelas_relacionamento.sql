CREATE TABLE usuarios_cartoes
(
    usuario_id UUID NOT NULL,
    cartao_id  UUID NOT NULL,
    CONSTRAINT usuario_id FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE,
    CONSTRAINT cartao_id FOREIGN KEY (cartao_id) REFERENCES cartoes (id) ON DELETE CASCADE

);
