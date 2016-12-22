--    List the number of health supporters that were authorized in the month of September 2016 by patients suffering from heart disease.
select count(*) from sick_person where (P_ID in (select P_ID from RECORD_DISEASE where D_ID in (select D_ID from Disease where UPPER(DNAME) = 'HEART DISEASE'))) 
and (to_char(hs1_auth_date,'MMYYYY') = '092016' or to_char(hs2_auth_date,'MMYYYY' )= '092016');


--    Give the number of patients who were not complying with the recommended frequency of recording observations.

select sum(ct) as Number_Of_Patients
from
(
SELECT
  count(*) as ct
FROM
    specific_recommendation spr1,
    recommendation r1,
    observation o1,
    well_person wp1
WHERE
    wp1.p_id = spr1.p_id AND spr1.r_id = r1.r_id AND o1.p_id = wp1.p_id AND o1.r_id = r1.r_id AND
    o1.ob_time + to_number(r1.frequency) <= sysdate AND ( to_number(TO_CHAR(o1.ob_time,'DD') ) + to_number(r1.frequency) ) > ALL (
        SELECT
            to_number(TO_CHAR(o2.ob_time,'DD') )
        FROM
            observation o2
        WHERE
            o2.r_id = r1.r_id AND o2.p_id = wp1.p_id
    )
UNION

SELECT
  count(*) as ct
FROM
    specific_recommendation spr1,
    recommendation r1,
    observation o1,
    sick_person sp1
WHERE
    sp1.p_id = spr1.p_id AND spr1.r_id = r1.r_id AND o1.p_id = sp1.p_id AND o1.r_id = r1.r_id AND
    o1.ob_time + to_number(r1.frequency) <= sysdate AND ( to_number(TO_CHAR(o1.ob_time,'DD') ) + to_number(r1.frequency) ) > ALL (
        SELECT
            to_number(TO_CHAR(o2.ob_time,'DD') )
        FROM
            observation o2
        WHERE
            o2.r_id = r1.r_id AND o2.p_id = sp1.p_id
    )
UNION

SELECT
  count(*) as ct
FROM
    record_disease rd1,
    standard_recommendation sr1,
    recommendation r1,
    observation o1,
    sick_person sp1
WHERE
    sp1.p_id = rd1.p_id AND rd1.d_id = sr1.d_id AND sr1.r_id = r1.r_id AND o1.p_id = sp1.p_id AND o1.r_id = r1.r_id AND
    r1.description <> all( select r2.description from recommendation r2, specific_recommendation spr1 where spr1.p_id = sp1.p_id and spr1.r_id = r2.r_id) and
    o1.ob_time + to_number(r1.frequency) <= sysdate AND ( to_number(TO_CHAR(o1.ob_time,'DD') ) + to_number(r1.frequency) ) > ALL (
        SELECT
            to_number(TO_CHAR(o2.ob_time,'DD') )
        FROM
            observation o2
        WHERE
            o2.r_id = r1.r_id AND o2.p_id = sp1.p_id
    )
);



--    List the health supporters who themselves are patients.
select * from person where P_id in (select p_id from sick_person where p_id in ((select hs1_id as p_id from sick_person) union (select hs1_id as p_id from WELL_PERSON)) or p_id in ((select hs2_id as p_id from sick_person) union (select hs2_id as p_id from WELL_PERSON)));

--    List the patients who are not ‘sick’.
select * from PERSON where P_ID not in (select P_ID from SICK_PERSON);

--    How many patients have different observation time and recording time (of the observation).
select count(P_ID) from OBSERVATION where (RECORD_TIME <> OB_TIME);



