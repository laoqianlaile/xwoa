package com.centit.app.service;

import java.util.List;

import com.centit.app.po.Forum;
import com.centit.app.po.Thread;
import com.centit.app.po.ThreadAnnex;
import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;

public interface ThreadManager extends BaseEntityManager<Thread> {

    /**
     * 保存帖子及附件
     * @param thread
     * @param threadAnnex
     */
    void saveObject(Thread thread, List<ThreadAnnex> threadAnnex);


    /**
     * 外部接口调用
     * @param forum 讨论版
     * @param titol 帖子名，本讨论版本中唯一
     * @return
     */
    Thread getExternalUse(Forum forum, String titol);
    
    
    List<Thread> listBySearch(String usercode, String content, PageDesc pageDesc);
    
    
    
}
