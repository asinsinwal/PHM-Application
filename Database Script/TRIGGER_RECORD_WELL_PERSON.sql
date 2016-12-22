CREATE OR REPLACE TRIGGER record_well_person AFTER
    INSERT ON person
    FOR EACH ROW
BEGIN
    INSERT INTO well_person (
        p_id,
        hs1_id,
        hs2_id,
        hs1_auth_date,
        hs2_auth_date
    ) VALUES (
        :new.p_id,
        NULL,
        NULL,
        NULL,
        NULL
    );
    INSERT INTO specific_recommendation values(:new.p_id, 1);

END;
/
