package com.centit.oa.service;

import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaLeaveMessage;

public interface OaLeaveMessageManager extends BaseEntityManager<OaLeaveMessage> 
{
    /**
     * 计算某一栏目类型帖子总数，和今天的帖子总数
     * 返回一个map,key分别为total,today
     */
    public Map<String,Object> getMount(String columnType);
    
    /**
     * 根据业务id和留言类型批量删除留言 ，逻辑删除
     * @param djId
     * @param infoType
     */
    public void deleteByDjId(String djId,String infoType);
}
