package com.centit.powerruntime.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.HQLUtils;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerruntime.dao.GeneralModuleParamDao;
import com.centit.powerruntime.po.GeneralModuleParam;
import com.centit.powerruntime.po.VOptShowIdeaInfo;
import com.centit.powerruntime.service.GeneralModuleParamManager;

public class GeneralModuleParamManagerImpl extends BaseEntityManagerImpl<GeneralModuleParam>
	implements GeneralModuleParamManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(GeneralModuleParamManager.class);

	private GeneralModuleParamDao generalModuleParamDao ;
	public void setGeneralModuleParamDao(GeneralModuleParamDao baseDao)
	{
		this.generalModuleParamDao = baseDao;
		setBaseDao(this.generalModuleParamDao);
	}
   
	/**
	 * 获取流程配置的通用模块列表
	 * @param flowCode
	 * 获取modulecode正则表达式replace(regexp_substr(w.optparam,'(^moduleCode=[^&]*)|(&&moduleCode=[^&]*)') ,'moduleCode=','')
	 * @return
	 */
	public List<GeneralModuleParam> getGeneralModuleList(String flowCode,Long version){
	  String sql=" From GeneralModuleParam  t where t.moduleCode in("
	          + "  select   replace(regexp_substr(w.optParam,'(^moduleCode=[^&]*)|(&&moduleCode=[^&]*)') ,'moduleCode=','')   from WfNode w   "
	          + "  where  "
	          + " w.flowCode= " + HQLUtils.buildHqlStringForSQL(flowCode)
	          + " and w.version= " + version
	          + " )  order by nodeOrder ";
	  
	         
	 List<GeneralModuleParam> res =generalModuleParamDao.listObjects(sql);
	         
	   return          res;   
	}
}
