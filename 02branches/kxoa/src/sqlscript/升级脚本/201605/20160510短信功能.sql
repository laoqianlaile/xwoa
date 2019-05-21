-- Add/modify columns 
alter table OA_SMSSEND add sequenceid VARCHAR2(40);
alter table OA_SMSSEND add msgid VARCHAR2(40);
-- Add comments to the columns 
comment on column OA_SMSSEND.sequenceid
is '短信的sequenceId 有回执后清空';
comment on column OA_SMSSEND.msgid
  is '下行短信msgId,用来标识一条短信';
  
  -- Add/modify columns 
alter table OA_SMSSEND modify state VARCHAR2(2);



--字典项

insert into F_DATACATALOG (CATALOGCODE, CATALOGNAME, CATALOGSTYLE, CATALOGTYPE, CATALOGDESC, FIELDDESC, ISUPLOAD, DICTIONARYTYPE, CREATEDATE, LASTMODIFYDATE)
values ('sendMSg', '短信功能', 'S', 'L', '短信功能', '短信功能', '0', 'XTGL', null, null);

insert into F_DATACATALOG (CATALOGCODE, CATALOGNAME, CATALOGSTYLE, CATALOGTYPE, CATALOGDESC, FIELDDESC, ISUPLOAD, DICTIONARYTYPE, CREATEDATE, LASTMODIFYDATE)
values ('sendMsgState', '短信回应状态', 'S', 'L', '下行短信返回状态类对应字段 state', '短信发送 回应', '0', 'XTGL', null, null);

insert into F_DATACATALOG (CATALOGCODE, CATALOGNAME, CATALOGSTYLE, CATALOGTYPE, CATALOGDESC, FIELDDESC, ISUPLOAD, DICTIONARYTYPE, CREATEDATE, LASTMODIFYDATE)
values ('sendMsgResState', '短信回执状态', 'U', 'L', '对应RESTOREMESSAGE', '短信回执状态', '0', 'XTGL', null, null);

insert into F_DATACATALOG (CATALOGCODE, CATALOGNAME, CATALOGSTYLE, CATALOGTYPE, CATALOGDESC, FIELDDESC, ISUPLOAD, DICTIONARYTYPE, CREATEDATE, LASTMODIFYDATE)
values ('sendMsgInfo', '短信网关信息', 'S', 'L', '短信网关信息', null, '0', 'XTGL', null, null);



---字典项明细
insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMSg', 'sendMsgInfo', null, null, 'T', 'sendMsgInfo', null, '短信网关对应字典项', null, null);

insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgInfo', 'ipAddress', null, null, 'T', '202.102.41.1012', null, null, null, null);

insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgInfo', 'port', null, null, 'T', '9005', null, null, null, null);

insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgInfo', 'username', null, null, 'T', '025C00004375', null, null, null, null);

insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMSg', 'isopen', null, null, 'T', 'F', null, '短信功能是否生效', null, null);

insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMSg', 'sendMsgResState', null, null, 'T', 'sendMsgResState', null, '回执对应字典项', null, null);

insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMSg', 'sendMsgState', null, null, 'T', 'sendMsgState', null, '回应对应字典项', null, null);

insert into F_DATADICTIONARY (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('sendMsgInfo', 'password', null, null, 'T', 'jh84558870', null, null, null, null);

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