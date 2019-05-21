
insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('UNITSHAREFILE', 'GRBGFZBG', '资料库', null, '/app/publicinfo!unitFilePanel.do', null, null, 'Y', null, null, 'S', 3, null, 'F');

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '查询', 'UNITSHAREFILE', 'list', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '修改/添加', 'UNITSHAREFILE', 'edit', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '删除', 'UNITSHAREFILE', 'delete', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '查看明细', 'UNITSHAREFILE', 'view', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '编辑全部', 'UNITSHAREFILE', 'editall', null, 'F');

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '部门内修改权限', 'UNITSHAREFILE', 'editunit', null, null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '上传', 'UNITSHAREFILE', 'add', null, null);


