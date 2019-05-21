package com.centit.powerruntime.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.powerruntime.po.OptStuffInfo;
import com.centit.powerruntime.po.VOptStuffInfo;

public class VOptStuffInfoDao extends BaseDaoImpl<VOptStuffInfo>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(VOptStuffInfo.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("stuffid" , CodeBook.EQUAL_HQL_ID);


			filterField.put("djId" , CodeBook.LIKE_HQL_ID);

			filterField.put("stuffname" , CodeBook.LIKE_HQL_ID);
	

			filterField.put("iszhi" , CodeBook.LIKE_HQL_ID);

			filterField.put("filename" , CodeBook.LIKE_HQL_ID);

			filterField.put("nodeinstid" , CodeBook.LIKE_HQL_ID);

			filterField.put("uploadtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("uploadusercode" , CodeBook.LIKE_HQL_ID);

			filterField.put("nodename" , CodeBook.LIKE_HQL_ID);

			filterField.put("filetype" , CodeBook.LIKE_HQL_ID);

			filterField.put("archivetype" , CodeBook.LIKE_HQL_ID);
			
			filterField.put(CodeBook.ORDER_BY_HQL_ID , " uploadtime desc ");

		}
		return filterField;
	} 
	
	public void deleteStuffByiszhi(String sortId){
	    String hql= "From OptStuffInfo where sortId = ? and iszhi = '1' ";
	   @SuppressWarnings("unchecked")
    List<OptStuffInfo> o= (List<OptStuffInfo>) getHibernateTemplate().find(hql, sortId);
	    delete(o.get(0));
	}
	
    public VOptStuffInfo getStuffInfoByFileType(String djId, String fileType) {
        String hql = "from VOptStuffInfo where djId = " + HQLUtils.buildHqlStringForSQL(djId);
        if(StringUtils.isNotBlank(fileType)){
            hql += " and filetype =" + HQLUtils.buildHqlStringForSQL(fileType);
        }
        @SuppressWarnings("unchecked")
        List<VOptStuffInfo> sqclList = (List<VOptStuffInfo>) this
                .findObjectsByHql(hql);
        if (sqclList != null && sqclList.size() > 0) {
            return sqclList.get(0);
        }
        return null;
    }
	 
    @SuppressWarnings("unchecked")
    public List<VOptStuffInfo> getStuffInfoListByFileType(String djId, String fileType) {
        String hql = "from VOptStuffInfo where djId = " + HQLUtils.buildHqlStringForSQL(djId);
        if(StringUtils.isNotBlank(fileType)){
            hql += " and filetype =" + HQLUtils.buildHqlStringForSQL(fileType);
        }
        return (List<VOptStuffInfo>) this.findObjectsByHql(hql);
    }
    
    /**
     * 获取许可登记上传附件
     * @param djId
     * @param nodeinstid
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<VOptStuffInfo> getStuffInfoListByNodeinstid(String djId, String nodeinstid) {
        String hql = "from VOptStuffInfo where djId = " + HQLUtils.buildHqlStringForSQL(djId);
        if(StringUtils.isNotBlank(nodeinstid)){
            hql += " and nodeinstid =" + HQLUtils.buildHqlStringForSQL(nodeinstid);
        }
        return (List<VOptStuffInfo>) this.findObjectsByHql(hql);
    }
    
    public VOptStuffInfo getStuffInfoByArchiveType(String djId, String archiveType) {
        String hql = "from VOptStuffInfo where djId = '" + djId + "' and archivetype ='"
                + archiveType + "'";
        @SuppressWarnings("unchecked")
        List<VOptStuffInfo> sqclList = (List<VOptStuffInfo>) this.findObjectsByHql(hql);
        if (sqclList != null && sqclList.size() > 0) {
            return sqclList.get(0);
        }
        return null;
    }
    
    /**
     * 
     * @param djId
     * @param fileType
     * @param archiveType
     * @return
     */
    public VOptStuffInfo getStuffInfoByType(String djId,String fileType, String archiveType) {
        String hql = "from VOptStuffInfo where djId = " +  HQLUtils.buildHqlStringForSQL(djId) + " and archivetype ="
                +  HQLUtils.buildHqlStringForSQL(archiveType) + " and filetype = " + HQLUtils.buildHqlStringForSQL(fileType);
        @SuppressWarnings("unchecked")
        List<VOptStuffInfo> sqclList = (List<VOptStuffInfo>) this.findObjectsByHql(hql);
        if (sqclList != null && sqclList.size() > 0) {
            return sqclList.get(0);
        }
        return null;
    }
    
    /**
     * 查询某办件下办理步骤的附件
     * @param djid
     * @return
     */
    public List<VOptStuffInfo> listTransStuffs(String djid) {
        String hql = "from VOptStuffInfo where djId = ? and archivetype in ('02','03','04')";

        List<VOptStuffInfo> sqclList = this.listObjects(hql,djid);
        return sqclList;
    }
    
    public List<VOptStuffInfo> listZwclStuffs(String djid) {
        String hql = "from VOptStuffInfo where djId =?  and stuffname='正文附件' ";

        List<VOptStuffInfo> sqclList = this.listObjects(hql,djid);
        return sqclList;
    }
    
    /**
     * 根据登记编号、附件类型删除受理意见材料
     * 
     * @param djid
     *            登记编号
     * @param clid
     *            附件类型
     */
    public void deleteTransStuffs(String djid) {
        String sSql = "delete from OPT_STUFF_INFO t where t.archivetype in ('02','03','04') and  t.dj_id = '"
                + djid + "'";
        super.doExecuteSql(sSql);
    }
    
    /**
     * 根据文件类型和正文分类删除附件
     * @param djid
     * @param fileType
     * @param archiveType
     */
    public void deleteStuffsByType(String djid,String fileType, String archiveType) {
        String sSql = "delete from OPT_STUFF_INFO t where t.FILETYPE = " + fileType + " and t.dj_id = "
        +  HQLUtils.buildHqlStringForSQL(djid) + " and t.ARCHIVETYPE ="
        +  HQLUtils.buildHqlStringForSQL(archiveType);
        super.doExecuteSql(sSql);
    }

    /**
     * 根据文件类型删除附件信息
     * 
     * @param djid
     *            登记编号
     * @param clid
     *            附件类型
     */
    public void deleteStuffsByFileType(String djid,String fileType) {
        String sSql = "delete from OPT_STUFF_INFO t where T.FILETYPE = '" + fileType + "' and  t.dj_id = '"
                + djid + "'";
        super.doExecuteSql(sSql);
    }
    
    
    public void isInuse(String djid){
        String hql="update OptStuffInfo  t set t.isuse = '1' where t.djId= '" + djid + "' and t.nodeInstId= '0'";  
        super.doExecuteHql(hql);
        String sSql = "delete  OptStuffInfo t where t.nodeInstId= '0' and t.isuse is null ";
        super.doExecuteHql(sSql);
    }

    public VOptStuffInfo getObjectById_SortId(String djId, String sortId) {
        String hql = "from VOptStuffInfo where djId =? and sortId =? ";
        @SuppressWarnings("unchecked")
        List<VOptStuffInfo> sqclList = (List<VOptStuffInfo>) this.findObjectsByHql(hql,new String[]{djId,sortId});
        if (sqclList != null && sqclList.size() > 0) {
            return sqclList.get(0);
        }
        return null;
    }
    
    
    @SuppressWarnings("unchecked")
    public List<String> getStuffInfoList(String djId) {
        
        StringBuffer sql = new StringBuffer();
        sql.append(" select FILENAME from OPT_STUFF_INFO where 1=1 ");
        if (StringUtils.isNotBlank(djId)) {
            sql.append(" and DJ_ID = '" + djId + "' ");
        }
        sql.append(" order by UPLOADTIME DESC ");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        List<String> list = sqlQuery.list();
        return list;
    }
}
