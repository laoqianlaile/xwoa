package com.centit.dispatchdoc.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.OaFwdepmange;
import com.centit.dispatchdoc.dao.OaFwdepmangeDao;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.dispatchdoc.service.OaFwdepmangeManager;
import com.centit.powerruntime.po.TemplateFile;
import com.centit.sys.po.FDatadictionary;

public class OaFwdepmangeManagerImpl extends BaseEntityManagerImpl<OaFwdepmange>
	implements OaFwdepmangeManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaFwdepmangeManager.class);

	private OaFwdepmangeDao oaFwdepmangeDao ;
	public void setOaFwdepmangeDao(OaFwdepmangeDao baseDao)
	{
		this.oaFwdepmangeDao = baseDao;
		setBaseDao(this.oaFwdepmangeDao);
	}
	
	/**
     * 依据部门信息获取文书情况
     * @param primaryUnit
     * @return
     */
    @Override
    public List<OaFwdepmange> getDocManageListByUnit(String primaryUnit) {
        // TODO Auto-generated method stub
        return oaFwdepmangeDao.getDocManageListByUnit( primaryUnit);
    }
    /**
     * 部门已配置的文书列表
     * @param pageDesc 
     * @param filterMap 
     * 
     */
    @Override
    public List<TemplateFile> getDocListByUnit(String primaryUnit) {
        // TODO Auto-generated method stub
        return oaFwdepmangeDao.getDocListByUnit( primaryUnit);
    }
    /**
     * 依据部门信息获取置文号规则情况
     * @param primaryUnit
     * @return
     */
    @Override
    public List<OaFwdepmange> getZWHManageListByUnit(String primaryUnit) {
        // TODO Auto-generated method stub
        return oaFwdepmangeDao.getZWHManageListByUnit( primaryUnit);
    }
    /**
     * 部门已配置的文号列表
     * @param pageDesc 
     * @param filterMap 
     * 
     */
    @Override
    public List<FDatadictionary> getZWHListByUnit(String primaryUnit) {
        // TODO Auto-generated method stub
        return oaFwdepmangeDao.getZWHListByUnit( primaryUnit);
    }
    /**
     * 依据部门信息获取配置情况
     * @param primaryUnit
     * @return
     */
    @Override
    public List<OaFwdepmange> getManageListByUnit(String primaryUnit) {
        // TODO Auto-generated method stub
        return oaFwdepmangeDao.getManageListByUnit( primaryUnit);
    }
    
    /**
     * 获取文书情况
     * @param primaryUnit
     * @return
     */
    @Override
    public List<OaFwdepmange> getAllDocManageList() {
        // TODO Auto-generated method stub
        return oaFwdepmangeDao.getAllDocManageList();
    }
    
    /**
     * 置文号规则情况
     * @param primaryUnit
     * @return
     */
    @Override
    public List<OaFwdepmange> getAllZWHManageList() {
        // TODO Auto-generated method stub
        return oaFwdepmangeDao.getAllZWHManageList();
    }
    
    /**待选
     * 新增文书列表
     * 
     */
    @Override
    public List<TemplateFile> selectDocList(String s_unitcode, Map<String, Object> filterMap, PageDesc pageDesc) {
        // TODO Auto-generated method stub
        return oaFwdepmangeDao.selectDocList(s_unitcode,filterMap,pageDesc);
    }
    
    /**待选
     * 新增文号规则列表
     * 
     */
    @Override
    public List<FDatadictionary> selectZWHList(String s_unitcode, Map<String, Object> filterMap, PageDesc pageDesc) {
        // TODO Auto-generated method stub
        return oaFwdepmangeDao.selectZWHList(s_unitcode,filterMap,pageDesc);
    }
    
    /**
     * 保存文书配置
     * @param s_unitcode
     * @param s_templates
     */
    @Override
    public void saveSelectDocList(String s_unitcode, String s_templates) {
        // TODO Auto-generated method stub
        if(StringUtils.isNotBlank(s_templates)){
            String ar[]=s_templates.split(",");
            for(String a :ar){
              List<OaFwdepmange>   list= oaFwdepmangeDao.getDocManageListByUnitAndTid(s_unitcode,a);
              if(null==list || list.size()==0){
                  OaFwdepmange oaFwdepmange=new OaFwdepmange();
                  oaFwdepmange.setUnitcode(s_unitcode);
                  oaFwdepmange.setTemplateids(a);
                  oaFwdepmange.setCreatertime(new Date());
                  oaFwdepmange.setNo(getNextKey());
                  oaFwdepmangeDao.saveObject(oaFwdepmange);
              }
            }
        }
    }
    /**
     * 保存文号配置
     * @param s_unitcode
     * @param s_templates
     */
    @Override
    public void saveSelectZWhList(String s_unitcode, String s_templates) {
        // TODO Auto-generated method stub
        if(StringUtils.isNotBlank(s_templates)){
            String ar[]=s_templates.split(",");
            for(String a :ar){
              List<OaFwdepmange>   list= oaFwdepmangeDao.getZWHManageListByUnitAndZWh(s_unitcode,a);
              if(null==list || list.size()==0){
                  OaFwdepmange oaFwdepmange=new OaFwdepmange();
                  oaFwdepmange.setUnitcode(s_unitcode);
                  oaFwdepmange.setDispatchfiletype(a);
                  oaFwdepmange.setCreatertime(new Date());
                  oaFwdepmange.setNo(getNextKey());
                  oaFwdepmangeDao.saveObject(oaFwdepmange);
              }
            }
        }
    }
    
    @Override
    public String getNextKey() {
        // TODO Auto-generated method stub
        return "DEPM"+oaFwdepmangeDao.getNextKeyBySequence("S_OAFWDEPMANGE", 11);
    }

   
    
  
}

