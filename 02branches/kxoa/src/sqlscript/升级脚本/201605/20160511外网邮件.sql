drop table F_MAIL_PROFILE cascade constraints;

/*==============================================================*/
/* Table: F_MAIL_PROFILE                                        */
/*==============================================================*/
create table F_MAIL_PROFILE 
(
   ID                   NUMBER               not null,
   USERCODE             VARCHAR2(8),
   EMAIL                VARCHAR2(100),
   PASSWORD             VARCHAR2(300),
   DISPLAYNAME          VARCHAR2(100),
   SENDERNAME           VARCHAR2(100),
   RECEIVE_HOST_TYPE    VARCHAR2(10),
   RECEIVE_HOST_ADDR    VARCHAR2(50),
   RECEIVE_PROTOCOL     VARCHAR2(10),
   RECEIVE_PORT         VARCHAR2(10),
   SEND_HOST_ADDR       VARCHAR2(50),
   SEND_PROTOCOL        VARCHAR2(10),
   SEND_PORT            VARCHAR2(10),
   ISACTIVE             VARCHAR2(1),
   constraint PK_F_MAIL_PROFILE primary key (ID)
);

comment on table F_MAIL_PROFILE is
'邮箱配置';

comment on column F_MAIL_PROFILE.DISPLAYNAME is
'就是当前邮箱的名称，能清楚自己正在操作哪个邮箱';

comment on column F_MAIL_PROFILE.SENDERNAME is
'对方接收邮件时，发件人显示的名称';

comment on column F_MAIL_PROFILE.RECEIVE_HOST_TYPE is
'POP3,IMAP,EXCHANGE';

comment on column F_MAIL_PROFILE.RECEIVE_PROTOCOL is
'normal,ssl';

comment on column F_MAIL_PROFILE.SEND_PROTOCOL is
'normal,ssl';

comment on column F_MAIL_PROFILE.ISACTIVE is
'T-是 F-是';

create sequence SEQ_F_MAIL_PROFILE;

drop table F_MAIL_INFO cascade constraints;

/*==============================================================*/
/* Table: F_MAIL_INFO                                           */
/*==============================================================*/
create table F_MAIL_INFO 
(
   ID                   NUMBER               not null,
   EMAIL                VARCHAR2(100),
   USERCODE             VARCHAR2(8),
   SENDER               VARCHAR2(200),
   RECEIVER             CLOB,
   COPYER               CLOB,
   SECRETER             CLOB,
   SENDTYPE             NUMBER,
   OWNTYPE              NUMBER,
   SUBJECT              VARCHAR2(300),
   CONTENT              CLOB,
   CREATETIME           DATE,
   SENDTIME             DATE,
   LOCATION             NUMBER,
   READSTATE            VARCHAR2(1),
   HASATTACHMENT        VARCHAR2(1),
   ISVALID              VARCHAR2(1),
   MESSAGEID            VARCHAR2(300),
   constraint PK_F_MAIL_INFO primary key (ID)
);

comment on table F_MAIL_INFO is
'注意
1、如果收信人不是我们系统里的用户，那么收信的记录将不插入到表中
2、如果当前用户没有设置邮箱配置，那么收信记录也不插入到表中
3、不要用配置id去关联配置表，因为当配置记录删除的话，我们可以不删除邮件，下次重新配置的话，我们还能看到以前的邮件';

comment on column F_MAIL_INFO.SENDTYPE is
'11-普通发送 12-回复发送 13-转发 14-回复全部';

comment on column F_MAIL_INFO.OWNTYPE is
'20-发送 21-普通发送接收 22-抄送接收 23--密送接收 ';

comment on column F_MAIL_INFO.LOCATION is
'1-发件箱 2-收件箱 3-草稿箱';

comment on column F_MAIL_INFO.READSTATE is
'T-已读 F-未读';

comment on column F_MAIL_INFO.HASATTACHMENT is
'T-有 F-无';

comment on column F_MAIL_INFO.ISVALID is
'T-有效 F-无效';

comment on column F_MAIL_INFO.MESSAGEID is
'邮箱服务器上消息头的id，用来判断是否已经接收过该邮件，系统发出的邮件该字段为0，该字段需要配合账号一起使用';



create sequence SEQ_F_MAIL_INFO;


drop table F_MAIL_ATTACHMENT cascade constraints;

/*==============================================================*/
/* Table: F_MAIL_ATTACHMENT                                     */
/*==============================================================*/
create table F_MAIL_ATTACHMENT 
(
   ID                   NUMBER               not null,
   MAIL_INFO_ID         NUMBER,
   FILENAME             VARCHAR2(100),
   FILESIZE             NUMBER,
   PATH                 VARCHAR2(1000),
   constraint PK_F_MAIL_ATTACHMENT primary key (ID)
);

create sequence SEQ_F_MAIL_ATTACHMENT;