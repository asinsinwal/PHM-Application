create view VW_PERSON_RECOM
AS
select R_ID,FREQUENCY,DESCRIPTION,METRIC,LOWER_BOUND,UPPER_BOUND,STRING_VALUE FROM RECOMMENDATION where r_id in (select R_ID from STANDARD_RECOMMENDATION where d_id in (select d_id from RECORD_DISEASE where P_ID in (select P_ID from person)))
union 
select R_ID,FREQUENCY,DESCRIPTION,METRIC,LOWER_BOUND,UPPER_BOUND,STRING_VALUE from RECOMMENDATION where r_id in (select r_id from SPECIFIC_RECOMMENDATION where P_id in (select P_ID from person));
