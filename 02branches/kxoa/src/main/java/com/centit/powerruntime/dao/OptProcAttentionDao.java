package com.centit.powerruntime.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.powerruntime.po.OptProcAttention;

public class OptProcAttentionDao extends BaseDaoImpl<OptProcAttention>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OptProcAttentionDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("usercode" , CodeBook.EQUAL_HQL_ID);


			filterField.put("attsettime" , CodeBook.LIKE_HQL_ID);

			filterField.put("attsetuser" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 
	
	public List<OptProcAttention> getAttentionsByDjId(String djId){
	    String baseHQL = "from OptProcAttention where cid.djId = " + HQLUtils.buildHqlStringForSQL(djId);
	    return this.listObjects(baseHQL);
	}	
	
    /**
     * 删除一个流程的所有关注
     */
    public void deleteProcAttention(String djId) {
        this.doExecuteHql("delete from OptProcAttention where cid.djId =?",djId);
    }
    /**
     * 获得一个流程的所有关注
     */
    public List<OptProcAttention> listAttentionByFlowInstId(String djId ,String optUser) {
        return this.listObjects( "From OptProcAttention where cid.djId =? and attsetuser=?",
                new Object[]{djId,optUser});
    }
    
    public void deleteFlowAttentionByOptUser(String djId ,String optUser)
    {
        super.doExecuteHql( "delete From OptProcAttention where cid.djId =? and attsetuser=?",
                new Object[]{djId,optUser});
    }
}
