create or replace view v_user_task_list_oa as
select rownum as taskid, t."WFINSTID",t."WFOPTNAME",t."WFOPTTAG",t."NODEINSTID",t."UNITCODE",t."USERCODE",t."ROLETYPE",t."ROLECODE",t."AUTHDESC",t."NODENAME",
t."NODETYPE",t."NODEOPTTYPE",t."OPTNAME",t."METHODNAME",t."OPTURL",t."OPTMETHOD",t."OPTPARAM",t."OPTDESC",t."CREATETIME",t."PROMISETIME",t."TIMELIMIT",t."EXPIREOPTSIGN",
t."OPTCODE",t."EXPIREOPT",t."FLOWPHASE",t."NODECODE",t."GRANTOR",t."LASTUPDATEUSER",t."LASTUPDATETIME",t."INSTSTATE",t."DJ_ID",t."FLOWINSTID",t."TRANSAFFAIRNAME",
t."BIZSTATE",t."BIZTYPE",t."ORGCODE",t."ORGNAME",t."HEADUNITCODE",t."HEADUSERCODE",t."CONTENT",t."POWERID",t."POWERNAME",t."SOLVESTATUS",t."SOLVETIME",t."SOLVENOTE",
t."CREATEUSER",t."CREATEDATE",t."OPTNO",t."OPTQUERYKEY",t."RISKTYPE",t."RISKDESC",t."RISKRESULT",t."TRANSAFFAIRNO",t."TRANSAFFAIRQUERYKEY",t."OPTID",t."SENDARCHIVENO",
t."ACCEPTARCHIVENO",t."CASENO",t."FLOWCODE",t."ISUPLOAD",t."SIGN",t."SIGN2",t."DBTYPE",t."SUPDJID",t."WARNTYPE",t."ITEMTYPE",t."READSTATE",t.newwarn,t.criticalLevel from ( select

distinct t.WFINSTID,e.TRANSAFFAIRNAME as WFOPTNAME,t.WFOPTTAG,t.NODEINSTID,t.UNITCODE,t.USERCODE,
       t.ROLETYPE,t.ROLECODE,t.AUTHDESC,t.NODENAME,t.NODETYPE,t.NODEOPTTYPE,t.OPTNAME,
       t.METHODNAME,t.OPTURL,t.OPTMETHOD,t.OPTPARAM,t.OPTDESC,t.CREATETIME,t.PROMISETIME,
       t.TIMELIMIT,t.EXPIREOPTSIGN,t.OPTCODE,t.EXPIREOPT,t.FLOWPHASE,t.NODECODE,t.GRANTOR,t.LASTUPDATEUSER,t.LASTUPDATETIME,t.inststate,
       e.DJ_ID,e.FLOWINSTID,e.TRANSAFFAIRNAME,e.BIZSTATE,e.BIZTYPE,e.ORGCODE,e.ORGNAME,e.HEADUNITCODE,e.HEADUSERCODE,e.CONTENT,e.POWERID,
       e.POWERNAME,e.SOLVESTATUS,e.SOLVETIME,e.SOLVENOTE,e.CREATEUSER,e.CREATEDATE,e.OPTNO,e.OPTQUERYKEY,e.RISKTYPE,e.RISKDESC,e.RISKRESULT,
       e.TRANSAFFAIRNO,e.TRANSAFFAIRQUERYKEY,e.OPTID,e.SENDARCHIVENO,e.ACCEPTARCHIVENO,e.CASENO,e.FLOWCODE,e.ISUPLOAD,e.SIGN,e.SIGN2,e.DBTYPE,
       --b.ITEM_TYPE as itemtype,
       o.supdjid,o.warntype,substr(e.dj_id,1,instr(e.dj_id,'0')-1) as itemtype,e.warntype as newwarn,
       (case when f.id is null then 'F' else 'T' end ) as readState --待办试图 阅读状态标识
       ,case when e.critical_level is null then '0' else e.critical_level end as criticalLevel --缓急程度
    from v_user_task_list t
    left join opt_base_info e on t.WFINSTID=e.flowinstid
   -- left join m_income_doc v on v.no=e.dj_id
    left join oa_supervise o on o.djid=e.dj_id
   -- left join opt_apply_info a on a.dj_id=e.dj_id
    left join b_v_powerinusing b on b.ITEM_ID=e.powerid
    left join F_userBizopt_log f on f.dj_id=e.dj_id and f.createuser=t.USERCODE and f.nodeinstid= t.NODEINSTID
     where t.OPTID =e.optid  and length(t.UNITCODE)<=6

     union
     select distinct t.WFINSTID,e.TRANSAFFAIRNAME as WFOPTNAME,t.WFOPTTAG,t.NODEINSTID,t.UNITCODE,t.USERCODE,
       t.ROLETYPE,t.ROLECODE,t.AUTHDESC,t.NODENAME,t.NODETYPE,t.NODEOPTTYPE,t.OPTNAME,
       t.METHODNAME,t.OPTURL,t.OPTMETHOD,t.OPTPARAM,t.OPTDESC,t.CREATETIME,t.PROMISETIME,
       t.TIMELIMIT,t.EXPIREOPTSIGN,t.OPTCODE,t.EXPIREOPT,t.FLOWPHASE,t.NODECODE,t.GRANTOR,t.LASTUPDATEUSER,t.LASTUPDATETIME,t.inststate,
       e.DJ_ID,e.FLOWINSTID,e.TRANSAFFAIRNAME,e.BIZSTATE,e.BIZTYPE,e.ORGCODE,e.ORGNAME,e.HEADUNITCODE,e.HEADUSERCODE,e.CONTENT,e.POWERID,
       e.POWERNAME,e.SOLVESTATUS,e.SOLVETIME,e.SOLVENOTE,e.CREATEUSER,e.CREATEDATE,e.OPTNO,e.OPTQUERYKEY,e.RISKTYPE,e.RISKDESC,e.RISKRESULT,
       e.TRANSAFFAIRNO,e.TRANSAFFAIRQUERYKEY,e.OPTID,e.SENDARCHIVENO,e.ACCEPTARCHIVENO,e.CASENO,e.FLOWCODE,e.ISUPLOAD,e.SIGN,e.SIGN2,e.DBTYPE,
       --b.ITEM_TYPE as itemtype,
       o.supdjid,o.warntype,substr(e.dj_id,1,instr(e.dj_id,'0')-1) as itemtype,e.warntype as newwarn,
       (case when f.id is null then 'F' else 'T' end ) as readState --待办试图 阅读状态标识
        ,case when e.critical_level is null then '0' else e.critical_level end as criticalLevel   --缓急程度
    from v_user_task_list t
    left join opt_base_info e on t.WFINSTID=e.flowinstid
   -- left join m_income_doc v on v.no=e.dj_id
    left join oa_supervise o on o.djid=e.dj_id
   -- left join opt_apply_info a on a.dj_id=e.dj_id
    left join b_v_powerinusing b on b.ITEM_ID=e.powerid
    left join F_userBizopt_log f on f.dj_id=e.dj_id and f.createuser=t.USERCODE and f.nodeinstid= t.NODEINSTID
     where t.OPTID =e.optid and length(t.UNITCODE)>6 and t.UNITCODE=t.USERCODE --此处处理因为多实例用人员代码替代部门的情况，所以用人员代码和机构代码相等进行关联

     ) t
      order by t.criticalLevel desc ,t.CREATETIME desc
;
