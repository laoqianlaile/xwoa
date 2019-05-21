--

--用车事由
insert into f_datacatalog (CATALOGCODE, CATALOGNAME, CATALOGSTYLE, CATALOGTYPE, CATALOGDESC, FIELDDESC, ISUPLOAD, DICTIONARYTYPE, CREATEDATE, LASTMODIFYDATE)
values ('req_remark', '用车事由', 'U', 'L', '', '', '0', 'GR', null, null);


----用车事由明细

insert into f_datadictionary (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('req_remark', 'A', '', '1', 'T', '执行重大抢险救灾，事故处理，突发事件处置等不可预测的特殊任务', 'S', '执行重大抢险救灾，事故处理，突发事件处置等不可预测的特殊任务', null, null);

insert into f_datadictionary (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('req_remark', 'B', '', '2', 'T', '参加省、市级领导机关和有关部门紧急召集的跨部门会议和活动', 'S', '参加省、市级领导机关和有关部门紧急召集的跨部门会议和活动', null, null);

insert into f_datadictionary (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('req_remark', 'C', '', '3', 'T', '参加重要公务接待和外事等活动', 'S', '参加重要公务接待和外事等活动', null, null);

insert into f_datadictionary (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('req_remark', 'D', '', '4', 'T', '单位统一组织的集体活动', 'S', '单位统一组织的集体活动', null, null);

insert into f_datadictionary (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('req_remark', 'E', '', '5', 'T', '送、取数量较多的公务用品', 'S', '送、取数量较多的公务用品', null, null);

insert into f_datadictionary (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('req_remark', 'F', '', '6', 'T', '财务人员取、送大额现金', 'S', '财务人员取、送大额现金', null, null);

insert into f_datadictionary (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('req_remark', 'G', '', '7', 'T', '干部职工因伤因病需紧急送医', 'S', '干部职工因伤因病需紧急送医', null, null);

insert into f_datadictionary (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('req_remark', 'H', '', '8', 'T', '离退休干部政策范围内用车', 'S', '离退休干部政策范围内用车', null, null);

insert into f_datadictionary (CATALOGCODE, DATACODE, EXTRACODE, EXTRACODE2, DATATAG, DATAVALUE, DATASTYLE, DATADESC, LASTMODIFYDATE, CREATEDATE)
values ('req_remark', 'I', '', '9', 'T', '经单位领导批准的其他特殊情况用车', 'S', '经单位领导批准的其他特殊情况用车', null, null);

