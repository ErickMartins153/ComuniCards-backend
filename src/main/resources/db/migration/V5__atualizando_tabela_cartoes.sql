ALTER TABLE cartoes
    ADD COLUMN IF NOT EXISTS is_favorito BOOLEAN NOT NULL DEFAULT FALSE;