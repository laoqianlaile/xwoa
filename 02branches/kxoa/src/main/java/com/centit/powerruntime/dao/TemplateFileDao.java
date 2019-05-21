package com.centit.powerruntime.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.driver.OracleResultSet;
import oracle.sql.BLOB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.powerruntime.po.TemplateFile;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;

public class TemplateFileDao extends BaseDaoImpl<TemplateFile>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(TemplateFileDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("templateId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("recordId" , CodeBook.LIKE_HQL_ID);

			filterField.put("fileName" , CodeBook.LIKE_HQL_ID);

			filterField.put("fileType" , CodeBook.LIKE_HQL_ID);

			filterField.put("fileDate" , CodeBook.LIKE_HQL_ID);

			filterField.put("fileSize" , CodeBook.LIKE_HQL_ID);

			filterField.put("filePath" , CodeBook.LIKE_HQL_ID);

			filterField.put("descript" , CodeBook.LIKE_HQL_ID);

			filterField.put("tempType" , CodeBook.LIKE_HQL_ID);

			filterField.put("isUsed" , CodeBook.LIKE_HQL_ID);

			filterField.put("orderBy" , CodeBook.LIKE_HQL_ID);
			
			filterField.put(CodeBook.ORDER_BY_HQL_ID, " to_number(orderBy) asc");

		}
		return filterField;
	} 
	
	public List<TemplateFile> listTemplateByType(String tempType){
	    String baseHQL = "from TemplateFile where tempType  = "+ HQLUtils.buildHqlStringForSQL(tempType) +" order by orderBy";
	    return this.listObjects(baseHQL);
	}
	
	/**
	 * 根据模板编号获取模板信息
	 * @param recordId 模板编号
	 * @return
	 */
    public TemplateFile getTemplateByRecordId(String recordId) {
        String baseHQL = "from TemplateFile where recordId  = "
                + HQLUtils.buildHqlStringForSQL(recordId);
        List<TemplateFile> temps = this.listObjects(baseHQL);
        if (temps != null && temps.size()>0) {
            return temps.get(0);
        }
        return new TemplateFile();
    }

    @SuppressWarnings("unchecked")
    public List<TemplateFile> listTemplateByTypeNoWith(String tempType) {
      String sql="select recordId,descript from template_file where " +
      		"recordId not in (Select recordId from opt_writdef where isinuse='T')"+
              " and tempType="+HQLUtils.buildHqlStringForSQL(tempType);
      List<Object[]> list=(List<Object[]>) findObjectsBySql(sql);
      List<TemplateFile> reList=new ArrayList<TemplateFile>();
      if(list.size()>0){
          for(Object[] o:list){
              TemplateFile bean=new TemplateFile();
              bean.setRecordId((String)o[0]);
              bean.setDescript((String)o[1]);
              reList.add(bean);
          }
      }
        return reList;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Object[] findFileBody(final String tempId){
        final String sql = "SELECT FileBody,FileSize FROM Template_File WHERE RecordID= ?";
        Object[] obj = getHibernateTemplate().execute(new HibernateCallback(){
            @Override
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Connection con = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                Object[] res = new Object[2];
                try{
                     con = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();      
                     ps = con.prepareStatement(sql);  
                     ps.setString(1, tempId);
                     rs = ps.executeQuery();
                    
                    if (rs.next()) {
                       int mFileSize = rs.getInt("FileSize");
                      
                       Blob content = rs.getBlob("FileBody");
                       res[0] = content;
                       res[1] = mFileSize;
                    }
                }catch(SQLException e){
                    throw new SQLException(e.getMessage());
                }finally{
                    rs.close();      
                    ps.close();      
                    session.flush();      
                    session.close(); 
                }
                return res;
            }
        });
        return obj;
    }

}
