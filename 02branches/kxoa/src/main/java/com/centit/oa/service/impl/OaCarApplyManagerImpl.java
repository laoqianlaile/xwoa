package com.centit.oa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaCarApply;
import com.centit.oa.dao.OaCarApplyDao;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.service.OaCarApplyManager;

public class OaCarApplyManagerImpl extends BaseEntityManagerImpl<OaCarApply>
	implements OaCarApplyManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaCarApplyManager.class);
	
	//private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

	private OaCarApplyDao oaCarApplyDao ;
	public void setOaCarApplyDao(OaCarApplyDao baseDao)
	{
		this.oaCarApplyDao = baseDao;
		setBaseDao(this.oaCarApplyDao);
	}
    @Override
    public String getNextKey() {
        // TODO Auto-generated method stub
        return "CARSQ"+oaCarApplyDao.getNextKeyBySequence("s_OaCarApply", 11);
    }
    /**
     * 会议室申请列表 states为希望获得的状态，eg"1,2"
     */
    @Override
    public List<OaCarApply> getApplylist(String djId, String cardjid,
            Date beg, Date end,String states) {
        List<OaCarApply>  applylist=new ArrayList<OaCarApply>();
        if (StringUtils.isNotBlank(states)) {
            String[] state = states.split(",");
            
            for (String s : state) {
                List<OaCarApply>  templist=oaCarApplyDao.getApplylist( djId,  cardjid,
                        beg,  end,  s);
                applylist.addAll(templist);
                }
            }
        return applylist;
    }
    
}

