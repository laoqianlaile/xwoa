package com.centit.powerruntime.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptStuffInfo;
import com.centit.powerruntime.service.OptStuffInfoManager;
	

public class OptStuffInfoAction  extends BaseEntityExtremeAction<OptStuffInfo>  {
	private static final Log log = LogFactory.getLog(OptStuffInfoAction.class);
	private static final long serialVersionUID = 1L;
	private OptStuffInfoManager optStuffInfoMag;
	public void setOptStuffInfoManager(OptStuffInfoManager basemgr)
	{
		optStuffInfoMag = basemgr;
		this.setBaseEntityManager(optStuffInfoMag);
	}

	
	/**
     * 用于附件管理的删除使用
     * 
     * @return
     */
    private String resetUsers;
    
    public void deleteObjectBanInfo(String djId) {
        optStuffInfoMag.deleteObjectBanInfo(djId);
    }

    public String deleteCase() {
        if (StringUtils.isNotBlank(resetUsers)) {
            String ar[] = resetUsers.split(",");
            for (String a : ar) {
                deleteObjectBanInfo(a);
            }
        }
        return this.list();
    }

    public OptStuffInfoManager getOptStuffInfoMag() {
        return optStuffInfoMag;
    }

    public void setOptStuffInfoMag(OptStuffInfoManager optStuffInfoMag) {
        this.optStuffInfoMag = optStuffInfoMag;
    }

    public String getResetUsers() {
        return resetUsers;
    }

    public void setResetUsers(String resetUsers) {
        this.resetUsers = resetUsers;
    }

    /**
     * 删除功能
     * @return 
     */
    @Override
    public String  delete(){
        super.delete();
        return list();
    }
}
