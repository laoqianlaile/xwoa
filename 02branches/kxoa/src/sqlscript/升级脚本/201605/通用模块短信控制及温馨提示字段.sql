-- Add/modify columns 
alter table C_GENERAL_MODULE_PARAM add tips VARCHAR2(4000);
alter table C_GENERAL_MODULE_PARAM add cansendmessage CHAR(1);
-- Add comments to the columns 
comment on column C_GENERAL_MODULE_PARAM.tips
  is '温馨提示';
-- Add comments to the columns 
comment on column C_GENERAL_MODULE_PARAM.cansendmessage
  is '是否可以发送短信';