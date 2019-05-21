
--新增角色
insert into F_ROLEINFO (ROLECODE, ROLENAME, ISVALID, ROLEDESC)
values ('1-xwtzgg', '发起通知类', 'T', '发起新闻、通知、公告');

--为角色添加权限
insert into F_ROLEPOWER (ROLECODE, OPTCODE)
values ('1-xwtzgg', '10002183');

insert into F_ROLEPOWER (ROLECODE, OPTCODE)
values ('1-xwtzgg', '10002184');

insert into F_ROLEPOWER (ROLECODE, OPTCODE)
values ('1-xwtzgg', '10002185');

insert into F_ROLEPOWER (ROLECODE, OPTCODE)
values ('1-xwtzgg', '10002186');

insert into F_ROLEPOWER (ROLECODE, OPTCODE)
values ('1-xwtzgg', '10002191');

insert into F_ROLEPOWER (ROLECODE, OPTCODE)
values ('1-xwtzgg', '10002192');

insert into F_ROLEPOWER (ROLECODE, OPTCODE)
values ('1-xwtzgg', '10002193');

insert into F_ROLEPOWER (ROLECODE, OPTCODE)
values ('1-xwtzgg', '10002194');

insert into F_ROLEPOWER (ROLECODE, OPTCODE)
values ('1-xwtzgg', '10002601');

insert into F_ROLEPOWER (ROLECODE, OPTCODE)
values ('1-xwtzgg', '10002602');

insert into F_ROLEPOWER (ROLECODE, OPTCODE)
values ('1-xwtzgg', '10002603');

insert into F_ROLEPOWER (ROLECODE, OPTCODE)
values ('1-xwtzgg', '10002604');


--为张夏英添加角色
insert into F_USERROLE (USERCODE, ROLECODE, OBTAINDATE, SECEDEDATE, CHANGEDESC)
values ('S0001353', '1-xwtzgg', to_date('01-06-2016', 'dd-mm-yyyy'), null, null);