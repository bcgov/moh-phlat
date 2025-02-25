DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='primary_care_specific_group_action'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN primary_care_specific_group_action VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='contact_info_group_action'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN contact_info_group_action VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='status_group_action'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN status_group_action VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='physical_address_group_action'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN physical_address_group_action VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='mailing_address_group_action'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN mailing_address_group_action VARCHAR(10);
    END IF;
END$$;


DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='primary_care_specific_group_action'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN primary_care_specific_group_action VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='contact_info_group_action'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN contact_info_group_action VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='status_group_action'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN status_group_action VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='physical_address_group_action'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN physical_address_group_action VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='mailing_address_group_action'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN mailing_address_group_action VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='PRIMARY_CARE_SPECIFIC_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'PRIMARY_CARE_SPECIFIC_GROUP_ACTION', 'PRIMARY_CARE_SPECIFIC_GROUP_ACTION', 'primaryCareSpecificGroupAction','Primary Care Specific Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='CONTACT_INFO_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'CONTACT_INFO_GROUP_ACTION', 'CONTACT_INFO_GROUP_ACTION', 'contactInfoGroupAction','Contact Info Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='STATUS_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'STATUS_GROUP_ACTION', 'STATUS_GROUP_ACTION', 'statusGroupAction','Status Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='PHYSICAL_ADDRESS_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'PHYSICAL_ADDRESS_GROUP_ACTION', 'PHYSICAL_ADDRESS_GROUP_ACTION', 'physicalAddressGroupAction','Physical Address Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='MAILING_ADDRESS_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'MAILING_ADDRESS_GROUP_ACTION', 'MAILING_ADDRESS_GROUP_ACTION', 'mailingAddressGroupAction','Mailing Address Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='PRIMARY_CARE_SPECIFIC_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'PRIMARY_CARE_SPECIFIC_GROUP_ACTION', 'PRIMARY_CARE_SPECIFIC_GROUP_ACTION', 'primaryCareSpecificGroupAction','Primary Care Specific Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='CONTACT_INFO_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'CONTACT_INFO_GROUP_ACTION', 'CONTACT_INFO_GROUP_ACTION', 'contactInfoGroupAction','Contact Info Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='STATUS_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'STATUS_GROUP_ACTION', 'STATUS_GROUP_ACTION', 'statusGroupAction','Status Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='PHYSICAL_ADDRESS_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'PHYSICAL_ADDRESS_GROUP_ACTION', 'PHYSICAL_ADDRESS_GROUP_ACTION', 'physicalAddressGroupAction','Physical Address Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='MAILING_ADDRESS_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'MAILING_ADDRESS_GROUP_ACTION', 'MAILING_ADDRESS_GROUP_ACTION', 'mailingAddressGroupAction','Mailing Address Group Action'
      );
    END IF;
END$$;