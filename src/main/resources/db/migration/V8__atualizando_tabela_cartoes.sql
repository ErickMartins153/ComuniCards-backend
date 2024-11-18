ALTER TABLE cartoes
    ADD CONSTRAINT fk_criador_id
        FOREIGN KEY (criador_id)
            REFERENCES usuarios(id)
            ON DELETE CASCADE;