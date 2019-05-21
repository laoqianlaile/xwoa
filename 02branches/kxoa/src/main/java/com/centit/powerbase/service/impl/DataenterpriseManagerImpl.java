package com.centit.powerbase.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerbase.po.Dataenterprise;
import com.centit.powerbase.dao.DataenterpriseDao;
import com.centit.powerbase.service.DataenterpriseManager;

public class DataenterpriseManagerImpl extends BaseEntityManagerImpl<Dataenterprise>
	implements DataenterpriseManager{

	private DataenterpriseDao dataenterpriseDao ;
	public void setDataenterpriseDao(DataenterpriseDao baseDao)
	{
		this.dataenterpriseDao = baseDao;
		setBaseDao(this.dataenterpriseDao);
	}
	
	 /**
     * 获取新增组织机构编号
     * @return
     */
    public Long genNextID(){
        return this.dataenterpriseDao.genNextID();
    }
    /**
     * 修改组织机构是否使用状态
     */
    public void disableObject(Dataenterprise object){
        this.dataenterpriseDao.disableObject(object);
    }
}

