package com.centit.oa.service.impl;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaMeetingApply;
import com.centit.oa.dao.OaMeetingApplyDao;
import com.centit.oa.dao.VOaMeetingMaterialApplyDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaMeetingApplyManager;
import com.centit.oa.po.VOaMeetingMaterialApply;
public class OaMeetingApplyManagerImpl extends BaseEntityManagerImpl<OaMeetingApply>
	implements OaMeetingApplyManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaMeetingApplyManager.class);
	private OaMeetingApplyDao oaMeetingApplyDao ;
	private VOaMeetingMaterialApplyDao oaMeetingMaterialApplyDao;
	 /**
	     * 获取我的会议
	     * @param filterMap
	     * @param pageDesc
	     * @return
	     */
	 public List<OaMeetingApply> oaMeetingList(Map<String,Object> filterMap, PageDesc pageDesc){
	     return oaMeetingMaterialApplyDao.oaMeetingList(filterMap,pageDesc);
	 }

    public OaMeetingApplyDao getOaMeetingApplyDao() {
        return oaMeetingApplyDao;
    }

    public void setOaMeetingApplyDao(OaMeetingApplyDao baseDao) {
        this.oaMeetingApplyDao = baseDao;
        setBaseDao(this.oaMeetingApplyDao);
    }

    @Override
    public String getDjId() {
        return "HYGL"+ oaMeetingApplyDao.getNextKeyBySequence("S_MEETINGAPPLY", 12);
    }

    public VOaMeetingMaterialApplyDao getOaMeetingMaterialApplyDao() {
        return oaMeetingMaterialApplyDao;
    }

    public void setOaMeetingMaterialApplyDao(
            VOaMeetingMaterialApplyDao oaMeetingMaterialApplyDao) {
        this.oaMeetingMaterialApplyDao = oaMeetingMaterialApplyDao;
    }
    
}

