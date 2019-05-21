package com.centit.bbs.service;

import net.sf.json.JSONArray;

import com.centit.core.service.BaseEntityManager;
import com.centit.bbs.po.OaBbsDiscussion;

public interface OaBbsDiscussionManager extends
        BaseEntityManager<OaBbsDiscussion> {

    /**
     * 获取序列生成的主键
     * 
     * @return
     */
    public String getNextKey();

    /**
     * 更新讨论区子模块帖子回复数
     * 
     * @param layoutno
     */
    public void updatePostsNum(String layoutno);
    /**
     * 更新讨论区子模块主題數
     * @param layoutno
     */
    public void updateSubjectnum(String layoutno);
    
    /**
     * 根据板块号删除子版块
     * @param layoutno
     */
    public void deleteByLayoutCode(String layoutno);
    
    /**
     * 获取帖子管理树
     * 
     * @return
     */
    public JSONArray putLayoutTree(String usercode, String sParentUnit);
    
}
