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

DO $$
BEGIN
    ALTER TABLE source_data ALTER COLUMN plr_hds_sub_type TYPE VARCHAR(50);
    ALTER TABLE source_data ALTER COLUMN plr_hds_name TYPE VARCHAR(255);
    ALTER TABLE source_data ALTER COLUMN plr_hds_email TYPE VARCHAR(255);
    ALTER TABLE source_data ALTER COLUMN plr_hds_website TYPE VARCHAR(255);
    ALTER TABLE source_data ALTER COLUMN plr_physical_address TYPE VARCHAR(255);
    ALTER TABLE source_data ALTER COLUMN plr_mailing_address TYPE VARCHAR(255);
    
    ALTER TABLE process_data ALTER COLUMN plr_hds_sub_type TYPE VARCHAR(50);
    ALTER TABLE process_data ALTER COLUMN plr_hds_name TYPE VARCHAR(255);
    ALTER TABLE process_data ALTER COLUMN plr_hds_email TYPE VARCHAR(255);
    ALTER TABLE process_data ALTER COLUMN plr_hds_website TYPE VARCHAR(255);
    ALTER TABLE process_data ALTER COLUMN plr_physical_address TYPE VARCHAR(255);
    ALTER TABLE process_data ALTER COLUMN plr_mailing_address TYPE VARCHAR(255);
END$$;