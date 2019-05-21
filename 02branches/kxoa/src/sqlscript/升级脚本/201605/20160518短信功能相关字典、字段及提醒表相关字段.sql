------提醒表添加字段：是否发送短信
alter table oa_remind_information add(IS_SEND_MSG CHAR(1));
------短信表添加字段：短信网关成功发送短信时间
alter table oa_smssend add(SUCCEEDTIME DATE);

----创建短信记录表
create table OA_SMSSEND_LOG
(
  NO             NUMBER(12) not null,
  SMSID          NUMBER(12) not null,
  SENDTIME       DATE,
  CONTENT        VARCHAR2(1000),
  STATE          VARCHAR2(2),
  DATASOURCE     CHAR(1),
  RESTOREMESSAGE VARCHAR2(140),
  SEQUENCEID     VARCHAR2(40),
  MSGID          VARCHAR2(40),
  SUCCEEDTIME    DATE
)
tablespace FGWSUNZW_DFT_TBS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

alter table OA_SMSSEND_LOG
  add constraint PK_SMSSEND_LOG primary key (NO)
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
  
-----短信记录表主键生成序列
create sequence S_SMSSEND_LOG
minvalue 0
maxvalue 999999999999
start with 20
increment by 1
cache 20;

------短信功能相关数据字典


insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('smsSource', 'F', null, null, 'T', '发文', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('smsSource', 'R', null, null, 'T', '手动添加', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('smsSource', 'S', null, null, 'T', '收文', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('smsSource', 'T', null, null, 'T', '提醒', null, null, null, null);


insert into F_DATACATALOG (CATALOGCODE, CATALOGNAME, CATALOGSTYLE, CATALOGTYPE, CATALOGDESC, FIELDDESC, ISUPLOAD, DICTIONARYTYPE, CREATEDATE, LASTMODIFYDATE)
values ('sendMSg', '短信功能', 'S', 'L', '短信功能', '短信功能', '0', 'XTGL', null, null);
insert into F_DATACATALOG (CATALOGCODE, CATALOGNAME, CATALOGSTYLE, CATALOGTYPE, CATALOGDESC, FIELDDESC, ISUPLOAD, DICTIONARYTYPE, CREATEDATE, LASTMODIFYDATE)
values ('sendMSgMod', '发送短信模板', 'S', 'L', '定义短信模板', null, '0', 'XTGL', null, null);
insert into F_DATACATALOG (CATALOGCODE, CATALOGNAME, CATALOGSTYLE, CATALOGTYPE, CATALOGDESC, FIELDDESC, ISUPLOAD, DICTIONARYTYPE, CREATEDATE, LASTMODIFYDATE)
values ('sendMsgInfo', '短信网关信息', 'S', 'L', '短信网关信息', null, '0', 'XTGL', null, null);
insert into F_DATACATALOG (CATALOGCODE, CATALOGNAME, CATALOGSTYLE, CATALOGTYPE, CATALOGDESC, FIELDDESC, ISUPLOAD, DICTIONARYTYPE, CREATEDATE, LASTMODIFYDATE)
values ('sendMsgResState', '短信回执状态', 'U', 'L', '对应RESTOREMESSAGE', '短信回执状态', '0', 'XTGL', null, null);
insert into F_DATACATALOG (CATALOGCODE, CATALOGNAME, CATALOGSTYLE, CATALOGTYPE, CATALOGDESC, FIELDDESC, ISUPLOAD, DICTIONARYTYPE, CREATEDATE, LASTMODIFYDATE)
values ('sendMsgState', '短信回应状态', 'S', 'L', '下行短信返回状态类对应字段 state', '短信发送 回应', '0', 'XTGL', null, null);

-----
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMSg', 'isopen', null, null, 'T', 'T', null, '短信功能是否生效', null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMSg', 'mobileMatcher', null, null, 'T', '^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$', null, '号码匹配', null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMSg', 'numPermitted', null, null, 'T', '200', null, '每个用户每个月限制的发送短信数', null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMSg', 'sendMSgMod', null, null, 'T', 'sendMSgMod', null, '短信模板', null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMSg', 'sendMsgInfo', null, null, 'T', 'sendMsgInfo', null, '短信网关对应字典项', null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMSg', 'sendMsgResState', null, null, 'T', 'sendMsgResState', null, '回执对应字典项', null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMSg', 'sendMsgState', null, null, 'T', 'sendMsgState', null, '回应对应字典项', null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMSg', 'smsSource', null, null, 'T', 'smsSource', null, '短信来源字典', null, null);

insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMSgMod', 'carOverDue', null, null, 'T', '{event}该车辆没有按时回车！', null, '示例', null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMSgMod', 'com', null, null, 'T', '{username}您好，您有关于{event}的新办件，请尽快办理！【OA办公系统】', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMSgMod', 'remind', null, null, 'T', '{username}，您好，您有一条关于{title}提醒', null, null, null, null);

insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgInfo', 'ipAddress', null, null, 'T', '202.102.41.101', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgInfo', 'password', null, null, 'T', 'jh84558870', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgInfo', 'port', null, null, 'T', '9005', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgInfo', 'username', null, null, 'T', '025C00004375', null, null, null, null);


insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgResState', 'E', null, null, 'T', '没有号码或者号码错误', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgResState', '0', null, null, 'T', '成功', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgResState', '1', null, null, 'T', '用户不能通信', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgResState', '2', null, null, 'T', '用户忙', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgResState', '3', null, null, 'T', '终端无此部件号', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgResState', '4', null, null, 'T', '非法用户', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgResState', '5', null, null, 'T', '用户在黑名单内', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgResState', '6', null, null, 'T', '系统错误', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgResState', '7', null, null, 'T', '用户内存满', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgResState', '8', null, null, 'T', '非信息终端', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgResState', '9', null, null, 'T', '数据错误', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgResState', '10', null, null, 'T', '数据丢失', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgResState', '999', null, null, 'T', '未知错误', null, null, null, null);


insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgState', 'E', null, null, 'T', '没有号码或者号码错误', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgState', '0', null, null, 'T', '成功', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgState', '1', null, null, 'T', '系统错误', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgState', '2', null, null, 'T', '帐号错误', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgState', '3', null, null, 'T', '密码错误', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgState', '4', null, null, 'T', '连接数超过限制', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgState', '5', null, null, 'T', '秒发送条目数超过限制', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgState', '6', null, null, 'T', '目的号码受限制', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgState', '7', null, null, 'T', '网络错误', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgState', '8', null, null, 'T', '月发送条目数超过限制', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgState', '9', null, null, 'T', '客户端关闭连接', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgState', '10', null, null, 'T', '短信网关关闭连接', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgState', '11', null, null, 'T', '超时退出', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgState', '12', null, null, 'T', '连接数据库错误', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgState', '13', null, null, 'T', '连接短信网关错误', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgState', '14', null, null, 'T', '非法发送内容', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgState', '15', null, null, 'T', '非法发送时间', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgState', '16', null, null, 'T', 'socket连接已经关闭', null, null, null, null);
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgState', '23', null, null, 'T', '发送号码前缀错误', null, null, null, null);




