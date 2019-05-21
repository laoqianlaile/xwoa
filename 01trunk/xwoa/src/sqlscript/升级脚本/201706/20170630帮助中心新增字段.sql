----帮助中心新增字段
alter table oa_helpinfo
add (optid varchar2(20));
alter table oa_helpinfo
add (supoptid varchar2(20));
alter table oa_helpinfo
add (optname varchar2(20));