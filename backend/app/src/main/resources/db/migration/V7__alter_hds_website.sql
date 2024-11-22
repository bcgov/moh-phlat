DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='hds_website'
    ) THEN
        ALTER TABLE source_data ALTER COLUMN hds_website TYPE VARCHAR(500);
    END IF;
END$$;

DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='hds_website'
    ) THEN
        ALTER TABLE process_data ALTER COLUMN hds_website TYPE VARCHAR(500);
    END IF;
END$$;

