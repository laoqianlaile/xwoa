package com.centit.oa.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.dao.OptFileTransferSendDao;
import com.centit.oa.po.OptFileTransferReceive;
import com.centit.oa.po.OptFileTransferSend;
import com.centit.oa.po.OptFilingCabinets;
import com.centit.oa.service.OptFileTransferReceiveManager;
import com.centit.oa.service.OptFileTransferSendManager;
import com.centit.oa.service.OptFilingCabinetsManager;
import com.centit.sys.util.SysParametersUtils;

public class OptFileTransferSendManagerImpl extends BaseEntityManagerImpl<OptFileTransferSend> 
   implements OptFileTransferSendManager{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private OptFileTransferSendDao optFileTransferSendDao;
    
    private OptFilingCabinetsManager optFilingCabinetsMgr;
    private OptFileTransferReceiveManager optFileTransferReceiveMgr;
    
    public OptFileTransferSendDao getOptFileTransferSendDao() {
        return optFileTransferSendDao;
    }

    public void setOptFileTransferSendDao(
            OptFileTransferSendDao optFileTransferSendDao) {
        this.optFileTransferSendDao = optFileTransferSendDao;
        super.setBaseDao(optFileTransferSendDao);
    }
    
    public OptFilingCabinetsManager getOptFilingCabinetsMgr() {
        return optFilingCabinetsMgr;
    }

    public void setOptFilingCabinetsMgr(
            OptFilingCabinetsManager optFilingCabinetsMgr) {
        this.optFilingCabinetsMgr = optFilingCabinetsMgr;
    }

    public OptFileTransferReceiveManager getOptFileTransferReceiveMgr() {
        return optFileTransferReceiveMgr;
    }


    public void setOptFileTransferReceiveMgr(
            OptFileTransferReceiveManager optFileTransferReceiveMgr) {
        this.optFileTransferReceiveMgr = optFileTransferReceiveMgr;
    }


    @Override
    public String getNextSequence() {
        return "IOS"+optFileTransferSendDao.getNextKeyBySequence("SEQ_OPT_FILETRANSFER_SEND",14);
    }

    @Override
    public void saveObject(OptFileTransferSend o,File[] files,String[] fileName){
        //保存发信信息
        String sendSeq = getNextSequence();
        o.setId(sendSeq);
        super.saveObject(o);
        
        List<OptFilingCabinets> optFilingCabinetsArr =   optFilingCabinetsMgr.save(sendSeq, SysParametersUtils.getFilingCabinetsHome(),
                File.separator + sendSeq, files, fileName);
        
        //保存收件信息
        String receiversCodes = o.getReceiverCode();
        if(receiversCodes != null){
            String[] receiverCodeArr = receiversCodes.split(",");
            for(String receiverCode : receiverCodeArr){
                OptFileTransferReceive optFileTransferReceive = new OptFileTransferReceive();
                String receiveSeq = optFileTransferReceiveMgr.getNextSequence();
                optFileTransferReceive.setId(receiveSeq);
                optFileTransferReceive.setReceiverCode(receiverCode);
                optFileTransferReceive.setScopeType(o.getScopeType());
                optFileTransferReceive.setSenderCode(o.getSenderCode());
                optFileTransferReceive.setSenderName(o.getSenderName());
                optFileTransferReceive.setCreateTime(new Date());
                optFileTransferReceive.setSubject(o.getSubject());
                optFileTransferReceive.setRemark(o.getRemark());
                
                optFileTransferReceiveMgr.saveObject(optFileTransferReceive);
                optFilingCabinetsMgr.save(optFilingCabinetsArr,receiveSeq);
            }
        }
    }
}
