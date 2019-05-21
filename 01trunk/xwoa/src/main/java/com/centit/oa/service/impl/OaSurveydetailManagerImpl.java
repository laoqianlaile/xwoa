package com.centit.oa.service.impl;

import java.util.Date;
import java.util.List;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaOnlineItem;
import com.centit.oa.po.OaOnlineItems;
import com.centit.oa.po.OaSurveydetail;
import com.centit.oa.po.OaSurveydetailId;
import com.centit.oa.po.VOaSurveyDetail;
import com.centit.oa.dao.OaOnlineItemDao;
import com.centit.oa.dao.OaOnlineItemsDao;
import com.centit.oa.dao.OaSurveydetailDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.centit.oa.service.OaSurveydetailManager;
import com.centit.sys.dao.UserInfoDao;
import com.centit.sys.po.FUserinfo;

public class OaSurveydetailManagerImpl extends
        BaseEntityManagerImpl<OaSurveydetail> implements OaSurveydetailManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(OaSurveydetailManager.class);

    private OaSurveydetailDao oaSurveydetailDao;
    private OaOnlineItemsDao oaOnlineItemsDao;// 根据选项明细itemid获取明细详情
    private OaOnlineItemDao oaOnlineItemDao;// 根据选项题目no获取题目类型
    private UserInfoDao sysUserDao;

    public void setOaSurveydetailDao(OaSurveydetailDao baseDao) {
        this.oaSurveydetailDao = baseDao;
        setBaseDao(this.oaSurveydetailDao);
    }

    /**
     * 调查信息明细
     */
    @Override
    public void saveObject(OaSurveydetail o) {
        if (!StringUtils.hasText(o.getItemid())) {
            o.setItemid("DCMX"
                    + oaSurveydetailDao.getNextKeyBySequence(
                            "S_OA_SURVEYDETAIL_NO", 12));
        }
        super.saveObject(o);
    }

    /**
     * 保存调查明细信息
     * 
     * @param OaSurveydetails
     */
    @Override
    public void saveSurveydetail(List<OaSurveydetail> oaSurveydetails,
            String usercode) {
        
        

        if (!CollectionUtils.isEmpty(oaSurveydetails)) {
            for (OaSurveydetail surveydetail : oaSurveydetails) {
//                surveydetail.getCid().setCreater(usercode);// 投票者
//                surveydetail.setCreatetime(new Date());// 发起时间
                String itemid=surveydetail.getItemid();
                String no=surveydetail.getNo();
                // 多选框需要分值
                if (StringUtils.hasText(itemid)) {
                    String[] itemids = itemid.split(",");
                   for (String s : itemids) {
                        OaOnlineItems oaOnlineItems = oaOnlineItemsDao
                                .getObjectById(s.trim());
                        OaOnlineItem oaOnlineItem = oaOnlineItemDao
                                .getObjectById(no);
                        OaSurveydetail detail=new OaSurveydetail(new OaSurveydetailId(s.trim(),usercode));
//                        detail.getCid().setCreater(usercode);// 投票者
//                        detail.getCid().setItemid(s);//itemid
                        detail.setCreatetime(new Date());// 发起时间
                        detail.setDjid(surveydetail.getDjid());//调查ID
                        detail.setNo(surveydetail.getNo());//题目流水号
                        // 单选，多选选项明细根据itemid获取，问答题title从页面获取
                        if ("3".equals(oaOnlineItem.getChosetype())) {
                            detail.setTitle(surveydetail.getTitle());
                            
                        }else {
                            detail.setTitle(oaOnlineItems.getTitle());// 选项明细名称
                        }
                        this.saveObject(detail);
                    }
                   
                }

               
            }

        }

    }
    /**
     * 按調查id獲取人員列表
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<FUserinfo> detailList(String djid,PageDesc pageDesc) {
        String shql = "from FUserinfo u where u.usercode " +
                " in (select distinct o.cid.creater  from OaSurveydetail o  where o.djid=?)  ";
        return (List<FUserinfo>) sysUserDao.findObjects(shql,new Object[]{djid} , pageDesc);
      
    }
    
    public OaOnlineItemsDao getOaOnlineItemsDao() {
        return oaOnlineItemsDao;
    }

    public void setOaOnlineItemsDao(OaOnlineItemsDao oaOnlineItemsDao) {
        this.oaOnlineItemsDao = oaOnlineItemsDao;
    }

    public OaOnlineItemDao getOaOnlineItemDao() {
        return oaOnlineItemDao;
    }

    public void setOaOnlineItemDao(OaOnlineItemDao oaOnlineItemDao) {
        this.oaOnlineItemDao = oaOnlineItemDao;
    }

   

    public UserInfoDao getSysUserDao() {
        return sysUserDao;
    }

    public void setSysUserDao(UserInfoDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

   
    public List<VOaSurveyDetail> detailViewList(String djid, String creater) {
        List<VOaSurveyDetail> list=oaSurveydetailDao.getSurveyDetail(djid,creater);
        return list;
    }



}
