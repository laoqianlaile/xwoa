CREATE TABLE OPT_DASHBOARD_MODULE(
            id                   number not null,
            MODULENAME           VARCHAR2(100),
            DISPLAYURL           VARCHAR2(1000),
            LINKURL              VARCHAR2(1000),
            MODULECODE           VARCHAR2(100),
            ISUSED               VARCHAR2(1),
            constraint PK_OPT_DASHBOARD_MODULE primary key (ID)
);
CREATE SEQUENCE SEQ_OPT_DASHBOARD_MODULE;

CREATE TABLE OPT_DASHBOARD_LAYOUT(
        ID                   NUMBER not null,
        LAYOUTNAME           VARCHAR2(100),
        LAYOUTTYPE           VARCHAR2(10),
        LAYOUTMETHODID       NUMBER,
        USERSCOPE            VARCHAR2(8),
        CONTENT              CLOB,
        REMARK               VARCHAR2(300),
        CREATEBY              VARCHAR2(8),
        CREATETIME           date,
        constraint PK_OPT_DASHBOARD_LAYOUT primary key (ID)
);
CREATE SEQUENCE SEQ_OPT_DASHBOARD_LAYOUT;

CREATE TABLE OPT_LAYOUT_ACTIVE(
    id          number not null, 
    USERCODE    VARCHAR2(8),
    LAYOUT_ID   NUMBER,
    constraint PK_OPT_LAYOUT_ACTIVE primary key (ID)
);
CREATE SEQUENCE SEQ_OPT_LAYOUT_ACTIVET;

CREATE TABLE OPT_LAYOUT_METHOD(
    id          number not null, 
    METHODNAME    VARCHAR2(100),
    CONTENT   CLOB,
     constraint PK_OPT_LAYOUT_METHOD primary key (ID)
);
CREATE SEQUENCE SEQ_OPT_LAYOUT_METHOD;