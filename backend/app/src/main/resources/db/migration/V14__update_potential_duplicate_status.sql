DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM row_status
        WHERE code='POTENTIAL_FAC_DUPLICATE'
    ) THEN
      INSERT INTO row_status (code, description) VALUES
      ('POTENTIAL_FAC_DUPLICATE','Outcomes of record validations indicates potential duplicate facility record and requires user review to verify duplicate facility. If the record has 1 or more potential duplicate facility validation codes and no associated Error Codes, the ROWSTATUS is set to POTENTIAL_FAC_DUPLICATE. Records flagged as Potential Facility Duplicate must have 2 or more potential duplicate facility records and may have one or more WARNING codes assigned to it. The user may override the record status and manually change the status to either VALID, ON_HOLD, DO_NOT_LOAD.');
    END IF;
END$$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM row_status
        WHERE code='POTENTIAL_HDS_DUPLICATE'
    ) THEN
      INSERT INTO row_status (code, description) VALUES
      ('POTENTIAL_HDS_DUPLICATE','Outcomes of record validations indicates potential duplicate HDS provider record and requires user review to verify duplicate HDS. If the record has 1 or more potential duplicate HDS validation codes and no associated Error Codes, the ROWSTATUS is set to POTENTIAL_HDS_DUPLICATE. Records flagged as Potential HDS Duplicate must have 2 or more potential duplicate HDS records and may have one or more WARNING codes assigned to it. The user may override the record status and manually change the status to either VALID, ON_HOLD, DO_NOT_LOAD.');
    END IF;
END$$;