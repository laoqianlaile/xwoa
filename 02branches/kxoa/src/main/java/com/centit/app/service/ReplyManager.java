package com.centit.app.service;

import java.util.List;

import com.centit.app.po.Reply;
import com.centit.app.po.ReplyAnnex;
import com.centit.core.service.BaseEntityManager;

public interface ReplyManager extends BaseEntityManager<Reply> {
    void saveObject(Reply reply, List<ReplyAnnex> replyAnnexs);
    
    
    
}
