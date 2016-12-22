CREATE OR REPLACE PROCEDURE check_freq (
    pt_id      IN VARCHAR2
) AS
    alert_count   NUMBER;
alert_exists number;

BEGIN
    SELECT
        COUNT(*) 
    INTO
        alert_count
    FROM
        alert;

    DECLARE
        CURSOR c1 IS
            SELECT
                concat(
                    'Alert generated due to low frequency for the measure of ',
                    concat(
                        r1.description,
                        concat(
                            '. Recommended is once in ',
                            concat(r1.frequency, ' day(s).' ) 
                        )
                    )
                ) AS de, R1.r_id
            FROM
                record_disease rd1,
                standard_recommendation sr1,
                recommendation r1,
                observation o1
            WHERE
                pt_id = rd1.p_id
            AND
                rd1.d_id = sr1.d_id
            AND
                sr1.r_id = r1.r_id
            AND
                o1.p_id = pt_id
            AND
                o1.r_id = r1.r_id
            AND
            
            o1.ob_time + to_number(r1.frequency) <= sysdate
                --( to_number(TO_CHAR(o1.ob_time,'DD') ) + to_number(r1.frequency) ) <= date_now
            AND
                ( to_number(TO_CHAR(o1.ob_time,'DD') ) + to_number(r1.frequency) ) > ALL (
                    SELECT
                        to_number(TO_CHAR(o2.ob_time,'DD') )
                    FROM
                        observation o2
                    WHERE
                        o2.r_id = r1.r_id
                    AND
                        o2.p_id = pt_id
                );

    BEGIN
        FOR item IN c1 LOOP
          select count(*) into alert_exists from alert a where a.r_id = item.r_id and a.description like '%low frequency%';
          --dbms_output.put_line( alert_exists);
          --dbms_output.put_line(item.r_id);
          if alert_exists <> 0
            then
             --dbms_output.put_line('update');
              update alert set is_mandatory = 'T', is_viewed = 'F' where a_id = (select a.a_id from alert a where a.r_id = item.r_id and a.description like '%low frequency%');
          else
            --dbms_output.put_line('Put new');
            INSERT INTO alert VALUES (
                alert_count + 1,
                pt_id,
                item.r_id,
                item.de,
                'T',
                'F'
            );
             alert_count := alert_count + 1;
           end if;

        END LOOP;

    END;

END;

