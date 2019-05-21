package com.centit.powerbase.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerbase.dao.DataindividualDao;
import com.centit.powerbase.po.Dataindividual;
import com.centit.powerbase.service.DataindividualManager;

public class DataindividualManagerImpl extends BaseEntityManagerImpl<Dataindividual>
	implements DataindividualManager{

	private DataindividualDao dataindividualDao ;
	public void setDataindividualDao(DataindividualDao baseDao)
	{
		this.dataindividualDao = baseDao;
		setBaseDao(this.dataindividualDao);
	}
	
	/**
     * 获取新增人员编号
     * @return
     */
    public Long genNextID(){
        return this.dataindividualDao.genNextID();
    }
    /**
     * 修改人员是否使用状态
     */
    public void disableObject(Dataindividual object){
        this.dataindividualDao.disableObject(object);
    }
	
}

