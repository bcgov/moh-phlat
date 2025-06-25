-- Remove pcn_clinic_status and related columns
DO $$
BEGIN
    ALTER TABLE source_data DROP COLUMN IF EXISTS pcn_clinic_status;
    ALTER TABLE source_data DROP COLUMN IF EXISTS plr_pcn_clinic_status;
    ALTER TABLE source_data DROP COLUMN IF EXISTS plr_pcn_clinic_status_effective_start_date;
    ALTER TABLE source_data DROP COLUMN IF EXISTS plr_pcn_clinic_status_effective_end_date;
    ALTER TABLE process_data DROP COLUMN IF EXISTS pcn_clinic_status;
    ALTER TABLE process_data DROP COLUMN IF EXISTS plr_pcn_clinic_status;
    ALTER TABLE process_data DROP COLUMN IF EXISTS plr_pcn_clinic_status_effective_start_date;
    ALTER TABLE process_data DROP COLUMN IF EXISTS plr_pcn_clinic_status_effective_end_date;
    
    DELETE FROM table_column_info WHERE table_name = 'SOURCE_DATA' AND column_name = 'PCN_CLINIC_STATUS';
    DELETE FROM table_column_info WHERE table_name = 'SOURCE_DATA' AND column_name = 'PLR_PCN_CLINIC_STATUS';
    DELETE FROM table_column_info WHERE table_name = 'SOURCE_DATA' AND column_name = 'PLR_PCN_CLINIC_STATUS_EFFECTIVE_START_DATE';
    DELETE FROM table_column_info WHERE table_name = 'SOURCE_DATA' AND column_name = 'PLR_PCN_CLINIC_STATUS_EFFECTIVE_END_DATE';
    DELETE FROM table_column_info WHERE table_name = 'PROCESS_DATA' AND column_name = 'PCN_CLINIC_STATUS';
    DELETE FROM table_column_info WHERE table_name = 'PROCESS_DATA' AND column_name = 'PLR_PCN_CLINIC_STATUS';
    DELETE FROM table_column_info WHERE table_name = 'PROCESS_DATA' AND column_name = 'PLR_PCN_CLINIC_STATUS_EFFECTIVE_START_DATE';
    DELETE FROM table_column_info WHERE table_name = 'PROCESS_DATA' AND column_name = 'PLR_PCN_CLINIC_STATUS_EFFECTIVE_END_DATE';
END$$;

-- Update column sizes
DO $$
BEGIN
    ALTER TABLE source_data ALTER COLUMN hds_website TYPE VARCHAR(150);
	ALTER TABLE source_data ALTER COLUMN plr_hds_sub_type TYPE VARCHAR(50);
    ALTER TABLE source_data ALTER COLUMN plr_source_status TYPE VARCHAR(50);
    ALTER TABLE source_data ALTER COLUMN plr_hds_name TYPE VARCHAR(255);
    ALTER TABLE source_data ALTER COLUMN plr_hds_email TYPE VARCHAR(255);
    ALTER TABLE source_data ALTER COLUMN plr_hds_website TYPE VARCHAR(255);
    
    ALTER TABLE process_data ALTER COLUMN hds_website TYPE VARCHAR(150);
    ALTER TABLE process_data ALTER COLUMN plr_hds_sub_type TYPE VARCHAR(50);
    ALTER TABLE process_data ALTER COLUMN plr_source_status TYPE VARCHAR(50);
    ALTER TABLE process_data ALTER COLUMN plr_hds_name TYPE VARCHAR(255);
    ALTER TABLE process_data ALTER COLUMN plr_hds_email TYPE VARCHAR(255);
    ALTER TABLE process_data ALTER COLUMN plr_hds_website TYPE VARCHAR(255);
END$$;

-- Update mail_bc to mail_province
DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='mail_bc'
    ) THEN
        ALTER TABLE source_data
        RENAME COLUMN mail_bc TO mail_province;
    END IF;
END$$;

DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='mail_bc'
    ) THEN
        ALTER TABLE process_data
        RENAME COLUMN mail_bc TO mail_province;
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='MAIL_PROVINCE'
    ) THEN
      UPDATE table_column_info
          SET column_name='MAIL_PROVINCE', header_name='MAIL_PROVINCE', variable_name='mailProvince', title='Mail Province'
          WHERE table_name='SOURCE_DATA' AND column_name='MAIL_BC';
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='MAIL_PROVINCE'
    ) THEN
      UPDATE table_column_info
          SET column_name='MAIL_PROVINCE', header_name='MAIL_PROVINCE', variable_name='mailProvince', title='Mail Province'
          WHERE table_name='PROCESS_DATA' AND column_name='MAIL_BC';
    END IF;
END$$;

-- Remove PLR_PHYSICAL_ADDRESS and PLR_MAILING_ADDRESS columns
DO $$
BEGIN
    ALTER TABLE source_data DROP COLUMN IF EXISTS plr_physical_address;
    ALTER TABLE source_data DROP COLUMN IF EXISTS plr_mailing_address;
    ALTER TABLE process_data DROP COLUMN IF EXISTS plr_physical_address;
    ALTER TABLE process_data DROP COLUMN IF EXISTS plr_mailing_address;
    
    DELETE FROM table_column_info WHERE table_name = 'SOURCE_DATA' AND column_name = 'PLR_PHYSICAL_ADDRESS';
    DELETE FROM table_column_info WHERE table_name = 'SOURCE_DATA' AND column_name = 'PLR_MAILING_ADDRESS';
    DELETE FROM table_column_info WHERE table_name = 'PROCESS_DATA' AND column_name = 'PLR_PHYSICAL_ADDRESS';
    DELETE FROM table_column_info WHERE table_name = 'PROCESS_DATA' AND column_name = 'PLR_MAILING_ADDRESS';
END$$;