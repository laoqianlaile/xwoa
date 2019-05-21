----添加考勤大模块（考勤信息跟考勤时间设置是同级，只需添加一次即可）
insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('YGJGGRBG', 'ATTENDANCE', '考勤管理', null, '...', null, null, 'Y', null, null, 'N', 2, null, 'F');

----添加考勤信息菜单
insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('ATTENDANCE_DETAILED', 'ATTENDANCE', '考勤信息', null, '/attendance/attendanceDetailed!list.do?listType=0', null, null, 'Y', null, null, 'N', 5, null, 'F');

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max(optcode) from F_Optdef)+1, '查询', 'ATTENDANCE_DETAILED', 'list', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max(optcode) from F_Optdef)+1, '修改/添加', 'ATTENDANCE_DETAILED', 'edit', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max(optcode) from F_Optdef)+1, '删除', 'ATTENDANCE_DETAILED', 'delete', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max(optcode) from F_Optdef)+1, '查看明细', 'ATTENDANCE_DETAILED', 'view', '系统自动添加', null);