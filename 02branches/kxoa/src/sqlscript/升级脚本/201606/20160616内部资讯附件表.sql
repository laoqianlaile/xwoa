 create table OA_INFORMATION_attachment(
            id                   number not null,
            filename             VARCHAR2(100),
            filesize             number,
            path                 VARCHAR2(1000),
            type                 VARCHAR2(2),
            ref_id               VARCHAR2(32),
            constraint PK_OA_INFORMATION_attachment primary key (ID)
        );
create sequence SEQ_OA_INFORMATION_attachment;