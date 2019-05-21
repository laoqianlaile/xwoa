package com.centit.oa.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.dao.OaScheduleResponseDao;
import com.centit.oa.po.OaScheduleResponse;
import com.centit.oa.service.OaScheduleResponseManager;

public class OaScheduleResponseManagerImpl extends BaseEntityManagerImpl<OaScheduleResponse>
	implements OaScheduleResponseManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaScheduleResponseManager.class);

	private OaScheduleResponseDao oaScheduleResponseDao ;
	public void setOaScheduleResponseDao(OaScheduleResponseDao baseDao)
	{
		this.oaScheduleResponseDao = baseDao;
		setBaseDao(this.oaScheduleResponseDao);
	}
	
	  /**
     * 日程安排响应
     */
    @Override
    public void saveObject(OaScheduleResponse o) {
        if (!StringUtils.hasText(o.getNo())) {
            o.setNo("RCXY"
                    + oaScheduleResponseDao.getNextKeyBySequence("S_SCH_RES_NO", 12));
        }
        super.saveObject(o);
    }

    @Override
    public OaScheduleResponse getOwnRes(OaScheduleResponse object) {
        
        return oaScheduleResponseDao.getOwnRes(object);
    }

    @Override
    public List<OaScheduleResponse> getOaScheduleResponseListByresType(
            String schno, String resType) {
        // TODO Auto-generated method stub
        return oaScheduleResponseDao.getOaScheduleResponseListByresType(schno,resType);
    }
	
}

