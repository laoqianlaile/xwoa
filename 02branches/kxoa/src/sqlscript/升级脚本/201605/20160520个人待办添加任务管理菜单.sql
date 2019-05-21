insert into F_OPTINFO  (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('RWGL', 'GRBGGRDB', '任务管理', null, '/sampleflow/flowUserTask!listUserCompTask.do', null, null, 'Y', null, null, 'N', 3, null, 'F');

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max(optcode) from F_Optdef)+1, '查询', 'RWGL', 'list', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max(optcode) from F_Optdef)+1, '修改/添加', 'RWGL', 'edit', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max(optcode) from F_Optdef)+1, '删除', 'RWGL', 'delete', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max(optcode) from F_Optdef)+1, '查看明细', 'RWGL', 'view', '系统自动添加', null);