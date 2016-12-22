CREATE OR REPLACE TRIGGER observation_viewed AFTER
    INSERT ON observation
    FOR EACH ROW
declare can_delete char(1);
count_alerts number;
BEGIN
  select count(*) into count_alerts from ALERT A1 where A1.p_id = :new.p_id and A1.r_id = :new.r_id and A1.is_mandatory = 'T';
  if count_alerts<>0
  then
      update ALERT A1 set A1.is_mandatory = 'F', A1.is_viewed = 'T' where A1.p_id = :new.p_id and A1.r_id = :new.r_id; 
    end if;
END;
/
