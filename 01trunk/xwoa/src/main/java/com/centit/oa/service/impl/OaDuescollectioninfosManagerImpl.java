package com.centit.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.dao.OaDuescollectioninfosDao;
import com.centit.oa.po.OaDuescollection;
import com.centit.oa.po.OaDuescollectioninfos;
import com.centit.oa.po.OaDuescollectioninfosId;
import com.centit.oa.service.OaDuescollectioninfosManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.dao.UnitInfoDao;
import com.centit.sys.po.FUserunit;

public class OaDuescollectioninfosManagerImpl extends BaseEntityManagerImpl<OaDuescollectioninfos>
	implements OaDuescollectioninfosManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaDuescollectioninfosManager.class);

	private OaDuescollectioninfosDao oaDuescollectioninfosDao ;
	private UnitInfoDao sysunitdao;
	
	public void setOaDuescollectioninfosDao(OaDuescollectioninfosDao baseDao)
	{
		this.oaDuescollectioninfosDao = baseDao;
		setBaseDao(this.oaDuescollectioninfosDao);
	}
    @Override
    public void initalTempState(String djId) {
        oaDuescollectioninfosDao.initalTempState(djId);
    }
 
    public void setSysunitdao(UnitInfoDao sysunitdao) {
        this.sysunitdao = sysunitdao;
    }
    @Override
    public void updateInfos(OaDuescollection object) {
        String usercodes = object.getSendpersens();
        if (StringUtils.isNotEmpty(usercodes)) {
           
            for (String usecode : usercodes.split(",")) {                    
                if(!StringUtils.isBlank(usecode)){
                 // 设置用户单位
              FUserunit userUnit = (sysunitdao.getUserPrimaryUnit(usecode));
              OaDuescollectioninfos bean = oaDuescollectioninfosDao.getObjectById(new OaDuescollectioninfosId(object.getDjId(),usecode));
                  //初始化时候，给出初始值
              if (bean==null) {
                  bean=new OaDuescollectioninfos(new OaDuescollectioninfosId(object.getDjId(),usecode));
                      if (userUnit!=null&&!StringUtils.isEmpty(userUnit.getUnitcode())) {
                          bean.setUnitcode(userUnit.getUnitcode());// 部门
                          bean.setIsfinish("F");
                      }   
                      bean.setCreatetime(DatetimeOpt.currentUtilDate());
                  }        
                bean.setTempstate("T");
                oaDuescollectioninfosDao.saveObject(bean);
                }
            }
          
    }
        
        this.deleteObjectProperties(object);
    }
    @Override
    public void deleteObjectProperties(OaDuescollection object) {
        // TODO Auto-generated method stub
        String usercodes = object.getSendpersens();
        Map<String, Object> filterMap=new HashMap<String, Object>();
        filterMap.put("djId", object.getDjId());
        List<OaDuescollectioninfos> list=oaDuescollectioninfosDao.listObjects(filterMap);
        
        if(list!=null&&list.size()>0){
            for(OaDuescollectioninfos bean:list){
                boolean flag=false;//标志位
                for(String usercode:usercodes.split(",")){
                if(StringUtils.isNotBlank(usercode)&&usercode.equals(bean.getUsercode())){
                    flag=true;
                    break;
                }
                }
                if(!flag){//如果在父表记录中找不到，说明 此条记录是应该被删除的
                    oaDuescollectioninfosDao.delete(bean);
                }
            }
        }
    }
    @Override
    public List<Object[]> listUnitsDistinct(String djId) {
        // TODO Auto-generated method stub
        return oaDuescollectioninfosDao.listUnitsDistinct(djId);
    }
}

