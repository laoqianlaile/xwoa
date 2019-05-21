package com.centit.app.service;

import com.centit.app.po.Forum;
import com.centit.app.po.ForumStaff;
import com.centit.app.po.ForumType;
import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;

import java.util.List;
import java.util.Map;


public interface ForumManager extends BaseEntityManager<Forum> {
    /**
     * 创建讨论版，并添加版主
     *
     * @param forum
     * @param forumStaff
     */
    void saveObject(Forum forum, ForumStaff forumStaff, List<ForumType> forumTypes);


    /**
     * 加入，退出讨论版
     *
     * @param forum
     * @param forumStaff
     */
    void saveObjectByInOut(Forum forum, ForumStaff forumStaff);


    List<Forum> listConfirmApplyJoin(Forum forum, PageDesc pageDesc);

    
    /**
     * 当前用户申请加入讨论版
     * @param params
     * @param pageDesc
     * @return
     */
    Map<Forum, Integer> listByJoinRight(Map<String, Object> params, PageDesc pageDesc);
    

    /**
     * 外部调用的讨论版
     * @param forumname
     * @return
     */
    Forum getExternalUse(String boardcode, String forumname);

}
