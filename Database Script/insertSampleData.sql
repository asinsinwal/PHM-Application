--Disease
insert into Disease values(1,'Heart Disease');
insert into Disease values(2, 'HIV');
insert into Disease values(3, 'COPD');


--RECOMMENDATION
insert into RECOMMENDATION values(1, '7', 'weight', 'lbs', '120', '200', null);
insert into RECOMMENDATION values(2, '1', 'Systolic Blood Pressure', 'mmHg', '140', '159', null);
insert into RECOMMENDATION values(3, '1', 'Diastolic Blood Pressure', 'mmHg', '90', '99', null);
insert into RECOMMENDATION values(4, '7', 'mood', 'Happy-Neutral-Sad', null, null, 'Happy');
insert into RECOMMENDATION values(5, '1', 'Systolic Blood Pressure', 'mmHg', null, null, null);
insert into RECOMMENDATION values(6, '1', 'Diastolic Blood Pressure', 'mmHg', null, null, null);
insert into RECOMMENDATION values(7, '1', 'pain', '1-10', null, null, '5');
insert into RECOMMENDATION values(8, '1', 'SPO2 level', '%', '90', '99', null);
insert into RECOMMENDATION values(9, '1', 'temperature', 'F', '95', '100', null);
insert into RECOMMENDATION values(10, '7', 'weight', 'lbs', '120', '190', null);


--STANDARD_RECOMMENDATION
insert into STANDARD_RECOMMENDATION values(1, 1);
insert into STANDARD_RECOMMENDATION values(1, 2);
insert into STANDARD_RECOMMENDATION values(1, 3);
insert into STANDARD_RECOMMENDATION values(1, 4);
insert into STANDARD_RECOMMENDATION values(2, 1);
insert into STANDARD_RECOMMENDATION values(2, 5);
insert into STANDARD_RECOMMENDATION values(2, 6);
insert into STANDARD_RECOMMENDATION values(2, 7);
insert into STANDARD_RECOMMENDATION values(3, 8);
insert into STANDARD_RECOMMENDATION values(3, 9);



--Person
insert into Person values('P1', 'Sheldon Cooper', 'moon_pie', 'password', '2500 Sacramento, Apt 903, Santa Cruz, CA - 90021', to_date('19840526','YYYYMMDD'),'M',null);
insert into Person values('P2', 'Leonard Hofstader', 'leo', 'password', '2500 Sacramento, Apt 904, Santa Cruz, CA - 90021', to_date('19890419','YYYYMMDD'),'M',null);
insert into Person values('P3', 'Penny Hofstader', 'cheese_cake', 'password', '2500 Sacramento, Apt 904, Santa Cruz, CA - 90021', to_date('19901225','YYYYMMDD'),'F',null);
insert into Person values('P4', 'Amy Farrahfowler', 'fowl', 'password', '2500 Sacramento, Apt 905, Santa Cruz, CA - 90021', to_date('19920615','YYYYMMDD'),'F',null);


--Sick Person
insert into SICK_PERSON values('P1', 'P2', 'P4', to_date('20161021','YYYYMMDD'), to_date('20161021','YYYYMMDD'));
insert into SICK_PERSON values('P2', 'P3', null, to_date('20161009','YYYYMMDD'), null);


--Well Person
update  WELL_PERSON set HS1_id = 'P4', HS1_auth_date = to_date('20161021','YYYYMMDD') where p_id = 'P3'; 
--insert into WELL_PERSON values('P4', null, null, null, null); 



--Record Disease
insert into RECORD_DISEASE values('P1', 1, '22-OCT-16');
insert into RECORD_DISEASE values('P2', 2, '10-OCT-16');




--Specific Recommendation
insert into SPECIFIC_RECOMMENDATION values('P2', 10);
insert into SPECIFIC_RECOMMENDATION values('P2', 5);
insert into SPECIFIC_RECOMMENDATION values('P2', 6);
insert into SPECIFIC_RECOMMENDATION values('P2', 7);
--insert into SPECIFIC_RECOMMENDATION values('P3', 1); -- For well patient
--insert into SPECIFIC_RECOMMENDATION values('P4', 1); -- For well patient


--Observation
insert into OBSERVATION values(1, 'P2', 10, '180', '11-OCT-16 12.00.00 AM', '10-OCT-16 12.00.00 AM');
insert into OBSERVATION values(2, 'P2', 10, '195', '17-OCT-16 12.00.00 AM', '17-OCT-16 12.00.00 AM');
insert into OBSERVATION values(3, 'P1', 1, '160', '22-OCT-16','22-OCT-16'); -- For P1, assumed
insert into OBSERVATION values(4, 'P1', 2, '165', '22-OCT-16','22-OCT-16'); -- For P1, assumed. Outside the limit case
insert into OBSERVATION values(5, 'P1', 3, '96', '22-OCT-16','22-OCT-16'); -- For P1, assumed
insert into OBSERVATION values(6, 'P1', 4, 'Neutral', '22-OCT-16','22-OCT-16'); -- For P1, assumed
insert into OBSERVATION values(7, 'P2', 5, '148', '21-OCT-16','21-OCT-16'); -- For P2, assumed
insert into OBSERVATION values(8, 'P2', 6, '98', '22-OCT-16','20-OCT-16'); -- For P2, assumed
insert into OBSERVATION values(9, 'P2', 7, '6', '16-OCT-16', '16-OCT-16'); -- For P2, assumed
insert into OBSERVATION values(10, 'P3', 1, '190', '22-OCT-16', '10-OCT-16'); -- For P3.  -- For well patient.
insert into OBSERVATION values(11, 'P3', 1, '210', '21-OCT-16', '19-OCT-16'); -- For P4.  -- For well patient.



 
commit;





