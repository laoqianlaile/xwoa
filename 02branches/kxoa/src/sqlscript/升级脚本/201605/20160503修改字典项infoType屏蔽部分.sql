


update F_DATADICTIONARY  t set  t.datatag='F' where t.catalogcode='infoType' 
and (t.datacode='public' or t.datacode='rsrm' or t.datacode='rules' or t.datacode='ghjh' or t.datacode='doc' or t.datacode='lzxx' or t.datacode='image' or t.datacode='fgwj')