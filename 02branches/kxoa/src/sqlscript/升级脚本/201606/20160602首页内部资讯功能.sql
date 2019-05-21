create or replace view v_oa_information as
select i."NO",i."INFO_TYPE",i."TITLE",i."MAJOR_DEGREE",i."RELEASE_DATE",i."VALID_DATE",i."PUBLIC_KEY",i."DOC_NO",i."AUTHOR",i."UPLOAD_FILE_NAME",i."REFERENCE_LINKS",i."LASTMODIFYTIME",i."CREATER",i."CREATERTIME",i."STATE",i."DEPNO",i."ISALLOWCOMMENT",i."IMAGEPATH",i.infostate as READSTATE,
       --   (case when f.id is null then 'F' else 'T' end ) as readState, --通知公告视图 阅读状态标识
          case when sysdate -i.creatertime<=3
           -- and f.id is null
             then 1 else 0 end  as newmsg --标记最新消息
      --   ,n.usercode
        from OA_INFORMATION i
       -- left join F_userBizopt_log f on f.dj_id=i.no and f.createuser=i.creater
      -- join f_userinfo n on n.usercode=f.createuser;
