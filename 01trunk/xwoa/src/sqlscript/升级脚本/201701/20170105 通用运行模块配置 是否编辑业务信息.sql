-- Add/modify columns 
alter table C_GENERAL_MODULE_PARAM add CANEDITOPT CHAR(1);
-- Add comments to the columns 
comment on column C_GENERAL_MODULE_PARAM.CANEDITOPT
  is '是否编辑业务信息';