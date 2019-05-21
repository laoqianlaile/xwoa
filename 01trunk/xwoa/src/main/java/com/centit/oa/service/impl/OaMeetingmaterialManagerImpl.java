package com.centit.oa.service.impl;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaCarinfo;
import com.centit.oa.po.OaMeetingmaterial;
import com.centit.oa.po.OaMeetingmaterialinfos;
import com.centit.oa.dao.OaMeetingmaterialDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.oa.service.OaMeetingmaterialManager;

public class OaMeetingmaterialManagerImpl extends BaseEntityManagerImpl<OaMeetingmaterial>
	implements OaMeetingmaterialManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaMeetingmaterialManager.class);

	private OaMeetingmaterialDao oaMeetingmaterialDao ;
	public void setOaMeetingmaterialDao(OaMeetingmaterialDao baseDao)
	{
		this.oaMeetingmaterialDao = baseDao;
		setBaseDao(this.oaMeetingmaterialDao);
	}
	
	   /**
     * 会议资料：信息djid 长度默认14
     */
    @Override
    public void saveObject(OaMeetingmaterial o) {
        if (!StringUtils.hasText(o.getDjId())) {
            o.setDjId("HYZL"+oaMeetingmaterialDao.getNextKeyBySequence("S_MEETINGMATERIAL",12));
        }
        super.saveObject(o);
    }

    @Override
    public String getDjId() {
        return "HYZL"+oaMeetingmaterialDao.getNextKeyBySequence("S_MEETINGMATERIAL",12);
    }

    @Override
    public List<OaMeetingmaterial> listOaMeetingmaterial(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        return oaMeetingmaterialDao.listOaMeetingmaterial(filterMap, pageDesc);
    }

    @Override
    public List<OaMeetingmaterial> findListByDjId(String mId) {
        return oaMeetingmaterialDao.listObjects("from OaMeetingmaterial where  mId=?", mId);
    }
    
}

