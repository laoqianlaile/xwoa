package com.centit.oa.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaHelpinfo;

public interface OaHelpinfoManager extends BaseEntityManager<OaHelpinfo> 
{
    //得到对象主键
    public String getNextkey();
    //根据用户输入的字符模糊搜索在infoname，remark与输入字符类似的oahelpinfo的所有对象
    public List<OaHelpinfo> search(String search,PageDesc pageDesc);
    /**
     * 当给oahelpinfo对象留言时，增加其回复数replynum
     */
    public void updateAfterReply(String djid);
    /**
     *去上传附件的搜索 
     */
    public List<OaHelpinfo> listOahelpinfo(Map<String,Object> filterMap,PageDesc pageDesc);
}
