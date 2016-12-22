drop table SICK_PERSON;
drop table WELL_PERSON;
drop table RECORD_DISEASE;
drop table STANDARD_RECOMMENDATION;
drop table SPECIFIC_RECOMMENDATION;
drop table ALERT;
drop table PERSON;
drop table DISEASE;
drop table OBSERVATION;
drop table RECOMMENDATION;

COMMIT;

purge recyclebin;
