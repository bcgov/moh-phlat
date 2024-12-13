DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE UPPER(table_name)='PROCESS_DATA' and UPPER(column_name)='FAC_HSDA_NAME'
    ) THEN
	UPDATE table_column_info SET  title = 'Facility HSDA Name' WHERE table_name = 'PROCESS_DATA' and column_name = 'FAC_HSDA_NAME';
    END IF;
END$$;





