create or replace TRIGGER obsv_value_bound
  BEFORE
    INSERT-- OR
    --UPDATE OF ob_value
  ON observation
FOR EACH ROW
declare is_null number;
lo_bound varchar2(20);
up_bound varchar2(20);
alert_count number;
descrip_reco VARCHAR2(1024);
reco_id number;
BEGIN
select R1.lower_bound into is_null from RECOMMENDATION R1 where R1.r_id = :new.r_id;
if is_null is not null
  then
    select to_number(R1.lower_bound), to_number(R1.upper_bound), R1.r_id,
    concat('Alert generated because your observation value for ', concat(R1.description, concat(' is beyond the limits. Recommended limits are ', concat( R1.lower_bound, concat(', ', concat(R1.upper_bound , concat('. But your value was ', :new.ob_value))))))) 
    into lo_bound, up_bound, reco_id, descrip_reco
    from RECOMMENDATION R1 where R1.r_id = :new.r_id;
    if (to_number(:new.ob_value) > to_number(up_bound) or to_number(:new.ob_value) < to_number(lo_bound))
      then
        select count(*) into alert_count from alert;
        insert into alert values(alert_count+1,:new.p_id, reco_id, descrip_reco, 'F', 'F');
    end if;
end if;
END;
/



COMMIT;

