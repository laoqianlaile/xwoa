-- Create table
create table OA_FILEMANAGER
(
  no               VARCHAR2(32) not null,
  info_type        VARCHAR2(50),
  title            VARCHAR2(200),
  remark           CLOB,
  creater          VARCHAR2(10),
  creatertime      DATE,
  release_date     DATE,
  valid_date       DATE,
  state            VARCHAR2(2),
  public_key       VARCHAR2(100)

)
tablespace FGWSUNZW_DFT_TBS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column OA_FILEMANAGER.info_type
  is '字典项 SW 收文  FW 发文';

comment on column OA_FILEMANAGER.state
  is '只能删除未启用的记录，启用或停用的记录不允许删除';

-- Create/Recreate primary, unique and foreign key constraints 
alter table OA_FILEMANAGER
  add constraint PK_OA_FILEMANAGER primary key (NO)
  using index 
  tablespace FGWSUNZW_DFT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

  
----市总文件序列
  -- Create sequence 
create sequence S_OAFILEMANAGER
minvalue 1
maxvalue 9999999999999999999999999
start with 1
increment by 1
cache 20
order;

---创建菜单
update   F_OPTINFO  t set  t.OPTNAME='公文归档管理' where  t.OPTID='GWLZXGGN';
insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('FILEMANAGE', 'YGJGGWLZ', '市总文件管理', null, '...', null, null, 'Y', null, null, 'S', 5, null, 'F');

insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('FILEMANAGESW', 'FILEMANAGE','阅办文', null, '/oa/oaFilemanager!list.do?s_infoType=SW', null, null, 'Y', null, null, 'S', 3, null, 'F');

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '查询', 'FILEMANAGESW', 'list', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '修改/添加', 'FILEMANAGESW', 'edit', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '删除', 'FILEMANAGESW', 'delete', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '查看明细', 'FILEMANAGESW', 'view', '系统自动添加', null);

insert into F_OPTINFO (OPTID, PREOPTID, OPTNAME, FORMCODE, OPTURL, MSGNO, MSGPRM, ISINTOOLBAR, IMGINDEX, TOPOPTID, OPTTYPE, ORDERIND, WFCODE, PAGETYPE)
values ('FILEMANAGEFW', 'FILEMANAGE', '拟发文', null, '/oa/oaFilemanager!list.do?s_infoType=FW', null, null, 'Y', null, null, 'S', 3, null, 'F');

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '查询', 'FILEMANAGEFW', 'list', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '修改/添加', 'FILEMANAGEFW', 'edit', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '删除', 'FILEMANAGEFW', 'delete', '系统自动添加', null);

insert into F_OPTDEF (OPTCODE, OPTNAME, OPTID, OPTMETHOD, OPTDESC, ISINWORKFLOW)
values ((select max((to_number(t.optcode))) + 1 from f_optdef t) , '查看明细', 'FILEMANAGEFW', 'view', '系统自动添加', null);





