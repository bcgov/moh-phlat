--delete earlier records from status table
delete from status;

--delete type column
DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_name = 'status' AND column_name = 'type'
    ) THEN
        ALTER TABLE status
        DROP COLUMN type;
    END IF;
END $$;

-- increase the size of the description column
DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_name = 'status' AND column_name = 'description'
    ) THEN
        ALTER TABLE status
        ALTER COLUMN description TYPE VARCHAR(500);
    END IF;
END $$;


INSERT INTO status (code, description) VALUES
('INITIAL', 'Source file upload complete; the system assigns a starting row status value for each record in the PHLAT Process File. System set.'),
('DO_NOT_LOAD', 'Indicates do not load the selected record into PLR. May be user or system set.'),
('ON_HOLD', 'Indicates do not load the selected record into PLR. May be user or system set.'),
('VALID', 'Indicates the PHLAT file pre-validation has completed, and the record is ready to be loaded into PLR. System set.'),
('INVALID', 'Indicates an error and requires user correction. An INVALID Status must have 1 or more ERROR codes assigned to it.'),
('WARNING', 'Indicates the record requires user review to identify any invalid information. The record must have 1 or more WARNING codes assigned to it and no error codes. The record must be either updated and re-validated or the user may override the ROWSTATUS code and change the ROWSTATUS to ''VALID'' or ''DO_NOT_LOAD''.'),
('POTENTIAL_DUPLICATE', 'Indicates the record may be a duplicate record and requires user review to verify duplicate entity (Facility, HDS, BUS, etc) and if so, choose the survivor and non-survivor. May have 1 or more WARNING codes assigned to it.'),
('COMPLETED', 'Once the System has completed the data load into PLR, the System sets the PHLAT Control file STATUS field to ''PLR_LOAD_COMPLETED'' and also sets the ROWSTATUS field of each record contained in the PHLAT Process file ''COMPLETED''.'),
('LOAD_ERROR', 'Data load into PLR was not able to complete. The System sets the PHLAT Control table STATUS field to ''PLR_LOAD_ERROR'' and the PHLAT Process file, each record ROWSTATUS field is set to ''LOAD_ERROR''.');