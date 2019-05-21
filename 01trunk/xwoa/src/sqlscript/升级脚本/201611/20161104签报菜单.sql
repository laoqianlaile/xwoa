----添加签报大模块
insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('YGJGNBSX', 'YGJG', '签报管理', null, '...', null, null, 'Y', null, null, 'N', 2, null, 'F');

----添加领导批示菜单
insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('QBDBLD', 'RCBGQBGL', '签报批示', null, '/dispatchdoc/vuserTaskListOA!list.do?s_itemtype=QB', null, null, 'Y', null, null, 'N', 5, null, 'F');

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max(optcode) from F_Optdef)+1, '查询', 'QBDBLD', 'list', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max(optcode) from F_Optdef)+1, '修改/添加', 'QBDBLD', 'edit', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max(optcode) from F_Optdef)+1, '删除', 'QBDBLD', 'delete', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max(optcode) from F_Optdef)+1, '查看明细', 'QBDBLD', 'view', '系统自动添加', null);