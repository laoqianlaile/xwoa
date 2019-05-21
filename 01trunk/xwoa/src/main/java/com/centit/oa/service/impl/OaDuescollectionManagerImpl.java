package com.centit.oa.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.dao.OaDuescollectionDao;
import com.centit.oa.dao.OaDuescollectioninfosDao;
import com.centit.oa.po.OaDuescollection;
import com.centit.oa.service.OaDuescollectionManager;
import com.centit.sys.po.FUserinfo;

public class OaDuescollectionManagerImpl extends BaseEntityManagerImpl<OaDuescollection>
	implements OaDuescollectionManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaDuescollectionManager.class);

	private OaDuescollectionDao oaDuescollectionDao ;
	private OaDuescollectioninfosDao oaDuescollectioninfosDao;
	public void setOaDuescollectionDao(OaDuescollectionDao baseDao)
	{
		this.oaDuescollectionDao = baseDao;
		setBaseDao(this.oaDuescollectionDao);
	}
 /*   *//**
   * 党费收缴：信息djid 长度默认14
   *//*
  @Override
  public void saveObject(OaDuescollection o) {
      if (!StringUtils.hasText(o.getDjId())) {
          o.setDjId("DFSJ"+oaDuescollectionDao.getNextKeyBySequence("S_OADUESCOLLECTION",12));
      }
      super.saveObject(o);
  }*/
    @Override
    public List<FUserinfo> getUserByCode(String usercode) {
        return oaDuescollectionDao.getUserByCode(usercode);
    }
    @Override
    public List<OaDuescollection> listOaDuescollection(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        return oaDuescollectionDao.listOaDuescollection(filterMap, pageDesc);
    }
    @Override
    public void deleteDuescollectiont(OaDuescollection bean) {
        // TODO Auto-generated method stub
        oaDuescollectioninfosDao.deleteDuescollectioninfosById(bean.getDjId());
        oaDuescollectionDao.delete(bean);
    }
    public void setOaDuescollectioninfosDao(
            OaDuescollectioninfosDao oaDuescollectioninfosDao) {
        this.oaDuescollectioninfosDao = oaDuescollectioninfosDao;
    }
    @Override
    public String getNextKey() {
       return  "DFSJ"+oaDuescollectionDao.getNextKeyBySequence("S_OADUESCOLLECTION",12);
    }
	
}

