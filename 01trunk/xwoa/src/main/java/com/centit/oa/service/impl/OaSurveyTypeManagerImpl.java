package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaSurveyType;
import com.centit.oa.dao.OaSurveyTypeDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaSurveyTypeManager;

public class OaSurveyTypeManagerImpl extends BaseEntityManagerImpl<OaSurveyType>
	implements OaSurveyTypeManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaSurveyTypeManager.class);

	private OaSurveyTypeDao oaSurveyTypeDao ;
	public void setOaSurveyTypeDao(OaSurveyTypeDao baseDao)
	{
		this.oaSurveyTypeDao = baseDao;
		setBaseDao(this.oaSurveyTypeDao);
	}
	@Override
	public void saveSuryType(OaSurveyType o){
	    if(null==o.getNo())
	    o.setNo(oaSurveyTypeDao.getNextNo());
	    oaSurveyTypeDao.saveObject(o);
	}
    @Override
    public void deleteSuryType(OaSurveyType o) {
        // TODO Auto-generated method stub
        if(null!=o){
            o.setState("F");
            this.oaSurveyTypeDao.saveObject(o);
        }
    }
}

