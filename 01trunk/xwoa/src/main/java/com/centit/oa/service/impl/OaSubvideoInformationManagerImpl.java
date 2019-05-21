package com.centit.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaSubvideoInformation;
import com.centit.oa.dao.OaSubvideoInformationDao;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.service.OaSubvideoInformationManager;

public class OaSubvideoInformationManagerImpl extends BaseEntityManagerImpl<OaSubvideoInformation>
	implements OaSubvideoInformationManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaSubvideoInformationManager.class);

	private OaSubvideoInformationDao oaSubvideoInformationDao ;
	public void setOaSubvideoInformationDao(OaSubvideoInformationDao baseDao)
	{
		this.oaSubvideoInformationDao = baseDao;
		setBaseDao(this.oaSubvideoInformationDao);
	}
    @Override
    public String createid() {
        // TODO Auto-generated method stub
        return "SP"+oaSubvideoInformationDao.getNextKeyBySequence("S_SPID", 12);
    }
    @Override
    public void deleteByVideoNo(String videoNo) {
         if(StringUtils.isNotBlank(videoNo)){
             Map<String,Object> filterMap = new HashMap<String,Object>();
             filterMap.put("no", videoNo);
             List<OaSubvideoInformation> subVideoList = oaSubvideoInformationDao.listObjects(filterMap);
             //级联删除
             if(subVideoList != null && !subVideoList.isEmpty()){
                 for(OaSubvideoInformation subVideoInfo : subVideoList){
                     subVideoInfo.setState("D");
                 }
             }
         }
        
    }
    
}

