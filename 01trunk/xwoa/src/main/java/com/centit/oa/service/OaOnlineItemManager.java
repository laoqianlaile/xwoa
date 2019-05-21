package com.centit.oa.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaOnlineItem;
import com.centit.oa.po.OaOnlineItems;

public interface OaOnlineItemManager extends BaseEntityManager<OaOnlineItem> {
    /**
     * 保存题目信息
     * @param oaOnlineItem
     * @param oaOnlineItems 选项信息
     */
    void saveObject(OaOnlineItem oaOnlineItem, List<OaOnlineItems> oaOnlineItems);

    public String getNextKey();

}
