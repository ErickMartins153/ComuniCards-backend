DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'usuarios_senha_key'
    ) THEN
        ALTER TABLE usuarios DROP CONSTRAINT usuarios_senha_key;
    END IF;
END $$;