package com.centit.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.oa.po.OaOptPowerDef;

public class OaOptPowerDefDao extends BaseDaoImpl<OaOptPowerDef>  {

    private static final long serialVersionUID = 1L;
	
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();
			filterField.put("OPTID" , CodeBook.EQUAL_HQL_ID);
			filterField.put("PREOPTID", CodeBook.EQUAL_HQL_ID);
			filterField.put("ISINTOOLBAR", CodeBook.EQUAL_HQL_ID);
			filterField.put("TOPOPTID", CodeBook.EQUAL_HQL_ID);
			filterField.put("OPTTYPE", CodeBook.EQUAL_HQL_ID);
			filterField.put("OPTNAME", CodeBook.LIKE_HQL_ID);			
		}
		return filterField;
	}	

	public String getNextOptCode()
	{
		return getNextKeyByHqlStrOfMax("optcode", "OaOptPowerDef",8);
	}
	/**
     * 按类别维护
     * 1.删除该类别编辑前数据
     * 2.新增新数据
     * @param optlevel
     * @return
     */
    public  void saveOptPower(List<OaOptPowerDef> oaOptPowerDefList) {
        
        if( oaOptPowerDefList.size() < 1) return;
        String optid = oaOptPowerDefList.get(0).getOptid();
        this.doExecuteHql( "DELETE FROM OaOptPowerDef rp where rp.optid = ?" , optid);
        
        for(OaOptPowerDef p:oaOptPowerDefList ){
            getHibernateTemplate().saveOrUpdate(p);
        }
        
    }
    
    @SuppressWarnings("unchecked")
    public List<OaOptPowerDef> getOptDef() {
        return getHibernateTemplate().find("FROM OaOptPowerDef ");
    }
    /**
     * 按类别获取信息
     * @param optlevel
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<OaOptPowerDef> getOptDefByOptId(String optid) {
        return getHibernateTemplate()
            .find("FROM OaOptPowerDef WHERE optid =?",optid);
    }
    /**
     * 按类别维护
     * @param optlevel
     * @return
     */
    public void deleteDocViewPower(String optid) {
        this.doExecuteHql( "DELETE FROM OaOptPowerDef rp where rp.optid = ?" , optid);
    }
}
