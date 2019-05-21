package com.centit.oa.service.impl;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaHelpinfo;
import com.centit.oa.dao.OaHelpinfoDao;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaHelpinfoManager;

public class OaHelpinfoManagerImpl extends BaseEntityManagerImpl<OaHelpinfo>
	implements OaHelpinfoManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaHelpinfoManager.class);

	private OaHelpinfoDao oaHelpinfoDao ;
	public void setOaHelpinfoDao(OaHelpinfoDao baseDao)
	{
		this.oaHelpinfoDao = baseDao;
		setBaseDao(this.oaHelpinfoDao);
	}
    @Override
    public String getNextkey() {
        // TODO Auto-generated method stub
            return "HELP"+oaHelpinfoDao.getNextKeyBySequence("S_OAHELPINFO",12);
    }
    @Override
    public List<OaHelpinfo> search(String search,PageDesc pageDesc) {
        // TODO Auto-generated method stub
        if(StringUtils.isNotBlank(search)){
            return oaHelpinfoDao.search(search,pageDesc);
        }else{
            return null;
        }
    }
    @Override
    public void updateAfterReply(String djid) {
        // TODO Auto-generated method stub
        if(StringUtils.isNotBlank(djid)){
            OaHelpinfo oaHelpinfo=this.getObjectById(djid);
            if(null!=oaHelpinfo){
                oaHelpinfo.setReplynum(oaHelpinfo.getReplynum()+1);
                this.oaHelpinfoDao.saveObject(oaHelpinfo);
            }
        }
    }
    @Override
    public List<OaHelpinfo> listOahelpinfo(Map<String,Object>  filterMap,PageDesc pageDesc) {
        // TODO Auto-generated method stub
        return this.oaHelpinfoDao.listOahelpinfo(filterMap, pageDesc);
    }
}

