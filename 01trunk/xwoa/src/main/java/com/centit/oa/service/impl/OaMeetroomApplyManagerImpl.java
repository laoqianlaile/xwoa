package com.centit.oa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaMeetroomApply;
import com.centit.oa.dao.OaMeetroomApplyDao;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaMeetroomApplyManager;

public class OaMeetroomApplyManagerImpl extends BaseEntityManagerImpl<OaMeetroomApply>
	implements OaMeetroomApplyManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaMeetroomApplyManager.class);

	private OaMeetroomApplyDao oaMeetroomApplyDao ;
	public void setOaMeetroomApplyDao(OaMeetroomApplyDao baseDao)
	{
		this.oaMeetroomApplyDao = baseDao;
		setBaseDao(this.oaMeetroomApplyDao);
	}
    @Override
    public String getNextKey() {
        // TODO Auto-generated method stub
        return "HYSSQ"+oaMeetroomApplyDao.getNextKeyBySequence("s_OaMeetroomApply", 11);
    }
    /**
     * 会议室申请列表 states为希望获得的状态，eg"1,2"
     */
    @Override
    public List<OaMeetroomApply> getApplylist(String djId, String meetingNo,
            Date beg, Date end,String states) {
        List<OaMeetroomApply>  applylist=new ArrayList<OaMeetroomApply>();
        if (StringUtils.isNotBlank(states)) {
            String[] state = states.split(",");
            
            for (String s : state) {
                List<OaMeetroomApply>  templist=oaMeetroomApplyDao.getApplylist( djId,  meetingNo,
                        beg,  end,  s);
                applylist.addAll(templist);
                }
            }
        return applylist;
    }
}

