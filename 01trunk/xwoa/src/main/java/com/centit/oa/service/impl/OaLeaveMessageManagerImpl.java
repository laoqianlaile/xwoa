package com.centit.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.bbs.dao.OaLeaveMessagereplyDao;
import com.centit.bbs.po.OaLeaveMessagereply;
import com.centit.bbs.service.OaLeaveMessagereplyManager;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaLeaveMessage;
import com.centit.oa.dao.OaHelpinfoDao;
import com.centit.oa.dao.OaLeaveMessageDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.oa.service.OaLeaveMessageManager;

public class OaLeaveMessageManagerImpl extends
        BaseEntityManagerImpl<OaLeaveMessage> implements OaLeaveMessageManager {
    private static final long serialVersionUID = 1L;
    public OaHelpinfoDao getOaHelpinfoDao() {
        return oaHelpinfoDao;
    }

    public void setOaHelpinfoDao(OaHelpinfoDao oaHelpinfoDao) {
        this.oaHelpinfoDao = oaHelpinfoDao;
    }

    public static final Log log = LogFactory
            .getLog(OaLeaveMessageManager.class);

    private OaLeaveMessageDao oaLeaveMessageDao;
    private OaHelpinfoDao oaHelpinfoDao ;
    public void setOaLeaveMessageDao(OaLeaveMessageDao baseDao) {
        this.oaLeaveMessageDao = baseDao;
        setBaseDao(this.oaLeaveMessageDao);
    }
    
    private OaLeaveMessagereplyDao oaLeaveMessagereplyDao ;
    public void setOaLeaveMessagereplyDao(OaLeaveMessagereplyDao baseDao)
    {
        this.oaLeaveMessagereplyDao = baseDao;
    }

    /**
     * 留言信息
     */
    @Override
    public void saveObject(OaLeaveMessage o) {
        if (!StringUtils.hasText(o.getNo())) {
            o.setNo("LYXX"
                    + oaLeaveMessageDao.getNextKeyBySequence("S_LEAVEMESSAGE_NO", 12));
        }
        super.saveObject(o);
    }

    @Override
    public Map<String, Object> getMount(String columnType) {
        // TODO Auto-generated method stub
        //1、得到未禁用的oaHelpInfo对象的所有主键
        List<String> djids=oaHelpinfoDao.getDjids();
        //2.统计未禁用的主键对应的所有oaleaveMessage数目
        return this.oaLeaveMessageDao.getMount(columnType,djids);
    }

    @Override
    public void deleteObject(OaLeaveMessage o) {
        OaLeaveMessage lm = super.getObjectById(o.getNo());
       if(lm != null){
           lm.setState("D");
           deleteByMsgNo(lm.getNo());
       }
    }
   /**
    * 删除该留言的回复
    * @param msgNo
    */
    private void deleteByMsgNo(String msgNo){
        Map<String,Object> filterMap = new HashMap<String,Object>();
        filterMap.put("lno", msgNo);
        filterMap.put("excludeStates", "D");
        List<OaLeaveMessagereply> replyList = oaLeaveMessagereplyDao.listObjects(filterMap);
        if(replyList != null && replyList.size() > 0){
            for(OaLeaveMessagereply o : replyList){
                o.setState("D");
            }
        }
    }
    @Override
    public void deleteByDjId(String djId, String infoType) {
        if(StringUtils.hasText(djId) && StringUtils.hasText(infoType)){
            Map<String,Object> filterMap = new HashMap<String,Object>();
            filterMap.put("djid", djId);
            filterMap.put("infoType", infoType);
            filterMap.put("excludeStates", "D");
            //获取所有留言根据条件
            List<OaLeaveMessage> lmList = super.listObjects(filterMap);
            //修改查出记录的状态
            if(lmList !=null && !lmList.isEmpty()){
                for(OaLeaveMessage msg : lmList){
                    msg.setState("D");
                    deleteByMsgNo(msg.getNo());
                }
            }
        }
    }
}
