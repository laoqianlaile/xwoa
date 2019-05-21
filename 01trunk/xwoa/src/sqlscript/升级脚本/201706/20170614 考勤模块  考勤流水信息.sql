-- Create table
create table OA_ATTENDANCE_DETAILED
(
  djid             VARCHAR2(32) not null,
  createdate       DATE,
  usercode         VARCHAR2(12),
  username         VARCHAR2(40),
  unitcount        VARCHAR2(12),
  unitname         VARCHAR2(40),
  workdate         VARCHAR2(10),
  saattendancetime VARCHAR2(20),
  xaattendancetime VARCHAR2(20),
  latein           VARCHAR2(2),
  earlyout         VARCHAR2(2),
  overtimehours    VARCHAR2(10),
  attenstate       VARCHAR2(1),
  amtime           VARCHAR2(10),
  pmtime           VARCHAR2(10),
  remarks          VARCHAR2(200) default '备注信息'
)
tablespace FGWSUNZW_DFT_TBS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table OA_ATTENDANCE_DETAILED
  add constraint PK_ATTENDANCE_DETAILED primary key (DJID)
  using index 
  tablespace FGWSUNZW_DFT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
