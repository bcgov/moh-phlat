DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='fac_ifc_id'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN fac_ifc_id VARCHAR(50);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='FAC_IFC_ID'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'FAC_IFC_ID', 'FAC_IFC_ID', 'facIfcId','Facility IFC Id'
      );
    END IF;
END$$;
