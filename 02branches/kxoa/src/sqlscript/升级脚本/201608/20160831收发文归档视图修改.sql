

create or replace view v_oa_archive_incomedoc as
select distinct
t.no, '' as usercode,'' as belongUnitcode,t.income_doc_title,t.income_date,t.acceptarchiveno,t.income_dept_name,
t.income_doc_no,t.income_doc_type,t.income_dept_type,t.item_source,m.id,m.unitcode,m.createtime,m.lastmodifytime,m.updateuser,t.bizstate

from  v_oa_income_doc_list t
left join OA_ARCHIVE m on m.no=t.no --and t.belongUnitcode=m.unitcode
where t.bizstate ='C';  --办结



create or replace view v_oa_archive_dispatchdoc as
select distinct
t.no,'' as usercode,t.transaffairname,t.dispatch_doc_no,t.opt_unit_name,t.main_notify_unit,t.other_units,t.create_date,
m.id,m.unitcode,m.createtime,m.lastmodifytime,m.updateuser,t.dispatch_user,t.draft_user_name,t.bizstate

from  v_oa_dispatch_doc_list t
left join OA_ARCHIVE m on m.no=t.no
where --t.dispatch_doc_type='T' and
t.dispatch_doc_no is not null and t.bizstate='C'; --办结
;
