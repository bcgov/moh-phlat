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

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='PHYSICAL_ADDR_MAILABILITY_SCORE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'PHYSICAL_ADDR_MAILABILITY_SCORE', 'PHYSICAL_ADDR_MAILABILITY_SCORE', 'physicalAddrMailabilityScore','Physical Addr Mailabity Score'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='MAIL_ADDR_MAILABILITY_SCORE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'MAIL_ADDR_MAILABILITY_SCORE', 'MAIL_ADDR_MAILABILITY_SCORE', 'mailAddrMailabilityScore','Mail Addr Mailabity Score'
      );
    END IF;
END$$;

