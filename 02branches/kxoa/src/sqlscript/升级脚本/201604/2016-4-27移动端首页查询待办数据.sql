----按类别统计待办项
create or replace view v_user_task_dashboard_mip as
select v.datacode as itemtype,v.datavalue as name,v.USERCODE,nvl(t.num,'0') as num
 from (select d.datacode,d.datavalue,m.USERCODE
               from f_datadictionary d,f_userinfo m
      where d.catalogcode='oa_ITEM_TYPE') v

 left join (select u.itemtype,u.USERCODE,count(u.itemtype) as num
               from v_user_task_list_oa  u

    group by  u.itemtype,u.USERCODE) t on t.USERCODE=v.USERCODE and t.itemtype=v.datacode;