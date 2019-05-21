insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('WDSY', 'YGJG', '我的首页', null, '...', null, null, 'Y', null, null, 'S', 60, null, 'F');

insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('WDSYPZ', 'WDSY', '我的首页', null, '...', null, null, 'N', null, null, 'S', 1, null, 'F');

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '拟发文待办选择项', 'WDSYPZ', 'nfwdb', '首页拟发文待办选择项', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1  from f_optdef t) , '阅办文待办选择项', 'WDSYPZ', 'ybwdb', '首页阅办文待办选择项', null);

insert into F_ROLEPOWER (ROLECODE, OPTCODE)
values ('1-zx', (select t.optcode from f_optdef t where t.optname = '拟发文待办选择项'));

insert into F_ROLEPOWER (ROLECODE, OPTCODE)
values ('1-zx', (select t.optcode from f_optdef t where t.optname = '阅办文待办选择项'));
