
      
      
create or replace view v_oa_dashboard as
select no,num,itemtype,usercode,name from v_user_email_dashboard
union
select 4 as no,num,itemtype,usercode,name from v_user_task_dashboard
union
select 5 as no,num,itemtype,usercode,name from v_user_remind_dashboard
--用不到，去除以便于加快查询速度
--union
--select no,num,itemtype,usercode,name from v_user_information_dashboard 
--union 
--select no,num,itemtype,usercode,name from v_user_incomedoc_dashboard




