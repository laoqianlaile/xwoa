----1主席添加主席公文流转模块中应该对应改成
--审核管理（审核待办，审核查看），
--审阅管理（审阅待办，审阅查看）
insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('ZXGWLZFWGL', 'YGJGGWLZ', '审批管理', null, '...', null, null, 'Y', null, null, 'N', 1, null, 'F');

insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('ZXGWLZSWGL', 'YGJGGWLZ', '审阅管理', null, '...', null, null, 'Y', null, null, 'N', 2, null, 'F');

insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('ZXFWGLFWDB', 'ZXGWLZFWGL', '审批', null, '/dispatchdoc/vuserTaskListOA!list.do?s_flowCode=000392&&s_itemtype=FW', null, null, 'Y', null, null, 'N', 2, null, 'F');

insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('ZXFWGLFWCK', 'ZXGWLZFWGL', '审批查看', null, '/dispatchdoc/dispatchDoc!list.do?show_type=T', null, null, 'Y', null, null, 'N', 3, null, 'F');

insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('ZXSWGLSWDB', 'ZXGWLZSWGL', '审阅', null, '/dispatchdoc/vuserTaskListOA!list.do?s_flowCode=000394&&s_itemtype=SW', null, null, 'Y', null, null, 'N', 2, null, 'F');

insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('ZXSWGLSWCK', 'ZXGWLZSWGL', '审阅查看', null, '/dispatchdoc/incomeDoc!list.do?show_type=T', null, null, 'Y', null, null, 'N', 3, null, 'F');




insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t), '审批查看', 'ZXFWGLFWCK', 'list', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t), '审批', 'ZXFWGLFWDB', 'list', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t), '主席审批管理', 'ZXGWLZFWGL', 'list', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t), '主席审阅管理', 'ZXGWLZSWGL', 'list', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t), '审阅查看', 'ZXSWGLSWCK', 'list', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t), '审阅', 'ZXSWGLSWDB', 'list', '系统自动添加', null);





-----2市总文件管理中，小模块为，拟发文查看、阅办文查看
update  F_OPTINFO  t set t.optname = '阅办文查看'  where  t.optid='FILEMANAGESW';
update  F_OPTINFO  t set t.optname = '拟发文查看'  where  t.optid='FILEMANAGEFW';


-----3.测试标题
update   OPT_BASE_INFO t  set  t.transaffairname='测试 '||t.transaffairname;
update   M_DISPATCH_DOC t  set  t.dispatch_doc_title='测试 '||t.dispatch_doc_title;
update   M_INCOME_DOC t  set  t.income_doc_title='测试 '||t.income_doc_title;

