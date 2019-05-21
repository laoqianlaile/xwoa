package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaVideoInformation;
import com.centit.oa.dao.OaVideoInformationDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.service.OaSubvideoInformationManager;
import com.centit.oa.service.OaVideoInformationManager;

public class OaVideoInformationManagerImpl extends BaseEntityManagerImpl<OaVideoInformation>
	implements OaVideoInformationManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaVideoInformationManager.class);

	private OaVideoInformationDao oaVideoInformationDao ;
	public void setOaVideoInformationDao(OaVideoInformationDao baseDao)
	{
		this.oaVideoInformationDao = baseDao;
		setBaseDao(this.oaVideoInformationDao);
	}
	
	private OaSubvideoInformationManager oaSubvideoInformationManager;
   
    
    public void setOaSubvideoInformationManager(
            OaSubvideoInformationManager oaSubvideoInformationManager) {
        this.oaSubvideoInformationManager = oaSubvideoInformationManager;
    }
    @Override
    public String createNo() {
        // TODO Auto-generated method stub
        return "SPJM"+oaVideoInformationDao.getNextKeyBySequence("S_VIDEO", 12);
    }
    /**
     * 定时器使用，使得过了视频有效期的视频的状态变成不可观看
     */
    @Override
    public void autoLoseEfficacy() {
        // TODO Auto-generated method stub
        oaVideoInformationDao.autoLoseEfficacy();
    }
	
    @Override
    public void deleteObject(OaVideoInformation o) {
        OaVideoInformation videoInfo = oaVideoInformationDao.getObjectById(o.getNo());
        if(videoInfo != null){
            videoInfo.setState("D");
            //级联删除关联的视频信息
            oaSubvideoInformationManager.deleteByVideoNo(o.getNo());
        }
    }
}

