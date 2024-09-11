DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='fac_civic_address'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN fac_civic_address VARCHAR(240);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='databc_input_civic_address'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN databc_input_civic_address VARCHAR(240);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='databc_output_civic_address'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN databc_output_civic_address VARCHAR(240);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='FAC_CIVIC_ADDRESS'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'FAC_CIVIC_ADDRESS', 'FAC_CIVIC_ADDRESS', 'facCivicAddress','Facility Civic Address'
      );
    END IF;
END$$;
