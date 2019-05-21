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
    
    /**
     * 自动生成编号
     */
    @Override
    public String getNewCode() {
        return  incomeDocDao.getNextKeyBySequence("S_IncomeDoc_number", 1);
    }

    @Override
    public String getNewCodeByAJJ() {
        // XW-SW-0002
        return  incomeDocDao.getNextKeyBySequence("S_IncomeDoc_number02", 1);
    }

    @Override
    public String getNewCodeByAJ() {
        // XW-SW-0005
        return  incomeDocDao.getNextKeyBySequence("S_IncomeDoc_number05", 1);
    }
    
    @Override
    public String getNewCodeByHBJ() {
        //XW-SW-0003
        return  incomeDocDao.getNextKeyBySequence("S_IncomeDoc_number03", 1);
    }

    @Override
    public String getNewCodeByDQ() {
        // XW-SW-0004
        return  incomeDocDao.getNextKeyBySequence("S_IncomeDoc_number04", 1);
    }

    @Override
    public String getNewCodeByGHJSQ() {
        // XW-SW-0008
        return  incomeDocDao.getNextKeyBySequence("S_IncomeDoc_number08", 1);
    }

    @Override
    public String getNewCodeByTY() {
        // XW-SW-0013
        return  incomeDocDao.getNextKeyBySequence("S_IncomeDoc_number13", 1);
    }
    
    @Override
    public String getNewCodeByGZB() {
        // XW-SW-0014
        return  incomeDocDao.getNextKeyBySequence("S_IncomeDoc_number14", 1);
    }
    
    
}

