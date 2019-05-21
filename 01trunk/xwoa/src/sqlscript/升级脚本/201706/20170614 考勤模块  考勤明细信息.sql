-- Create table
create table ATTENDANCE_INFO
(
  djid             VARCHAR2(8) not null,
  createdate       DATE,
  usercode         VARCHAR2(8),
  username         VARCHAR2(20),
  unitcount        NUMBER(10),
  unitname         NUMBER(10),
  workdate         DATE,
  saattendancetime DATE,
  xaattendancetime DATE,
  overtimehours    DATE,
  latein           VARCHAR2(20),
  earlyout         VARCHAR2(20),
  reason           VARCHAR2(200),
  begintime        DATE,
  endtime          DATE,
  travelday        VARCHAR2(8),
  registrant       VARCHAR2(8),
  remarks          VARCHAR2(200),
  sponsor          VARCHAR2(32),
  comments         VARCHAR2(200),
  opinions         VARCHAR2(200),
  approvalstate    VARCHAR2(20),
  recordtime       DATE,
  holidaynum       VARCHAR2(8),
  overtimehour     VARCHAR2(8),
  type             VARCHAR2(8),
  flowcode         VARCHAR2(8),
  flowinstid       NUMBER(12),
  bizstate         VARCHAR2(2),
  biztype          NUMBER(12),
  solvestatus      VARCHAR2(2),
  solvetime        DATE,
  solvenote        VARCHAR2(4000),
  optid            VARCHAR2(20)
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
alter table ATTENDANCE_INFO
  add constraint PK_ATTENDANCE_INFO primary key (DJID)
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
