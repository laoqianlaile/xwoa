package com.centit.oa.action;

import java.util.Date;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.oa.po.OaAssetinformationBond;
import com.centit.oa.po.OaAssetinformationBondId;
import com.centit.oa.service.OaAssetinformationBondManager;
import com.centit.sys.security.FUserDetail;
	

public class OaAssetinformationBondAction  extends BaseEntityDwzAction<OaAssetinformationBond>  {
	private static final long serialVersionUID = 1L;
	private OaAssetinformationBondManager oaAssetinformationBondMag;
	private OaAssetinformationBond assetinformationBond;
	public void setOaAssetinformationBondManager(OaAssetinformationBondManager basemgr)
	{
		oaAssetinformationBondMag = basemgr;
		this.setBaseEntityManager(oaAssetinformationBondMag);
	}
	//保存事权关联信息
    public void  saveCombineSQ(){
        FUserDetail fuser = (FUserDetail) getLoginUser();
        String djid =(String)request.getParameter("djid");
        objList= oaAssetinformationBondMag.listOaAssetinformation(djid);
        //若关联过事权信息则删除信息后保存新关联
        if(objList!=null&&objList.size()>0){
            OaAssetinformationBondId cid = new OaAssetinformationBondId(objList.get(0).getDjid(),objList.get(0).getNo());
            oaAssetinformationBondMag.deleteObjectById(cid);
        }
        object.setCreater(fuser.getUsercode());
        object.setCreatetime(new Date(System.currentTimeMillis()));
        oaAssetinformationBondMag.saveObject(object);
    }
    public OaAssetinformationBond getAssetinformationBond() {
        return assetinformationBond;
    }
    public void setAssetinformationBond(OaAssetinformationBond assetinformationBond) {
        this.assetinformationBond = assetinformationBond;
    }
    
}
