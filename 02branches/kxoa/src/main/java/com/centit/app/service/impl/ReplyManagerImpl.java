package com.centit.app.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.app.dao.ReplyAnnexDao;
import com.centit.app.dao.ReplyDao;
import com.centit.app.po.FileinfoFs;
import com.centit.app.po.Reply;
import com.centit.app.po.ReplyAnnex;
import com.centit.app.service.FileinfoFsManager;
import com.centit.app.service.FileinfoManager;
import com.centit.app.service.ReplyManager;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.support.searcher.DocDesc;

public class ReplyManagerImpl extends BaseEntityManagerImpl<Reply>
        implements ReplyManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(ReplyManager.class);

    //private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

    private ReplyDao replyDao;

    private ReplyAnnexDao replyAnnexDao;

    public void setReplyAnnexDao(ReplyAnnexDao replyAnnexDao) {
        this.replyAnnexDao = replyAnnexDao;
    }
    
	public void setReplyDao(ReplyDao baseDao) {
        this.replyDao = baseDao;
        setBaseDao(this.replyDao);
    }
    
    private FileinfoFsManager fileinfoFsManager;



    public void setFileinfoFsManager(FileinfoFsManager fileinfoFsManager) {
        this.fileinfoFsManager = fileinfoFsManager;
    }

    @Override
    public void saveObject(Reply reply, List<ReplyAnnex> replyAnnexs) {

        baseDao.saveObject(reply);

        for (ReplyAnnex replyAnnex : replyAnnexs) {
            replyAnnex.getCid().setReplyid(reply.getReplyid());
            
            FileinfoFs fileinfo = fileinfoFsManager.getObjectById(replyAnnex.getCid().getFilecode());
            
            DocDesc docDesc = new DocDesc(DocDesc.ResType.FILE, "THREAD", reply.getUserid(), "THREAD_" + fileinfo.getFilecode(),
                    reply.getThread().getTitol(), null, "view", "thread.threadid=" + reply.getThread().getThreadid());

            fileinfoFsManager.update4ConfirmFileinfo(fileinfo, docDesc);

            replyAnnexDao.saveObject(replyAnnex);
        }
    }
    
    
}

