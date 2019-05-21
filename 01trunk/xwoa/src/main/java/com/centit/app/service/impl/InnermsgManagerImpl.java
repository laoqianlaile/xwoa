package com.centit.app.service.impl;

import java.util.List;

import com.centit.app.dao.FileInfoDao;
import com.centit.app.dao.InnermsgDao;
import com.centit.app.po.FileInfo;
import com.centit.app.po.Innermsg;
import com.centit.app.po.Msgannex;
import com.centit.app.service.InnermsgManager;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.service.MessageSender;

public class InnermsgManagerImpl extends BaseEntityManagerImpl<Innermsg>
        implements InnermsgManager,MessageSender {
    private static final long serialVersionUID = 1L;
    private InnermsgDao innermsgDao;
    private FileInfoDao fileinfoDao;

    public void setInnermsgDao(InnermsgDao baseDao) {
        this.innermsgDao = baseDao;
        setBaseDao(this.innermsgDao);
    }

    public List<Innermsg> listReplyMsgs(Long replymsgcode){
        
        return innermsgDao.listMsgsByReplycode(replymsgcode);
    }
    
    public List<Innermsg> listMyMsgs(String receive){
        return innermsgDao.listMsgsByReceive(receive);
    }

    public boolean sendMessage(String sender, String receiver,
            String msgSubject, String msgContent) {
        Innermsg innermsg = new Innermsg();
        innermsg.setSender(sender);
        innermsg.setReceive(receiver);
        innermsg.setMsgtitle(msgSubject);
        innermsg.setMsgcontent(msgContent);
        innermsg.setSenddate(DatetimeOpt.currentSqlDate());
        innermsg.setMsgstate("U");
        innermsg.setReceivetype("P");

        innermsgDao.saveObject(innermsg);
        return true;
    }

    public void saveInnermsg(Innermsg innermsg) {
        Long msgCode = innermsg.getMsgcode();
        if(msgCode == null || msgCode == 0){
            msgCode = innermsgDao.getNextMsgCode();
            innermsg.setMsgcode(msgCode);
        }
        
        //获取文件编号，解析并封装Innermsg
            String[] codeArr = innermsg.getFileCodes();
            if (codeArr != null) {
                for (String fileCode : codeArr) {
                    Msgannex msgannex = new Msgannex();
                    msgannex.setMsgcode(msgCode);
                    msgannex.setFilecode(fileCode);
                    innermsg.addMsgannex(msgannex);
                }
            }
        
        innermsgDao.saveObject(innermsg);
    }

    public List<FileInfo> listFilesByMsg(Long msgcode) {
        return fileinfoDao.listFilesByMsg(msgcode);
    }

    public FileInfoDao getFileinfoDao() {
        return fileinfoDao;
    }

    public void setFileinfoDao(FileInfoDao fileInfoDao) {
        this.fileinfoDao = fileInfoDao;
    }

}
