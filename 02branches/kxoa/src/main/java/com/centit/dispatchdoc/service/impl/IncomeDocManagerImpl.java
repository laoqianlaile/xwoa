package com.centit.dispatchdoc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.dispatchdoc.dao.IncomeDocDao;
import com.centit.dispatchdoc.po.IncomeDoc;
import com.centit.dispatchdoc.service.IncomeDocManager;
import com.centit.powerruntime.dao.VOrgSupPowerDao;
import com.centit.powerruntime.po.VOrgSupPower;

public class IncomeDocManagerImpl extends BaseEntityManagerImpl<IncomeDoc>
	implements IncomeDocManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(IncomeDocManager.class);

	private IncomeDocDao incomeDocDao ;
	private VOrgSupPowerDao vOrgSupPowerDao;
	public void setIncomeDocDao(IncomeDocDao baseDao)
	{
		this.incomeDocDao = baseDao;
		setBaseDao(this.incomeDocDao);
	}
	
    public void setvOrgSupPowerDao(VOrgSupPowerDao vOrgSupPowerDao) {
        this.vOrgSupPowerDao = vOrgSupPowerDao;
    }

    public String getNextkey(String zt) {
        return zt + incomeDocDao.getNextKeyBySequence("S_INCOME_DOC", 14);
    }
    
    public IncomeDoc getIncomeDoc(String internalNo, String itemId){
        return incomeDocDao.getIncomeDoc(internalNo, itemId);
    }
    
    public VOrgSupPower getPowerByPowerid(String powerid) {
        VOrgSupPower power = new VOrgSupPower();
        power.setItemId(powerid);
        return vOrgSupPowerDao.getObject(power);
    }
    
    public String getJSONDocumentNames(String djId) {
        return "";
    }
    
    /**
     * 获取系统内的发文机关名称
     * @return
     */
    public List<String> getIncomeDeptNames(){
        return incomeDocDao.getIncomeDeptNames();
    }
    
    /**
     * 获取系统内的来文单位分类
     * @return
     */
    public List<String> getIncomeAcceptNos(){
        return incomeDocDao.getIncomeAcceptNos();
    }
}

