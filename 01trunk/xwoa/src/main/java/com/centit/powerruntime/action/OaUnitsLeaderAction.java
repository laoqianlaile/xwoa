package com.centit.powerruntime.action;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.action.OACommonBizAction;
import com.centit.powerruntime.po.OaLeaderunits;
import com.centit.powerruntime.po.OaLeaderunitsId;
import com.centit.powerruntime.po.OaUnitsLeader;
import com.centit.powerruntime.po.OaUnitsLeaderLog;
import com.centit.powerruntime.service.OaLeaderunitsManager;
import com.centit.powerruntime.service.OaUnitsLeaderLogManager;
import com.centit.powerruntime.service.OaUnitsLeaderManager;
import com.centit.sys.security.FUserDetail;
	

public class OaUnitsLeaderAction  extends OACommonBizAction<OaUnitsLeader>  {
	private static final Log log = LogFactory.getLog(OaUnitsLeaderAction.class);
	private static final long serialVersionUID = 1L;
	private OaUnitsLeaderManager oaUnitsLeaderMag;
	private OaUnitsLeaderLogManager oaUnitsLeaderLogManager;
	private OaLeaderunitsManager oaLeaderunitsManager;
	public void setOaUnitsLeaderManager(OaUnitsLeaderManager basemgr)
	{
		oaUnitsLeaderMag = basemgr;
		this.setBaseEntityManager(oaUnitsLeaderMag);
	}

	public void setOaUnitsLeaderLogManager(
            OaUnitsLeaderLogManager oaUnitsLeaderLogManager) {
        this.oaUnitsLeaderLogManager = oaUnitsLeaderLogManager;
    }
	public void setOaLeaderunitsManager(OaLeaderunitsManager oaLeaderunitsManager) {
        this.oaLeaderunitsManager = oaLeaderunitsManager;
    }

@Override
public String edit() {
    // TODO Auto-generated method stub
    super.edit();
    request.setAttribute("userjson",super.initUnitsUsersTree());
    return EDIT;
}
    @Override
	public String save() {
	    try{
	        
	        FUserDetail fuser = ((FUserDetail) getLoginUser());
	        //保存领导分管部门
	        OaUnitsLeader oaUnitsLeader=oaUnitsLeaderMag.getObjectById(object.getLeadercode());	  
	        if(oaUnitsLeader!=null){//更新
	            oaUnitsLeader.setLastmodifytime(new Date(System.currentTimeMillis()));
	            oaUnitsLeader.setUpdateuser(fuser.getUsercode());
            }else{//新增
                oaUnitsLeader=new OaUnitsLeader();
                oaUnitsLeader.setCreatetime(new Date(System.currentTimeMillis()));
                oaUnitsLeader.setCreateuser(fuser.getUsercode());
            }
	        oaUnitsLeaderMag.copyObjectNotNullProperty(oaUnitsLeader,object);
	        object=oaUnitsLeader;	        
	        oaUnitsLeaderMag.saveObject(object);
	        
	    //记录设置日志信息
	    OaUnitsLeaderLog oaUnitsLeaderLog=new OaUnitsLeaderLog();
	    oaUnitsLeaderLog.setNo(oaUnitsLeaderLogManager.getnextKey());
	    oaUnitsLeaderLog.setLeadercode(object.getLeadercode());
	    oaUnitsLeaderLog.setIsuse(object.getIsuse());
	    oaUnitsLeaderLog.setUnitcodes(object.getUnitcodes());
	    oaUnitsLeaderLog.setUnitNames(object.getUnitNames());
	    oaUnitsLeaderLog.setCreatetime(new Date(System.currentTimeMillis()));
	    oaUnitsLeaderLog.setCreateuser(fuser.getUsercode());
	    oaUnitsLeaderLog.setRemark(object.getRemark());
	    oaUnitsLeaderLog.setIsuse(object.getIsuse());	    
	    oaUnitsLeaderLogManager.saveObject(oaUnitsLeaderLog);
	    
	    //向部门人员关联表中保存记录
	    String [] O=object.getUnitcodes().split(",");
	    oaLeaderunitsManager.deleteObjects(object.getLeadercode());
	    for(String o:O){
	        OaLeaderunits oaLeaderunits=new OaLeaderunits();
	        oaLeaderunits.setCid(new OaLeaderunitsId(object.getLeadercode(),o));
	        oaLeaderunitsManager.saveObject(oaLeaderunits);
	    }
	    saveMessage("保存信息成功！");	    
	    }catch (Exception e) {
        e.printStackTrace();
        saveMessage("保存信息失败！");
        }
	    return this.list();
	}		
    
    
    @Override
    public String delete() {
        // TODO Auto-generated method stub
        //清空领导部门关联表
        oaLeaderunitsManager.deleteObjects(object.getLeadercode());
        //删除部门领导表
        return super.delete();
    }
}
