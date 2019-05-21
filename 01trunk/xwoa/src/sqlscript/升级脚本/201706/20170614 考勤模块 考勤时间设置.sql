-- Create table
create table OA_ATTENDANCE_SETTING
(
  djid             VARCHAR2(8) not null,
  attcode          VARCHAR2(8),
  createdate       DATE,
  lastcodedate     DATE,
  schedulingtype   VARCHAR2(16),
  statetime        CHAR(1),
  onetype          VARCHAR2(16),
  twotype          VARCHAR2(16),
  threetype        VARCHAR2(16),
  fourtype         VARCHAR2(16),
  fivetype         VARCHAR2(16),
  sixtype          VARCHAR2(16),
  onetime          DATE,
  twotime          DATE,
  threetime        DATE,
  fourtime         DATE,
  fivetime         DATE,
  sextime          DATE,
  notworkweek      VARCHAR2(32),
  notworkdate      VARCHAR2(100),
  personnelcode    VARCHAR2(500),
  intermissiontime DATE,
  state            CHAR(1),
  saattendancetime VARCHAR2(10),
  xaattendancetime VARCHAR2(10)
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
alter table OA_ATTENDANCE_SETTING
  add constraint PK_ATTENDANCE_SETTING primary key (DJID)
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
