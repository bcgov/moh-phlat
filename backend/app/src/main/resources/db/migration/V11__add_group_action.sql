DO $$
BEGIN
     ALTER TABLE table_column_info ALTER COLUMN column_name TYPE VARCHAR(50);
     ALTER TABLE table_column_info ALTER COLUMN header_name TYPE VARCHAR(50);
     ALTER TABLE table_column_info ALTER COLUMN variable_name TYPE VARCHAR(50);
     ALTER TABLE table_column_info ALTER COLUMN title TYPE VARCHAR(50);
 END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PRIMARY_CARE_GROUP_ACTION'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PRIMARY_CARE_GROUP_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PRIMARY_CARE_GROUP_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PRIMARY_CARE_GROUP_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PRIMARY_CARE_GROUP_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PRIMARY_CARE_GROUP_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='HDS_SUB_TYPE_GROUP_ACTION'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN HDS_SUB_TYPE_GROUP_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='HDS_SUB_TYPE_GROUP_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN HDS_SUB_TYPE_GROUP_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='HDS_SUB_TYPE_GROUP_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN HDS_SUB_TYPE_GROUP_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='HDS_NAME_GROUP_ACTION'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN HDS_NAME_GROUP_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='HDS_NAME_GROUP_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN HDS_NAME_GROUP_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='HDS_NAME_GROUP_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN HDS_NAME_GROUP_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='STATUS_GROUP_ACTION'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN STATUS_GROUP_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='STATUS_GROUP_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN STATUS_GROUP_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='STATUS_GROUP_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN STATUS_GROUP_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='HDS_EMAIL_GROUP_ACTION'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN HDS_EMAIL_GROUP_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='HDS_EMAIL_GROUP_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN HDS_EMAIL_GROUP_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='HDS_EMAIL_GROUP_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN HDS_EMAIL_GROUP_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='HDS_WEBSITE_GROUP_ACTION'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN HDS_WEBSITE_GROUP_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='HDS_WEBSITE_GROUP_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN HDS_WEBSITE_GROUP_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='HDS_WEBSITE_GROUP_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN HDS_WEBSITE_GROUP_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='BUSINESS_PHONE_GROUP_ACTION'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN BUSINESS_PHONE_GROUP_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='BUSINESS_PHONE_GROUP_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN BUSINESS_PHONE_GROUP_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='BUSINESS_PHONE_GROUP_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN BUSINESS_PHONE_GROUP_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='HDS_FAX_GROUP_ACTION'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN HDS_FAX_GROUP_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='HDS_FAX_GROUP_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN HDS_FAX_GROUP_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='HDS_FAX_GROUP_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN HDS_FAX_GROUP_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='HDS_CELL_GROUP_ACTION'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN HDS_CELL_GROUP_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='HDS_CELL_GROUP_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN HDS_CELL_GROUP_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='HDS_CELL_GROUP_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN HDS_CELL_GROUP_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PHYSICAL_ADDRESS_GROUP_ACTION'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PHYSICAL_ADDRESS_GROUP_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PHYSICAL_ADDRESS_GROUP_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PHYSICAL_ADDRESS_GROUP_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PHYSICAL_ADDRESS_GROUP_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PHYSICAL_ADDRESS_GROUP_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='MAILING_ADDRESS_GROUP_ACTION'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN MAILING_ADDRESS_GROUP_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='MAILING_ADDRESS_GROUP_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN MAILING_ADDRESS_GROUP_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='MAILING_ADDRESS_GROUP_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN MAILING_ADDRESS_GROUP_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='RECORD_ACTION'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN RECORD_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_HDS_SUB_TYPE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_HDS_SUB_TYPE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_HDS_SUB_TYPE_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_HDS_SUB_TYPE_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_HDS_SUB_TYPE_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_HDS_SUB_TYPE_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_HDS_NAME'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_HDS_NAME VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_HDS_NAME_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_HDS_NAME_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_HDS_NAME_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_HDS_NAME_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_SOURCE_STATUS'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_SOURCE_STATUS VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_SOURCE_STATUS_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_SOURCE_STATUS_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_SOURCE_STATUS_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_SOURCE_STATUS_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_PCN_CLINIC_STATUS'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_PCN_CLINIC_STATUS VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_PCN_CLINIC_STATUS_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_PCN_CLINIC_STATUS_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_PCN_CLINIC_STATUS_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_PCN_CLINIC_STATUS_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_HDS_EMAIL'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_HDS_EMAIL VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_HDS_EMAIL_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_HDS_EMAIL_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_HDS_EMAIL_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_HDS_EMAIL_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_HDS_WEBSITE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_HDS_WEBSITE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_HDS_WEBSITE_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_HDS_WEBSITE_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_HDS_WEBSITE_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_HDS_WEBSITE_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_HDS_BUSINESS_PHONE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_HDS_BUSINESS_PHONE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_BUSINESS_PHONE_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_BUSINESS_PHONE_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_BUSINESS_PHONE_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_BUSINESS_PHONE_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_HDS_FAX'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_HDS_FAX VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_HDS_FAX_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_HDS_FAX_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_HDS_FAX_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_HDS_FAX_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_HDS_CELL'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_HDS_CELL VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_HDS_CELL_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_HDS_CELL_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_HDS_CELL_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_HDS_CELL_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_PHYSICAL_ADDRESS'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_PHYSICAL_ADDRESS VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_PHYSICAL_ADDRESS_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_PHYSICAL_ADDRESS_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_PHYSICAL_ADDRESS_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_PHYSICAL_ADDRESS_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_MAILING_ADDRESS'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_MAILING_ADDRESS VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_MAILING_ADDRESS_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_MAILING_ADDRESS_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PLR_MAILING_ADDRESS_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PLR_MAILING_ADDRESS_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='HDS_SUB_TYPE_PROPERTY_CHID'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN HDS_SUB_TYPE_PROPERTY_CHID VARCHAR(15);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PCN_SERVICE_DELIVERY_TYPE_PROPERTY_CHID'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PCN_SERVICE_DELIVERY_TYPE_PROPERTY_CHID VARCHAR(15);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PCN_CLINIC_TYPE_PROPERTY_CHID'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PCN_CLINIC_TYPE_PROPERTY_CHID VARCHAR(15);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='PCN_PCI_FLAG_PROPERTY_CHID'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN PCN_PCI_FLAG_PROPERTY_CHID VARCHAR(15);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='source_data' and column_name='FAC_ADDRESS_UNIT_PROPERTY_CHID'
    ) THEN
        ALTER TABLE source_data
        ADD COLUMN FAC_ADDRESS_UNIT_PROPERTY_CHID VARCHAR(15);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PRIMARY_CARE_GROUP_ACTION'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PRIMARY_CARE_GROUP_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PRIMARY_CARE_GROUP_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PRIMARY_CARE_GROUP_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PRIMARY_CARE_GROUP_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PRIMARY_CARE_GROUP_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='HDS_SUB_TYPE_GROUP_ACTION'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN HDS_SUB_TYPE_GROUP_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='HDS_SUB_TYPE_GROUP_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN HDS_SUB_TYPE_GROUP_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='HDS_SUB_TYPE_GROUP_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN HDS_SUB_TYPE_GROUP_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='HDS_NAME_GROUP_ACTION'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN HDS_NAME_GROUP_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='HDS_NAME_GROUP_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN HDS_NAME_GROUP_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='HDS_NAME_GROUP_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN HDS_NAME_GROUP_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='STATUS_GROUP_ACTION'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN STATUS_GROUP_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='STATUS_GROUP_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN STATUS_GROUP_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='STATUS_GROUP_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN STATUS_GROUP_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='HDS_EMAIL_GROUP_ACTION'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN HDS_EMAIL_GROUP_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='HDS_EMAIL_GROUP_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN HDS_EMAIL_GROUP_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='HDS_EMAIL_GROUP_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN HDS_EMAIL_GROUP_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='HDS_WEBSITE_GROUP_ACTION'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN HDS_WEBSITE_GROUP_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='HDS_WEBSITE_GROUP_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN HDS_WEBSITE_GROUP_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='HDS_WEBSITE_GROUP_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN HDS_WEBSITE_GROUP_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='BUSINESS_PHONE_GROUP_ACTION'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN BUSINESS_PHONE_GROUP_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='BUSINESS_PHONE_GROUP_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN BUSINESS_PHONE_GROUP_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='BUSINESS_PHONE_GROUP_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN BUSINESS_PHONE_GROUP_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='HDS_FAX_GROUP_ACTION'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN HDS_FAX_GROUP_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='HDS_FAX_GROUP_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN HDS_FAX_GROUP_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='HDS_FAX_GROUP_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN HDS_FAX_GROUP_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='HDS_CELL_GROUP_ACTION'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN HDS_CELL_GROUP_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='HDS_CELL_GROUP_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN HDS_CELL_GROUP_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='HDS_CELL_GROUP_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN HDS_CELL_GROUP_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PHYSICAL_ADDRESS_GROUP_ACTION'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PHYSICAL_ADDRESS_GROUP_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PHYSICAL_ADDRESS_GROUP_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PHYSICAL_ADDRESS_GROUP_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PHYSICAL_ADDRESS_GROUP_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PHYSICAL_ADDRESS_GROUP_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='MAILING_ADDRESS_GROUP_ACTION'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN MAILING_ADDRESS_GROUP_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='MAILING_ADDRESS_GROUP_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN MAILING_ADDRESS_GROUP_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='MAILING_ADDRESS_GROUP_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN MAILING_ADDRESS_GROUP_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='RECORD_ACTION'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN RECORD_ACTION VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_HDS_SUB_TYPE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_HDS_SUB_TYPE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_HDS_SUB_TYPE_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_HDS_SUB_TYPE_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_HDS_SUB_TYPE_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_HDS_SUB_TYPE_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_HDS_NAME'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_HDS_NAME VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_HDS_NAME_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_HDS_NAME_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_HDS_NAME_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_HDS_NAME_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_SOURCE_STATUS'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_SOURCE_STATUS VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_SOURCE_STATUS_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_SOURCE_STATUS_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_SOURCE_STATUS_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_SOURCE_STATUS_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_PCN_CLINIC_STATUS'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_PCN_CLINIC_STATUS VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_PCN_CLINIC_STATUS_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_PCN_CLINIC_STATUS_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_PCN_CLINIC_STATUS_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_PCN_CLINIC_STATUS_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_HDS_EMAIL'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_HDS_EMAIL VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_HDS_EMAIL_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_HDS_EMAIL_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_HDS_EMAIL_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_HDS_EMAIL_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_HDS_WEBSITE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_HDS_WEBSITE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_HDS_WEBSITE_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_HDS_WEBSITE_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_HDS_WEBSITE_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_HDS_WEBSITE_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_HDS_BUSINESS_PHONE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_HDS_BUSINESS_PHONE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_BUSINESS_PHONE_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_BUSINESS_PHONE_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_BUSINESS_PHONE_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_BUSINESS_PHONE_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_HDS_FAX'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_HDS_FAX VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_HDS_FAX_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_HDS_FAX_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_HDS_FAX_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_HDS_FAX_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_HDS_CELL'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_HDS_CELL VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_HDS_CELL_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_HDS_CELL_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_HDS_CELL_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_HDS_CELL_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_PHYSICAL_ADDRESS'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_PHYSICAL_ADDRESS VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_PHYSICAL_ADDRESS_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_PHYSICAL_ADDRESS_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_PHYSICAL_ADDRESS_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_PHYSICAL_ADDRESS_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_MAILING_ADDRESS'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_MAILING_ADDRESS VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_MAILING_ADDRESS_EFFECTIVE_START_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_MAILING_ADDRESS_EFFECTIVE_START_DATE VARCHAR(10);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PLR_MAILING_ADDRESS_EFFECTIVE_END_DATE'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PLR_MAILING_ADDRESS_EFFECTIVE_END_DATE VARCHAR(10);
    END IF;
END$$;


DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='HDS_SUB_TYPE_PROPERTY_CHID'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN HDS_SUB_TYPE_PROPERTY_CHID VARCHAR(15);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PCN_SERVICE_DELIVERY_TYPE_PROPERTY_CHID'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PCN_SERVICE_DELIVERY_TYPE_PROPERTY_CHID VARCHAR(15);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PCN_CLINIC_TYPE_PROPERTY_CHID'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PCN_CLINIC_TYPE_PROPERTY_CHID VARCHAR(15);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='PCN_PCI_FLAG_PROPERTY_CHID'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN PCN_PCI_FLAG_PROPERTY_CHID VARCHAR(15);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='process_data' and column_name='FAC_ADDRESS_UNIT_PROPERTY_CHID'
    ) THEN
        ALTER TABLE process_data
        ADD COLUMN FAC_ADDRESS_UNIT_PROPERTY_CHID VARCHAR(15);
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='PRIMARY_CARE_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'PRIMARY_CARE_GROUP_ACTION', 'PRIMARY_CARE_GROUP_ACTION', 'primaryCareGroupAction','Primary Care Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='PRIMARY_CARE_GROUP_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'PRIMARY_CARE_GROUP_EFFECTIVE_START_DATE', 'PRIMARY_CARE_GROUP_EFFECTIVE_START_DATE', 'primaryCareGroupEffectiveStartDate','Primary Care Group Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='PRIMARY_CARE_GROUP_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'PRIMARY_CARE_GROUP_EFFECTIVE_END_DATE', 'PRIMARY_CARE_GROUP_EFFECTIVE_END_DATE', 'primaryCareGroupEffectiveEndDate','Primary Care Group Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='HDS_SUB_TYPE_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'HDS_SUB_TYPE_GROUP_ACTION', 'HDS_SUB_TYPE_GROUP_ACTION', 'hdsSubTypeGroupAction','Hds Sub Type Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='HDS_SUB_TYPE_GROUP_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'HDS_SUB_TYPE_GROUP_EFFECTIVE_START_DATE', 'HDS_SUB_TYPE_GROUP_EFFECTIVE_START_DATE', 'hdsSubTypeGroupEffectiveStartDate','Hds Sub Type Group Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='HDS_SUB_TYPE_GROUP_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'HDS_SUB_TYPE_GROUP_EFFECTIVE_END_DATE', 'HDS_SUB_TYPE_GROUP_EFFECTIVE_END_DATE', 'hdsSubTypeGroupEffectiveEndDate','Hds Sub Type Group Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='HDS_NAME_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'HDS_NAME_GROUP_ACTION', 'HDS_NAME_GROUP_ACTION', 'hdsNameGroupAction','Hds Name Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='HDS_NAME_GROUP_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'HDS_NAME_GROUP_EFFECTIVE_START_DATE', 'HDS_NAME_GROUP_EFFECTIVE_START_DATE', 'hdsNameGroupEffectiveStartDate','Hds Name Group Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='HDS_NAME_GROUP_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'HDS_NAME_GROUP_EFFECTIVE_END_DATE', 'HDS_NAME_GROUP_EFFECTIVE_END_DATE', 'hdsNameGroupEffectiveEndDate','Hds Name Group Effective End Date'
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
        WHERE table_name='SOURCE_DATA' and column_name='STATUS_GROUP_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'STATUS_GROUP_EFFECTIVE_START_DATE', 'STATUS_GROUP_EFFECTIVE_START_DATE', 'statusGroupEffectiveStartDate','Status Group Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='STATUS_GROUP_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'STATUS_GROUP_EFFECTIVE_END_DATE', 'STATUS_GROUP_EFFECTIVE_END_DATE', 'statusGroupEffectiveEndDate','Status Group Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='HDS_EMAIL_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'HDS_EMAIL_GROUP_ACTION', 'HDS_EMAIL_GROUP_ACTION', 'hdsEmailGroupAction','Hds Email Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='HDS_EMAIL_GROUP_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'HDS_EMAIL_GROUP_EFFECTIVE_START_DATE', 'HDS_EMAIL_GROUP_EFFECTIVE_START_DATE', 'hdsEmailGroupEffectiveStartDate','Hds Email Group Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='HDS_EMAIL_GROUP_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'HDS_EMAIL_GROUP_EFFECTIVE_END_DATE', 'HDS_EMAIL_GROUP_EFFECTIVE_END_DATE', 'hdsEmailGroupEffectiveEndDate','Hds Email Group Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='HDS_WEBSITE_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'HDS_WEBSITE_GROUP_ACTION', 'HDS_WEBSITE_GROUP_ACTION', 'hdsWebsiteGroupAction','Hds Website Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='HDS_WEBSITE_GROUP_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'HDS_WEBSITE_GROUP_EFFECTIVE_START_DATE', 'HDS_WEBSITE_GROUP_EFFECTIVE_START_DATE', 'hdsWebsiteGroupEffectiveStartDate','Hds Website Group Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='HDS_WEBSITE_GROUP_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'HDS_WEBSITE_GROUP_EFFECTIVE_END_DATE', 'HDS_WEBSITE_GROUP_EFFECTIVE_END_DATE', 'hdsWebsiteGroupEffectiveEndDate','Hds Website Group Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='BUSINESS_PHONE_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'BUSINESS_PHONE_GROUP_ACTION', 'BUSINESS_PHONE_GROUP_ACTION', 'businessPhoneGroupAction','Business Phone Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='BUSINESS_PHONE_GROUP_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'BUSINESS_PHONE_GROUP_EFFECTIVE_START_DATE', 'BUSINESS_PHONE_GROUP_EFFECTIVE_START_DATE', 'businessPhoneGroupEffectiveStartDate','Business Phone Group Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='BUSINESS_PHONE_GROUP_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'BUSINESS_PHONE_GROUP_EFFECTIVE_END_DATE', 'BUSINESS_PHONE_GROUP_EFFECTIVE_END_DATE', 'businessPhoneGroupEffectiveEndDate','Business Phone Group Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='HDS_FAX_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'HDS_FAX_GROUP_ACTION', 'HDS_FAX_GROUP_ACTION', 'hdsFaxGroupAction','Hds Fax Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='HDS_FAX_GROUP_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'HDS_FAX_GROUP_EFFECTIVE_START_DATE', 'HDS_FAX_GROUP_EFFECTIVE_START_DATE', 'hdsFaxGroupEffectiveStartDate','Hds Fax Group Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='HDS_FAX_GROUP_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'HDS_FAX_GROUP_EFFECTIVE_END_DATE', 'HDS_FAX_GROUP_EFFECTIVE_END_DATE', 'hdsFaxGroupEffectiveEndDate','Hds Fax Group Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='HDS_CELL_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'HDS_CELL_GROUP_ACTION', 'HDS_CELL_GROUP_ACTION', 'hdsCellGroupAction','Hds Cell Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='HDS_CELL_GROUP_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'HDS_CELL_GROUP_EFFECTIVE_START_DATE', 'HDS_CELL_GROUP_EFFECTIVE_START_DATE', 'hdsCellGroupEffectiveStartDate','Hds Cell Group Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='HDS_CELL_GROUP_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'HDS_CELL_GROUP_EFFECTIVE_END_DATE', 'HDS_CELL_GROUP_EFFECTIVE_END_DATE', 'hdsCellGroupEffectiveEndDate','Hds Cell Group Effective End Date'
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
        WHERE table_name='SOURCE_DATA' and column_name='PHYSICAL_ADDRESS_GROUP_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'PHYSICAL_ADDRESS_GROUP_EFFECTIVE_START_DATE', 'PHYSICAL_ADDRESS_GROUP_EFFECTIVE_START_DATE', 'physicalAddressGroupEffectiveStartDate','Physical Address Group Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='PHYSICAL_ADDRESS_GROUP_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'PHYSICAL_ADDRESS_GROUP_EFFECTIVE_END_DATE', 'PHYSICAL_ADDRESS_GROUP_EFFECTIVE_END_DATE', 'physicalAddressGroupEffectiveEndDate','Physical Address Group Effective End Date'
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
        WHERE table_name='SOURCE_DATA' and column_name='MAILING_ADDRESS_GROUP_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'MAILING_ADDRESS_GROUP_EFFECTIVE_START_DATE', 'MAILING_ADDRESS_GROUP_EFFECTIVE_START_DATE', 'mailingAddressGroupEffectiveStartDate','Mailing Address Group Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='MAILING_ADDRESS_GROUP_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'MAILING_ADDRESS_GROUP_EFFECTIVE_END_DATE', 'MAILING_ADDRESS_GROUP_EFFECTIVE_END_DATE', 'mailingAddressGroupEffectiveEndDate','Mailing Address Group Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' and column_name='RECORD_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'SOURCE_DATA', 'RECORD_ACTION', 'RECORD_ACTION', 'recordAction','Record Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_HDS_SUB_TYPE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_HDS_SUB_TYPE', 'PLR_HDS_SUB_TYPE', 'plrHdsSubType', 'Plr Hds Sub Type'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_HDS_SUB_TYPE_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_HDS_SUB_TYPE_EFFECTIVE_START_DATE', 'PLR_HDS_SUB_TYPE_EFFECTIVE_START_DATE', 'plrHdsSubTypeEffectiveStartDate', 'Plr Hds Sub Type Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_HDS_SUB_TYPE_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_HDS_SUB_TYPE_EFFECTIVE_END_DATE', 'PLR_HDS_SUB_TYPE_EFFECTIVE_END_DATE', 'plrHdsSubTypeEffectiveEndDate', 'Plr Hds Sub Type Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_HDS_NAME'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_HDS_NAME', 'PLR_HDS_NAME', 'plrHdsName', 'Plr Hds Name'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_HDS_NAME_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_HDS_NAME_EFFECTIVE_START_DATE', 'PLR_HDS_NAME_EFFECTIVE_START_DATE', 'plrHdsNameEffectiveStartDate', 'Plr Hds Name Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_HDS_NAME_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_HDS_NAME_EFFECTIVE_END_DATE', 'PLR_HDS_NAME_EFFECTIVE_END_DATE', 'plrHdsNameEffectiveEndDate', 'Plr Hds Name Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_SOURCE_STATUS'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_SOURCE_STATUS', 'PLR_SOURCE_STATUS', 'plrSourceStatus', 'Plr Source Status'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_SOURCE_STATUS_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_SOURCE_STATUS_EFFECTIVE_START_DATE', 'PLR_SOURCE_STATUS_EFFECTIVE_START_DATE', 'plrSourceStatusEffectiveStartDate', 'Plr Source Status Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_SOURCE_STATUS_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_SOURCE_STATUS_EFFECTIVE_END_DATE', 'PLR_SOURCE_STATUS_EFFECTIVE_END_DATE', 'plrSourceStatusEffectiveEndDate', 'Plr Source Status Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_PCN_CLINIC_STATUS'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_PCN_CLINIC_STATUS', 'PLR_PCN_CLINIC_STATUS', 'plrPcnClinicStatus', 'Plr Pcn Clinic Status'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_PCN_CLINIC_STATUS_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_PCN_CLINIC_STATUS_EFFECTIVE_START_DATE', 'PLR_PCN_CLINIC_STATUS_EFFECTIVE_START_DATE', 'plrPcnClinicStatusEffectiveStartDate', 'Plr Pcn Clinic Status Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_PCN_CLINIC_STATUS_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_PCN_CLINIC_STATUS_EFFECTIVE_END_DATE', 'PLR_PCN_CLINIC_STATUS_EFFECTIVE_END_DATE', 'plrPcnClinicStatusEffectiveEndDate', 'Plr Pcn Clinic Status Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_HDS_EMAIL'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_HDS_EMAIL', 'PLR_HDS_EMAIL', 'plrHdsEmail', 'Plr Hds Email'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_HDS_EMAIL_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_HDS_EMAIL_EFFECTIVE_START_DATE', 'PLR_HDS_EMAIL_EFFECTIVE_START_DATE', 'plrHdsEmailEffectiveStartDate', 'Plr Hds Email Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_HDS_EMAIL_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_HDS_EMAIL_EFFECTIVE_END_DATE', 'PLR_HDS_EMAIL_EFFECTIVE_END_DATE', 'plrHdsEmailEffectiveEndDate', 'Plr Hds Email Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_HDS_WEBSITE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_HDS_WEBSITE', 'PLR_HDS_WEBSITE', 'plrHdsWebsite', 'Plr Hds Website'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_HDS_WEBSITE_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_HDS_WEBSITE_EFFECTIVE_START_DATE', 'PLR_HDS_WEBSITE_EFFECTIVE_START_DATE', 'plrHdsWebsiteEffectiveStartDate', 'Plr Hds Website Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_HDS_WEBSITE_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_HDS_WEBSITE_EFFECTIVE_END_DATE', 'PLR_HDS_WEBSITE_EFFECTIVE_END_DATE', 'plrHdsWebsiteEffectiveEndDate', 'Plr Hds Website Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_HDS_BUSINESS_PHONE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_HDS_BUSINESS_PHONE', 'PLR_HDS_BUSINESS_PHONE', 'plrHdsBusinessPhone', 'Plr Hds Business Phone'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_BUSINESS_PHONE_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_BUSINESS_PHONE_EFFECTIVE_START_DATE', 'PLR_BUSINESS_PHONE_EFFECTIVE_START_DATE', 'plrBusinessPhoneEffectiveStartDate', 'Plr Business Phone Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_BUSINESS_PHONE_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_BUSINESS_PHONE_EFFECTIVE_END_DATE', 'PLR_BUSINESS_PHONE_EFFECTIVE_END_DATE', 'plrBusinessPhoneEffectiveEndDate', 'Plr Business Phone Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_HDS_FAX'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_HDS_FAX', 'PLR_HDS_FAX', 'plrHdsFax', 'Plr Hds Fax'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_HDS_FAX_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_HDS_FAX_EFFECTIVE_START_DATE', 'PLR_HDS_FAX_EFFECTIVE_START_DATE', 'plrHdsFaxEffectiveStartDate', 'Plr Hds Fax Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_HDS_FAX_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_HDS_FAX_EFFECTIVE_END_DATE', 'PLR_HDS_FAX_EFFECTIVE_END_DATE', 'plrHdsFaxEffectiveEndDate', 'Plr Hds Fax Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_HDS_CELL'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_HDS_CELL', 'PLR_HDS_CELL', 'plrHdsCell', 'Plr Hds Cell'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_HDS_CELL_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_HDS_CELL_EFFECTIVE_START_DATE', 'PLR_HDS_CELL_EFFECTIVE_START_DATE', 'plrHdsCellEffectiveStartDate', 'Plr Hds Cell Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_HDS_CELL_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_HDS_CELL_EFFECTIVE_END_DATE', 'PLR_HDS_CELL_EFFECTIVE_END_DATE', 'plrHdsCellEffectiveEndDate', 'Plr Hds Cell Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_PHYSICAL_ADDRESS'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_PHYSICAL_ADDRESS', 'PLR_PHYSICAL_ADDRESS', 'plrPhysicalAddress', 'Plr Physical Address'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_PHYSICAL_ADDRESS_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_PHYSICAL_ADDRESS_EFFECTIVE_START_DATE', 'PLR_PHYSICAL_ADDRESS_EFFECTIVE_START_DATE', 'plrPhysicalAddressEffectiveStartDate', 'Plr Physical Address Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_PHYSICAL_ADDRESS_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_PHYSICAL_ADDRESS_EFFECTIVE_END_DATE', 'PLR_PHYSICAL_ADDRESS_EFFECTIVE_END_DATE', 'plrPhysicalAddressEffectiveEndDate', 'Plr Physical Address Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_MAILING_ADDRESS'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_MAILING_ADDRESS', 'PLR_MAILING_ADDRESS', 'plrMailingAddress', 'Plr Mailing Address'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_MAILING_ADDRESS_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_MAILING_ADDRESS_EFFECTIVE_START_DATE', 'PLR_MAILING_ADDRESS_EFFECTIVE_START_DATE', 'plrMailingAddressEffectiveStartDate', 'Plr Mailing Address Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PLR_MAILING_ADDRESS_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PLR_MAILING_ADDRESS_EFFECTIVE_END_DATE', 'PLR_MAILING_ADDRESS_EFFECTIVE_END_DATE', 'plrMailingAddressEffectiveEndDate', 'Plr Mailing Address Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='HDS_SUB_TYPE_PROPERTY_CHID'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'HDS_SUB_TYPE_PROPERTY_CHID', 'HDS_SUB_TYPE_PROPERTY_CHID', 'hdsSubTypePropertyChid', 'Hds Sub Type Property Chid'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PCN_SERVICE_DELIVERY_TYPE_PROPERTY_CHID'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PCN_SERVICE_DELIVERY_TYPE_PROPERTY_CHID', 'PCN_SERVICE_DELIVERY_TYPE_PROPERTY_CHID', 'pcnServiceDeliveryTypePropertyChid', 'Pcn Service Delivery Type Property Chid'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PCN_CLINIC_TYPE_PROPERTY_CHID'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PCN_CLINIC_TYPE_PROPERTY_CHID', 'PCN_CLINIC_TYPE_PROPERTY_CHID', 'pcnClinicTypePropertyChid', 'Pcn Clinic Type Property Chid'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='PCN_PCI_FLAG_PROPERTY_CHID'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'PCN_PCI_FLAG_PROPERTY_CHID', 'PCN_PCI_FLAG_PROPERTY_CHID', 'pcnPciFlagPropertyChid', 'Pcn Pci Flag Property Chid'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='SOURCE_DATA' AND column_name='FAC_ADDRESS_UNIT_PROPERTY_CHID'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name, variable_name, title) VALUES (
          'SOURCE_DATA', 'FAC_ADDRESS_UNIT_PROPERTY_CHID', 'FAC_ADDRESS_UNIT_PROPERTY_CHID', 'facAddressUnitPropertyChid', 'Fac Address Unit Property Chid'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='PRIMARY_CARE_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'PRIMARY_CARE_GROUP_ACTION', 'PRIMARY_CARE_GROUP_ACTION', 'primaryCareGroupAction','Primary Care Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='PRIMARY_CARE_GROUP_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'PRIMARY_CARE_GROUP_EFFECTIVE_START_DATE', 'PRIMARY_CARE_GROUP_EFFECTIVE_START_DATE', 'primaryCareGroupEffectiveStartDate','Primary Care Group Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='PRIMARY_CARE_GROUP_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'PRIMARY_CARE_GROUP_EFFECTIVE_END_DATE', 'PRIMARY_CARE_GROUP_EFFECTIVE_END_DATE', 'primaryCareGroupEffectiveEndDate','Primary Care Group Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='HDS_SUB_TYPE_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'HDS_SUB_TYPE_GROUP_ACTION', 'HDS_SUB_TYPE_GROUP_ACTION', 'hdsSubTypeGroupAction','Hds Sub Type Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='HDS_SUB_TYPE_GROUP_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'HDS_SUB_TYPE_GROUP_EFFECTIVE_START_DATE', 'HDS_SUB_TYPE_GROUP_EFFECTIVE_START_DATE', 'hdsSubTypeGroupEffectiveStartDate','Hds Sub Type Group Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='HDS_SUB_TYPE_GROUP_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'HDS_SUB_TYPE_GROUP_EFFECTIVE_END_DATE', 'HDS_SUB_TYPE_GROUP_EFFECTIVE_END_DATE', 'hdsSubTypeGroupEffectiveEndDate','Hds Sub Type Group Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='HDS_NAME_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'HDS_NAME_GROUP_ACTION', 'HDS_NAME_GROUP_ACTION', 'hdsNameGroupAction','Hds Name Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='HDS_NAME_GROUP_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'HDS_NAME_GROUP_EFFECTIVE_START_DATE', 'HDS_NAME_GROUP_EFFECTIVE_START_DATE', 'hdsNameGroupEffectiveStartDate','Hds Name Group Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='HDS_NAME_GROUP_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'HDS_NAME_GROUP_EFFECTIVE_END_DATE', 'HDS_NAME_GROUP_EFFECTIVE_END_DATE', 'hdsNameGroupEffectiveEndDate','Hds Name Group Effective End Date'
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
        WHERE table_name='PROCESS_DATA' and column_name='STATUS_GROUP_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'STATUS_GROUP_EFFECTIVE_START_DATE', 'STATUS_GROUP_EFFECTIVE_START_DATE', 'statusGroupEffectiveStartDate','Status Group Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='STATUS_GROUP_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'STATUS_GROUP_EFFECTIVE_END_DATE', 'STATUS_GROUP_EFFECTIVE_END_DATE', 'statusGroupEffectiveEndDate','Status Group Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='HDS_EMAIL_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'HDS_EMAIL_GROUP_ACTION', 'HDS_EMAIL_GROUP_ACTION', 'hdsEmailGroupAction','Hds Email Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='HDS_EMAIL_GROUP_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'HDS_EMAIL_GROUP_EFFECTIVE_START_DATE', 'HDS_EMAIL_GROUP_EFFECTIVE_START_DATE', 'hdsEmailGroupEffectiveStartDate','Hds Email Group Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='HDS_EMAIL_GROUP_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'HDS_EMAIL_GROUP_EFFECTIVE_END_DATE', 'HDS_EMAIL_GROUP_EFFECTIVE_END_DATE', 'hdsEmailGroupEffectiveEndDate','Hds Email Group Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='HDS_WEBSITE_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'HDS_WEBSITE_GROUP_ACTION', 'HDS_WEBSITE_GROUP_ACTION', 'hdsWebsiteGroupAction','Hds Website Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='HDS_WEBSITE_GROUP_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'HDS_WEBSITE_GROUP_EFFECTIVE_START_DATE', 'HDS_WEBSITE_GROUP_EFFECTIVE_START_DATE', 'hdsWebsiteGroupEffectiveStartDate','Hds Website Group Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='HDS_WEBSITE_GROUP_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'HDS_WEBSITE_GROUP_EFFECTIVE_END_DATE', 'HDS_WEBSITE_GROUP_EFFECTIVE_END_DATE', 'hdsWebsiteGroupEffectiveEndDate','Hds Website Group Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='BUSINESS_PHONE_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'BUSINESS_PHONE_GROUP_ACTION', 'BUSINESS_PHONE_GROUP_ACTION', 'businessPhoneGroupAction','Business Phone Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='BUSINESS_PHONE_GROUP_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'BUSINESS_PHONE_GROUP_EFFECTIVE_START_DATE', 'BUSINESS_PHONE_GROUP_EFFECTIVE_START_DATE', 'businessPhoneGroupEffectiveStartDate','Business Phone Group Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='BUSINESS_PHONE_GROUP_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'BUSINESS_PHONE_GROUP_EFFECTIVE_END_DATE', 'BUSINESS_PHONE_GROUP_EFFECTIVE_END_DATE', 'businessPhoneGroupEffectiveEndDate','Business Phone Group Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='HDS_FAX_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'HDS_FAX_GROUP_ACTION', 'HDS_FAX_GROUP_ACTION', 'hdsFaxGroupAction','Hds Fax Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='HDS_FAX_GROUP_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'HDS_FAX_GROUP_EFFECTIVE_START_DATE', 'HDS_FAX_GROUP_EFFECTIVE_START_DATE', 'hdsFaxGroupEffectiveStartDate','Hds Fax Group Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='HDS_FAX_GROUP_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'HDS_FAX_GROUP_EFFECTIVE_END_DATE', 'HDS_FAX_GROUP_EFFECTIVE_END_DATE', 'hdsFaxGroupEffectiveEndDate','Hds Fax Group Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='HDS_CELL_GROUP_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'HDS_CELL_GROUP_ACTION', 'HDS_CELL_GROUP_ACTION', 'hdsCellGroupAction','Hds Cell Group Action'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='HDS_CELL_GROUP_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'HDS_CELL_GROUP_EFFECTIVE_START_DATE', 'HDS_CELL_GROUP_EFFECTIVE_START_DATE', 'hdsCellGroupEffectiveStartDate','Hds Cell Group Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='HDS_CELL_GROUP_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'HDS_CELL_GROUP_EFFECTIVE_END_DATE', 'HDS_CELL_GROUP_EFFECTIVE_END_DATE', 'hdsCellGroupEffectiveEndDate','Hds Cell Group Effective End Date'
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
        WHERE table_name='PROCESS_DATA' and column_name='PHYSICAL_ADDRESS_GROUP_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'PHYSICAL_ADDRESS_GROUP_EFFECTIVE_START_DATE', 'PHYSICAL_ADDRESS_GROUP_EFFECTIVE_START_DATE', 'physicalAddressGroupEffectiveStartDate','Physical Address Group Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='PHYSICAL_ADDRESS_GROUP_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'PHYSICAL_ADDRESS_GROUP_EFFECTIVE_END_DATE', 'PHYSICAL_ADDRESS_GROUP_EFFECTIVE_END_DATE', 'physicalAddressGroupEffectiveEndDate','Physical Address Group Effective End Date'
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

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='MAILING_ADDRESS_GROUP_EFFECTIVE_START_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'MAILING_ADDRESS_GROUP_EFFECTIVE_START_DATE', 'MAILING_ADDRESS_GROUP_EFFECTIVE_START_DATE', 'mailingAddressGroupEffectiveStartDate','Mailing Address Group Effective Start Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='MAILING_ADDRESS_GROUP_EFFECTIVE_END_DATE'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'MAILING_ADDRESS_GROUP_EFFECTIVE_END_DATE', 'MAILING_ADDRESS_GROUP_EFFECTIVE_END_DATE', 'mailingAddressGroupEffectiveEndDate','Mailing Address Group Effective End Date'
      );
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM table_column_info
        WHERE table_name='PROCESS_DATA' and column_name='RECORD_ACTION'
    ) THEN
      INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
          'PROCESS_DATA', 'RECORD_ACTION', 'RECORD_ACTION', 'recordAction','Record Action'
      );
    END IF;
END$$;