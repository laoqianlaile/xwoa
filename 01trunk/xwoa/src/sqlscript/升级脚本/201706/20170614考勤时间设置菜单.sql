----添加时间设置菜单
insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('ATTENDANCE_SETTING', 'ATTENDANCE', '时间设置', null, '/attendance/attendanceSetting!list.do', null, null, 'Y', null, null, 'N', 5, null, 'F');

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max(optcode) from F_Optdef)+1, '查询', 'ATTENDANCE_SETTING', 'list', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max(optcode) from F_Optdef)+1, '修改/添加', 'ATTENDANCE_SETTING', 'edit', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max(optcode) from F_Optdef)+1, '删除', 'ATTENDANCE_SETTING', 'delete', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max(optcode) from F_Optdef)+1, '查看明细', 'ATTENDANCE_SETTING', 'view', '系统自动添加', null);

