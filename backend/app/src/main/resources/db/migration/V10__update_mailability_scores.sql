DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='PHYSICAL_ADDR_MAILABILITY_SCORE'
    ) THEN
      UPDATE table_column_info SET title = 'Physical Addr Mailability Score'
        WHERE table_name='PROCESS_DATA' and column_name='PHYSICAL_ADDR_MAILABILITY_SCORE';
    END IF;
END$$;

DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='MAIL_ADDR_MAILABILITY_SCORE'
    ) THEN
      UPDATE table_column_info SET title = 'Mail Addr Mailability Score'
        WHERE table_name='PROCESS_DATA' and column_name='MAIL_ADDR_MAILABILITY_SCORE';
    END IF;
END$$;

