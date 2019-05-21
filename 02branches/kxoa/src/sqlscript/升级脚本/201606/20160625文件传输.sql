drop table OPT_FILETRANSFER_SEND;
CREATE TABLE OPT_FILETRANSFER_SEND(
            ID                   VARCHAR2(32) not null,
            RECEIVERNAME         CLOB,
            SENDERCODE           VARCHAR2(8),
            SCOPETYPE            VARCHAR2(5),
            SENDTYPE             NUMBER,
            CREATETIME           DATE,
            SUBJECT              VARCHAR2(600),
            REMARK               VARCHAR2(1000),
            constraint PK_OPT_FILETRANSFER_SEND primary key (ID)
);
CREATE SEQUENCE SEQ_OPT_FILETRANSFER_SEND;

drop table OPT_FILETRANSFER_RECEIVE;

CREATE TABLE OPT_FILETRANSFER_RECEIVE(
      ID                   VARCHAR2(32) not null,
      SENDERCODE           VARCHAR2(8),
      SENDERNAME           VARCHAR2(64),
      RECEIVERCODE         VARCHAR2(8),
      SCOPETYPE            VARCHAR2(5),
      CREATETIME           DATE,
      SUBJECT              VARCHAR2(600),
      REMARK               VARCHAR2(1000),
      constraint PK_OPT_FILETRANSFER_RECEIVE primary key (ID)
);
CREATE SEQUENCE SEQ_OPT_FILETRANSFER_RECEIVE;

DROP TABLE OPT_FIlING_CABINETS;
CREATE TABLE OPT_FIlING_CABINETS(
            ID                   NUMBER not null,
            REF_ID               VARCHAR2(32),
            FILESIZE             NUMBER,
            FILENAME             VARCHAR2(100),
            FILEPATH             VARCHAR2(1000),
            constraint PK_OPT_FIlING_CABINETS primary key (ID)
);

CREATE SEQUENCE SEQ_OPT_FIlING_CABINETS;