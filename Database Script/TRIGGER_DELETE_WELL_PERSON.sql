CREATE OR REPLACE TRIGGER delete_well_person AFTER
    INSERT ON sick_person
    FOR EACH ROW
BEGIN
    DELETE FROM well_person WHERE
        p_id =:new.p_id;
    delete from specific_recommendation where
      p_id = :new.p_id and r_id = 1;

END;
/
