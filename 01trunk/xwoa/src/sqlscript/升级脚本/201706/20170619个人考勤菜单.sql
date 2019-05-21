----添加个人考勤菜单
insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('ATTENDANCE_USER', 'ATTENDANCE', '个人考勤', null, '/attendance/attendanceDetailed!list.do?listType=1', null, null, 'Y', null, null, 'N', 5, null, 'F');

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max(optcode) from F_Optdef)+1, '查询', 'ATTENDANCE_USER', 'list', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max(optcode) from F_Optdef)+1, '修改/添加', 'ATTENDANCE_USER', 'edit', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max(optcode) from F_Optdef)+1, '删除', 'ATTENDANCE_USER', 'delete', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max(optcode) from F_Optdef)+1, '查看明细', 'ATTENDANCE_USER', 'view', '系统自动添加', null);