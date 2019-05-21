package com.centit.oa.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Order;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.dao.EquipmentInfoDao;
import com.centit.oa.po.EquipmentInfo;
import com.centit.oa.service.EquipmentInfoManager;
import com.centit.sys.dao.DataCatalogDao;
import com.centit.sys.dao.DataDictionaryDao;
import com.centit.sys.po.FDatacatalog;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.po.FDatadictionaryId;

public class EquipmentInfoManagerImpl extends BaseEntityManagerImpl<EquipmentInfo>
	implements EquipmentInfoManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(EquipmentInfoManager.class);
	
	//private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

	private EquipmentInfoDao equipmentInfoDao ;
	private DataDictionaryDao dictionaryDao;
	 private DataCatalogDao catalogDao;
	public void setEquipmentInfoDao(EquipmentInfoDao baseDao)
	{
		this.equipmentInfoDao = baseDao;
		setBaseDao(this.equipmentInfoDao);
	}
	
	public void setCatalogDao(DataCatalogDao dao) {
        this.catalogDao = dao;
    }

    public void setDictionaryDao(DataDictionaryDao dao) {
        this.dictionaryDao = dao;
    }
    @Override
    //获取下一个固定资产使用申请号
    public Long genNextEquipmentId() {
            return equipmentInfoDao.getNextLongSequence("S_equipmentId");
    }
    @Override
    public void saveObject(EquipmentInfo o) {
        if (null == (o.getEquipmentId())) {
            o.setEquipmentId(equipmentInfoDao.getNextLongSequence("S_EQUIPMENTID"));
        }
        super.saveObject(o);
    }
    @Override
    public List<EquipmentInfo> listByType(EquipmentInfo object,
            List<String> types, PageDesc pageDesc, Order order) {
        // TODO Auto-generated method stub
        return equipmentInfoDao.listByType(object, types, pageDesc, order);
    }
    @Override
    public void delAndUpdateDic(FDatadictionaryId id) {
        // TODO Auto-generated method stub
        FDatacatalog datacatalog = catalogDao.getObjectById(id.getCatalogcode());
        FDatadictionary datadictionary=dictionaryDao.getObjectById(id);
        datacatalog.setIsupload("0");
        catalogDao.saveObject(datacatalog);
        dictionaryDao.deleteObjectById(id);//删除
        equipmentInfoDao.updatesByid(datadictionary);//批量更新
        equipmentInfoDao.updatesById(datadictionary);//批量更新
        
    }
}

