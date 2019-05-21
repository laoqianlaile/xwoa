insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('WJG', 'YGJGGWLZ', '文件柜', null, '...', null, null, 'Y', null, null, 'N', 5, null, 'F');

insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('WJSBTOSYSOUT', 'WJG', '文件上报', null, '/oa/optFileTransferSend!listReportToSysout.do', null, null, 'Y', null, null, 'S', 1, null, null);

insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('WJXF', 'WJG', '文件下发', null, '/oa/optFileTransferSend!listIssued.do', null, null, 'Y', null, null, 'S', 2, null, 'F');

insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('WJSJX', 'WJG', '文件收件箱', null, '/oa/optFileTransferReceive!list.do', null, null, 'Y', null, null, 'S', 3, null, 'F');




insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE, ROWID)
values ('YGJGWJG', 'YGJG', '中间部门文件柜', null, '...', null, null, 'Y', null, null, 'N', 5, null, 'F', null);

insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE, ROWID)
values ('WJGWJSB', 'YGJGWJG', '文件上报', null, '/oa/optFileTransferSend!listReportToSysout.do', null, null, 'Y', null, null, 'S', 1, null, 'I', null);

insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE, ROWID)
values ('WJGWJXF', 'YGJGWJG', '文件下发', null, '/oa/optFileTransferSend!listIssued.do', null, null, 'Y', null, null, 'S', 2, null, 'F', null);

insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE, ROWID)
values ('WJGWJSJX', 'YGJGWJG', '文件收件箱', null, '/oa/optFileTransferReceive!list.do', null, null, 'Y', null, null, 'S', 3, null, 'F', null);


insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '查询', 'WJSBTOSYSOUT', 'list', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '修改/添加', 'WJSBTOSYSOUT', 'edit', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '删除', 'WJSBTOSYSOUT', 'delete', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '查看明细', 'WJSBTOSYSOUT', 'view', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '查询', 'WJXF', 'list', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '修改/添加', 'WJXF', 'edit', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '删除', 'WJXF', 'delete', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '查看明细', 'WJXF', 'view', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '查询', 'WJSJX', 'list', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '修改/添加', 'WJSJX', 'edit', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '删除', 'WJSJX', 'delete', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '查看明细', 'WJSJX', 'view', '系统自动添加', null);


insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '查询', 'WJGWJSB', 'list', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '修改/添加', 'WJGWJSB', 'edit', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '删除', 'WJGWJSB', 'delete', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '查看明细', 'WJGWJSB', 'view', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '查询', 'WJGWJXF', 'list', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '修改/添加', 'WJGWJXF', 'edit', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '删除', 'WJGWJXF', 'delete', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '查看明细', 'WJGWJXF', 'view', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '查询', 'WJGWJSJX', 'list', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '修改/添加', 'WJGWJSJX', 'edit', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '删除', 'WJGWJSJX', 'delete', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '查看明细', 'WJGWJSJX', 'view', '系统自动添加', null);