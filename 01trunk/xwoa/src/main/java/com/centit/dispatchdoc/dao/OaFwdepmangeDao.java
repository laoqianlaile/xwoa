package com.centit.dispatchdoc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.OaFwdepmange;
import com.centit.powerruntime.po.TemplateFile;
import com.centit.sys.po.FDatadictionary;

public class OaFwdepmangeDao extends BaseDaoImpl<OaFwdepmange>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaFwdepmangeDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("no" , CodeBook.EQUAL_HQL_ID);


			filterField.put("dispatchfiletype" , CodeBook.LIKE_HQL_ID);

			filterField.put("templateids" , CodeBook.LIKE_HQL_ID);

//			filterField.put("unitcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.LIKE_HQL_ID);

			filterField.put("creatertime" , CodeBook.LIKE_HQL_ID);
			
			 filterField.put("fileName" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	}
	/**
     * 依据部门信息获取指定文书情况
     * @param primaryUnit
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<OaFwdepmange> getDocManageListByUnitAndTid(String primaryUnit,String templateids) {
        String  sql=("FROM OaFwdepmange  where unitcode=? and  templateids  = ? ");
        List<OaFwdepmange> ls = getHibernateTemplate().find( sql.toString(),  new Object[]{primaryUnit,templateids});
        return ls;
    }
	/**
     * 依据部门信息获取文书情况
     * @param primaryUnit
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<OaFwdepmange> getDocManageListByUnit(String primaryUnit) {
        String  sql=("FROM OaFwdepmange  where unitcode=? and  templateids  is not  null ");
        List<OaFwdepmange> ls = getHibernateTemplate().find( sql.toString(),  new Object[]{primaryUnit});
        
        if(null != ls){
            for(OaFwdepmange o :ls){
                if(StringUtils.isNotBlank(o.getTemplateids())){
                    TemplateFile t=getTemplateFile(o.getTemplateids());
                 if(null!=t){
                     o.setDescript(t.getDescript());
                 }
             }
            }
        }
         
        return ls;
    }
    /**
     * 部门已配置的文书列表
     * @param pageDesc 
     * @param filterMap 
     * 
     */
    public List<TemplateFile> getDocListByUnit(String primaryUnit) {
        String sqlex=" select templateids from  OaFwdepmange where unitcode="+ HQLUtils.buildHqlStringForSQL(primaryUnit)+ " and  templateids  is not  null ";
        String sql = " From TemplateFile t where t.recordId  in ("+sqlex+")  ORDER BY  orderBy ";
        List ls = super.listObjects( sql.toString());
        return ls;
    }
    /**
     * 依据部门信息获取指定置文号规则情况
     * @param primaryUnit
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<OaFwdepmange> getZWHManageListByUnitAndZWh(String primaryUnit,String dispatchfiletype) {
        String  sql=("FROM OaFwdepmange  where unitcode=? and  dispatchfiletype  = ? ");
        List<OaFwdepmange> ls = getHibernateTemplate().find( sql.toString(),  new Object[]{primaryUnit,dispatchfiletype });
        return ls;
    }
    /**
     * 依据部门信息获取置文号规则情况
     * @param primaryUnit
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<OaFwdepmange> getZWHManageListByUnit(String primaryUnit) {
        String  sql=("FROM OaFwdepmange  where unitcode=? and  dispatchfiletype  is not  null ");
        List<OaFwdepmange> ls = getHibernateTemplate().find( sql.toString(),  new Object[]{primaryUnit});
        return ls;
    }
    /**
     * 部门已配置的文号列表
     * @param pageDesc 
     * @param filterMap 
     * 
     */
    @SuppressWarnings("unchecked")
    public List<FDatadictionary> getZWHListByUnit(String primaryUnit) {
        String sqlex=" select dispatchfiletype from  OaFwdepmange where unitcode="+ HQLUtils.buildHqlStringForSQL(primaryUnit)+ " and  dispatchfiletype  is not  null ";
        String sql = " From FDatadictionary t where t.id.catalogcode='WJLX'   and  t.id.datacode  in ("+sqlex+")   ORDER BY extracode2 ";
        List ls = super.listObjects( sql.toString());
        return ls;
    }
   
    /**
     * 依据部门信息获取配置情况
     * @param primaryUnit
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<OaFwdepmange> getManageListByUnit(String primaryUnit) {
        String  sql=("FROM OaFwdepmange  where unitcode=? ");
        List<OaFwdepmange> ls = getHibernateTemplate().find( sql.toString(),  new Object[]{primaryUnit});
        return ls;
    }
    
    
    /**
     * 获取文书情况
     * @param primaryUnit
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<OaFwdepmange> getAllDocManageList() {
        String  sql=("FROM OaFwdepmange  where  templateids  is not  null ORDER BY unitcode desc");
        List<OaFwdepmange> ls = getHibernateTemplate().find( sql.toString());
        
       if(null != ls){
           for(OaFwdepmange o :ls){
               if(StringUtils.isNotBlank(o.getTemplateids())){
                   TemplateFile t=getTemplateFile(o.getTemplateids());
                if(null!=t){
                    o.setDescript(t.getDescript());
                }
            }
           }
       }
        
        return ls;
    }
  
    
    /**
     * 查询文书描述字段
     * @param templateids
     * @return
     */
    @SuppressWarnings("rawtypes")
    public TemplateFile getTemplateFile(String  templateids) {
       
        String sqld = " From TemplateFile t where t.recordId = ?  " ;
        List lsd = super.listObjects(sqld,   new Object[]{templateids} );
        if(null != lsd && lsd.size()>0){
            return (TemplateFile) lsd.get(0);     
        }
        
      return  null;
    }
    
    /**
     * 置文号规则情况
     * @param primaryUnit
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<OaFwdepmange> getAllZWHManageList() {
        String  sql=("FROM OaFwdepmange  where  dispatchfiletype  is not  null ORDER BY unitcode desc ");
        List<OaFwdepmange> ls = getHibernateTemplate().find( sql.toString());
        return ls;
    }
    
    /**
     * 待选
     * 新增文书列表
     * @param pageDesc 
     * @param filterMap 
     * 
     */
    @SuppressWarnings("unchecked")
    public List<TemplateFile> selectDocList(String s_unitcode, Map<String, Object> filterMap, PageDesc pageDesc) {
        String sqlex=" select templateids from  OaFwdepmange where unitcode="+ HQLUtils.buildHqlStringForSQL(s_unitcode)+ " and  templateids  is not  null ";
        String sql = " From TemplateFile t where t.recordId not in ("+sqlex+")    ORDER BY  orderBy ";
        List ls = super.listObjects( sql.toString(), filterMap, pageDesc);
        return ls;
    }
    /**
     * 待选WJLX
     * 新增文号列表
     * @param pageDesc 
     * @param filterMap 
     * 
     */
    public List<FDatadictionary> selectZWHList(String s_unitcode,
            Map<String, Object> filterMap, PageDesc pageDesc) {
        String sqlex=" select dispatchfiletype from  OaFwdepmange where unitcode="+ HQLUtils.buildHqlStringForSQL(s_unitcode)+ " and  dispatchfiletype  is not  null ";
        String sql = " From FDatadictionary t where t.id.catalogcode='WJLX'   and  t.id.datacode not in ("+sqlex+")  ORDER BY extracode2  ";
        List ls = super.listObjects( sql.toString(), filterMap, pageDesc);
        return ls;
    }
   
  
}
