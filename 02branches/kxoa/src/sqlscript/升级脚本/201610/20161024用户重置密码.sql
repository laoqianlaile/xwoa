-- Create table
create table F_USERFORGETPASSWORD
(
  djid            VARCHAR2(50),
  telephone        VARCHAR2(64),
  ip         VARCHAR2(16),
  state           CHAR(1),
  
  usercode        VARCHAR2(8) ,
  forgettime      DATE,
 
  validatenum     VARCHAR2(16),
  validatetime      DATE
)
tablespace FGWSUNZW_DFT_TBS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 384K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column F_USERFORGETPASSWORD.telephone
  is '用户系统内预留手机号';
comment on column F_USERFORGETPASSWORD.state
  is '重置密码进度  0 发起重置申请 1 短信验证通过   2重置完成 3因未知因素重置失败 4短信验证码失效（同一页面支持再次发起短信）';
comment on column F_USERFORGETPASSWORD.forgettime
  is '重置密码   发起重置申请时间';
comment on column F_USERFORGETPASSWORD.validatenum
  is '短信验证码';
 comment on column F_USERFORGETPASSWORD.validatetime
  is '短信验证通过时间';

-- Create/Recreate primary, unique and foreign key constraints 
alter table F_USERFORGETPASSWORD
  add constraint PK_F_USERFORGETPASSWORD primary key (djid)
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
