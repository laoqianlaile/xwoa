package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaSmssend;
import com.centit.oa.po.OaSmssendLog;
import com.centit.oa.dao.OaSmssendLogDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.service.OaSmssendLogManager;

public class OaSmssendLogManagerImpl extends BaseEntityManagerImpl<OaSmssendLog>
    implements OaSmssendLogManager{
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OaSmssendLogManager.class);

    private OaSmssendLogDao oaSmssendLogDao ;
    public void setOaSmssendLogDao(OaSmssendLogDao baseDao)
    {
        this.oaSmssendLogDao = baseDao;
        setBaseDao(this.oaSmssendLogDao);
    }


    @Override
    public String getNextValueOfSequence() {
        // TODO Auto-generated method stub
        return oaSmssendLogDao.getNextValueOfSequence("S_SMSSEND_LOG");
    }

    /**
     * 保存对应的短信发送记录
     * @param oaSmssend
     */
    public void saveSMSLogs(OaSmssend oaSmssend){
        
        if(null != oaSmssend){
            OaSmssendLog log = new OaSmssendLog();
            log.setNo(Long.parseLong(this.getNextValueOfSequence()));
            /*log.setOaSmssend(oaSmssend);*/
            log.setSmsid(oaSmssend.getSmsid());
            log.setContent(oaSmssend.getContent());
            log.setDatasource(oaSmssend.getDatasource());
            log.setMsgId(oaSmssend.getMsgId());
            log.setRestoremessage(oaSmssend.getRestoremessage());
            log.setSendtime(oaSmssend.getSendtime());
            log.setSequenceId(oaSmssend.getSequenceId());
            log.setState(oaSmssend.getState());
            log.setSucceedTime(oaSmssend.getSucceedTime());
            
            this.saveObject(log);
        }
    }
   
   
}

