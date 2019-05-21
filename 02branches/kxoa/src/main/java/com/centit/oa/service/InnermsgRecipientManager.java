package com.centit.oa.service;

import com.centit.oa.po.Innermsg;
import com.centit.oa.po.InnermsgRecipient;
import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;

import java.util.List;

public interface InnermsgRecipientManager extends
        BaseEntityManager<InnermsgRecipient> {
    /**
     * 当前用户未读消息
     * 
     * @param ir
     * @return
     */
    List<InnermsgRecipient> listUnReaderInnermsg(InnermsgRecipient ir);

    void delete(List<String> pk);

    List<InnermsgRecipient> listBySearch(String usercode, String key,
            PageDesc pageDesc);

    List<InnermsgRecipient> listByRtype(String msgCode, String recipientType);

    /**
     * 删除收件箱中记录（包括抄送，密送记录）
     * 
     * @param pk
     * @param loginUserCode
     */
    void deleteRec(List<String> pk, String loginUserCode);
    
    /**
     * 删除收件箱中记录（包括抄送，密送记录）----废件箱
     * 
     * @param pk
     * @param loginUserCode
     */
    void dropRec(List<String> pk, String loginUserCode);
    /**
     * 还原废件箱到收件箱
     * @param pk
     * @param loginUserCode
     */
    void cancleDropRec(List<String> pk, String loginUserCode);
    /**
     * 查看时收件箱与本人有关的该条收件msgstat都更新为已读
     * @param innermsg
     * @param loginUserCode
     */
    void updateMsgStat(Innermsg innermsg, String loginUserCode);
}
