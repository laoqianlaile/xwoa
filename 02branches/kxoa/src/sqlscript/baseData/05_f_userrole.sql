truncate table F_USERROLE;
insert into F_USERROLE (USERCODE, ROLECODE, OBTAINDATE, SECEDEDATE, CHANGEDESC)
values ('99999999', 'G-SYSADMIN', to_date('01-03-2010', 'dd-mm-yyyy'), to_date('23-11-3012', 'dd-mm-yyyy'), null);
insert into F_USERROLE (USERCODE, ROLECODE, OBTAINDATE, SECEDEDATE, CHANGEDESC)
values ('noname', 'G-anonymous', to_date('22-12-2010', 'dd-mm-yyyy'), to_date('31-01-3027', 'dd-mm-yyyy'), null);
