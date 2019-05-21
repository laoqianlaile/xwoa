package com.centit.powerbase.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.HQLUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.powerbase.po.PcpunishItem;
import com.centit.powerbase.dao.PcpunishItemDao;
public class PcpunishItemDao extends BaseDaoImpl<PcpunishItem>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(PcpunishItemDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("punishclassid" , CodeBook.EQUAL_HQL_ID);


			filterField.put("itemId" , CodeBook.LIKE_HQL_ID);

			filterField.put("version" , CodeBook.LIKE_HQL_ID);

			filterField.put("punishclassname" , CodeBook.LIKE_HQL_ID);

			filterField.put("depid" , CodeBook.LIKE_HQL_ID);

			filterField.put("punishclasscode" , CodeBook.LIKE_HQL_ID);

			filterField.put("punishobject" , CodeBook.LIKE_HQL_ID);

			filterField.put("isinuse" , CodeBook.LIKE_HQL_ID);

			filterField.put("punishbasiscontent" , CodeBook.LIKE_HQL_ID);

			filterField.put("punishbasis" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("islawsuit" , CodeBook.LIKE_HQL_ID);

			filterField.put("isreconsider" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 
	  /**
     * 处罚项目类别流水号PunishClassID
     * 
     * @return
     */

    public String genNextPunishClassID() {
        return getNextKeyBySequence("S_Pcpunish_Item", 8);
        
    }
    public PcpunishItem getObjectByItemId(String punishclasscode) {
        List<PcpunishItem> procs = this.listObjects("From PcpunishItem where punishclasscode =  "+HQLUtils.buildHqlStringForSQL(punishclasscode));
        if (procs == null || procs.size() < 1)
            return null;
        return procs.get(0);
    } 
}
