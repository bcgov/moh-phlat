DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='physical_addr_mailability_score'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN physical_addr_mailability_score VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='mail_addr_mailability_score'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN mail_addr_mailability_score VARCHAR(10);
    END IF;
END$$;