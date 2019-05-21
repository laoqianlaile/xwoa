package com.centit.oa.action;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.po.OaDuescollectioninfos;
import com.centit.oa.service.OaDuescollectioninfosManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.security.FUserDetail;
	

public class OaDuescollectioninfosAction  extends OACommonBizAction<OaDuescollectioninfos>  {
	private static final Log log = LogFactory.getLog(OaDuescollectioninfosAction.class);
	private static final long serialVersionUID = 1L;
	private OaDuescollectioninfosManager oaDuescollectioninfosMag;
	public void setOaDuescollectioninfosManager(OaDuescollectioninfosManager basemgr)
	{
		oaDuescollectioninfosMag = basemgr;
		this.setBaseEntityManager(oaDuescollectioninfosMag);
	}
    public String save() {
        try {
            FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
            if (StringUtils.isBlank(object.getDjId())) {// 录入人员,录入时间
                
                object.setCreatetime(DatetimeOpt.currentUtilDate());
            }

            return super.save();
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }		
}
