package com.centit.app.dao;

import com.centit.app.po.Publicinfo;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublicinfoDao extends BaseDaoImpl<Publicinfo> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(PublicinfoDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("infocode", CodeBook.EQUAL_HQL_ID);
            
            filterField.put("parentinfocode", CodeBook.EQUAL_HQL_ID);

            filterField.put("filecode", CodeBook.EQUAL_HQL_ID);
            
//            filterField.put("filename", CodeBook.LIKE_HQL_ID);
            filterField.put("filename", "  (instr((lower(filename)),lower(?))>0) ");
            filterField.put("fileextension", CodeBook.LIKE_HQL_ID);

            filterField.put("ownercode", CodeBook.EQUAL_HQL_ID);

            filterField.put("readcount", CodeBook.LIKE_HQL_ID);

            filterField.put("downloadcount", CodeBook.LIKE_HQL_ID);

            filterField.put("md5", CodeBook.EQUAL_HQL_ID);

            filterField.put("uploadtime", CodeBook.LIKE_HQL_ID);

            filterField.put("modifytimes", CodeBook.LIKE_HQL_ID);

            filterField.put("status", CodeBook.EQUAL_HQL_ID);

            filterField.put("foldertype", CodeBook.EQUAL_HQL_ID);

            filterField.put("isfolder", CodeBook.EQUAL_HQL_ID);

            filterField.put("filedescription", CodeBook.LIKE_HQL_ID);
            
            filterField.put("unitcode", CodeBook.EQUAL_HQL_ID);
            
            //接口查询条件--时间戳
            filterField.put("currentdatetime", " modifytime < to_timestamp(?,'yyyy-MM-dd hh24:mi:ss.ff')");
        }
        return filterField;
    }
    
    /**
     * 列出机构的直接父级机构
     * 
     * @param unitcode
     * @return
     */
    @SuppressWarnings({ "unchecked", "deprecation" })
    public List<String> listUnitDirectParents(String unitcode) {
        String sql = getSession().getNamedQuery("LIST_UNIT_DIRECT_PARENTS").getQueryString();
        
        return ((List<String>)getSession().createSQLQuery(sql)
                .addScalar("UNITCODE", Hibernate.STRING)
                .setString("unitcode", unitcode)
                .list());
    }
    
    /**
     * 列出用户所在机构
     * 
     * @param unitcode
     * @return
     */
    @SuppressWarnings({ "unchecked", "deprecation" })
    public List<String> listUserUnits(String usercode) {
        String sql = getSession().getNamedQuery("LIST_USER_UNITCODE").getQueryString();
        
        return ((List<String>)getSession().createSQLQuery(sql)
                .addScalar("UNITCODE", Hibernate.STRING)
                .setString("usercode", usercode)
                .list());
    }
    
    /**
     * 列出文件路径
     * 
     * @param infocode
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, String>> listFolderParents(String infocode) {
        return (List<Map<String, String>>) getSession().getNamedQuery("LIST_PATH_PARENTS")
            .setString("infocode", infocode)
            .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
            .list();
    }
    
    /**
     * 列出指定公共文件夹目录下所有文件/文件夹
     * 
     * @param infocode
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Publicinfo> listAllPublicinfos(String infocode) {
        String sql = getSession().getNamedQuery("LIST_ALL_PUBLICFILES").getQueryString();
        
        return getSession().createSQLQuery(sql)
                .addEntity("p", Publicinfo.class)
                .setString("infocode", infocode)
                .list();
                
    }
    /**
     * 列出某个用户所有的文件
     * @param ownercode
     * @param infocode
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Publicinfo> listAllSubPublicinfos(String ownercode, String infocode) {
        String sql = getSession().getNamedQuery("LIST_ALL_SUB_PUBLICFILES").getQueryString();
        
        return getSession().createSQLQuery(sql)
                .addEntity("p", Publicinfo.class)
                .setString("ownercode", ownercode)
                .setString("parentinfocode", infocode)
                .list();
                
    }
    /**
     * 列出部门内部指定公共文件夹目录下所有文件/文件夹
     * @param infocode
     * @param unitcode
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Publicinfo> listUnitAllPublicinfos(String infocode, String unitcode) {
        String sql = getSession().getNamedQuery("LIST_UNIT_ALL_PUBLICFILES").getQueryString();
        
        return getSession().createSQLQuery(sql)
                .addEntity("p", Publicinfo.class)
                .setString("infocode", infocode)
                .setString("unitcode", unitcode)
                .list();
                
    }
    /**
     * 列出指定公共文件夹首页下用户可见文件
     * 
     * @param infocode
     * @param usercode
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Publicinfo> listRootPublicinfos(String infocode, String usercode) {
        String sql = getSession().getNamedQuery("LIST_ROOT_PUBLICFILES").getQueryString();
        
        return getSession().createSQLQuery(sql)
                .addEntity("p", Publicinfo.class)
                .setString("infocode", infocode)
                .setString("usercode", usercode)
                .list();
                
    }
    
    /**
     * 列出指定公共文件夹目录下所有文件夹
     * 
     * @param infocode
     * @param userUnitcode
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Publicinfo> listAllPublicinfosFolder(String infocode) {
        String sql = getSession().getNamedQuery("LIST_ALL_PUBLICFILES_FOLDER").getQueryString();
        
        return getSession().createSQLQuery(sql)
                .addEntity("p", Publicinfo.class)
                .setString("infocode", infocode)
                .list();
                
    }
    
    /**
     * 列出指定公共文件夹目录下所有文件夹
     * 
     * @param infocode
     * @param userUnitcode
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Publicinfo> listUnitPublicinfosFolder(String infocode, String unitcode) {
        String sql = getSession().getNamedQuery("LIST_UNIT_PUBLICFILES_FOLDER").getQueryString();
        
        return getSession().createSQLQuery(sql)
                .addEntity("p", Publicinfo.class)
                .setString("unitcode", unitcode)
                .setString("infocode", infocode)
                .list();
                
    }
    
    /**
     * 根据名称获取文件夹对象
     * 
     * @param name
     * @return
     */
    @SuppressWarnings("unchecked")
    public Publicinfo getPublicFolderByName(String name, String infocode) {
        String sql = getSession().getNamedQuery("GET_FOLDER_BY_NAME").getQueryString();
        
        List<Publicinfo> list = getSession().createSQLQuery(sql)
                .addEntity("p", Publicinfo.class)
                .setString("name", name)
                .setString("infocode", infocode)
                .list();
        
        if (list.size() == 0) {
            return null;
        }
        
        return list.get(0);
    }
    
    /**
     * 根据名称获取文件对象
     * 
     * @param name
     * @return
     */
    @SuppressWarnings("unchecked")
    public Publicinfo getPublicFileByName(String name, String ext, String infocode) {
        String sql = getSession().getNamedQuery("GET_FILE_BY_NAME").getQueryString();
        
        List<Publicinfo> list = getSession().createSQLQuery(sql)
                .addEntity("p", Publicinfo.class)
                .setString("name", name)
                .setString("ext", ext)
                .setString("infocode", infocode)
                .list();
        
        if (list.size() == 0) {
            return null;
        }
        
        return list.get(0);
    }
    
    /**
     * 根据名称获取文件对象
     * 
     * @param name
     * @return
     */
    @SuppressWarnings("unchecked")
    public Publicinfo getPublicFileByName(String name, String infocode) {
        String sql = getSession().getNamedQuery("GET_FILE_BY_NAME_WITHOUT_EXT").getQueryString();
        
        List<Publicinfo> list = getSession().createSQLQuery(sql)
                .addEntity("p", Publicinfo.class)
                .setString("name", name)
                .setString("infocode", infocode)
                .list();
        
        if (list.size() == 0) {
            return null;
        }
        
        return list.get(0);
    }
    
    /**
     * 批量列出公共信息文件
     * 
     * @param infocodes
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Publicinfo> listPublicinfos(List<String> codes) {
        String hql = getSession().getNamedQuery("LIST_PUBLICFILES_BY_IDS").getQueryString();
        
        return getHibernateTemplate().findByNamedParam(hql, "codes", codes);
    }
    
    /**
     * 查询单位公共文件夹的根目录
     * 
     * @param unitcode
     * @return
     */
    public Publicinfo getUnitRootDirectory(String unitcode) {
        String sql = getSession().getNamedQuery("GET_UNIT_ROOT_DIRECTORY").getQueryString();
        
        return (Publicinfo) getSession().createSQLQuery(sql)
                .addEntity("p", Publicinfo.class)
                .setString("unitcode", unitcode)
                .uniqueResult();
    }
    
    /**
     * 查询公共文件夹的根目录
     * 
     * @param unitcode
     * @return
     */
    public Publicinfo getUnitRootDirectory() {
        String sql = getSession().getNamedQuery("GET_ROOT_DIRECTORY").getQueryString();
        
        return (Publicinfo) getSession().createSQLQuery(sql)
                .addEntity("p", Publicinfo.class)
                .uniqueResult();
    }
    
    /**
     * 查询部门文档的根目录
     * 
     * @param unitcode
     * @return
     */
    public Publicinfo getUnitFileRootDirectory(String unitcode) {
        String sql = getSession().getNamedQuery("GET_UNITFILE_ROOT_DIRECTORY").getQueryString();
        
        return (Publicinfo) getSession().createSQLQuery(sql)
                .addEntity("p", Publicinfo.class)
                .setString("unitcode", unitcode)
                .uniqueResult();
    }
    /**
     * 查询部门文档的根目录
     * 
     * @param unitcode
     * @return
     */
    public Publicinfo getUnitShareFileRootDirectory(String unitcode) {
        String sql = getSession().getNamedQuery("GET_UNITSHAREFILE_ROOT_DIRECTORY").getQueryString();
        
        return (Publicinfo) getSession().createSQLQuery(sql)
                .addEntity("p", Publicinfo.class)
                .setString("unitcode", unitcode)
                .uniqueResult();
    }
    
    /**
     * 查询个人文件夹的根目录
     * 
     * @param unitcode
     * @return
     */
    public Publicinfo getPersonalRootDirectory(String usercode) {
        String sql = getSession().getNamedQuery("GET_PERSONAL_ROOT_DIRECTORY").getQueryString();
        
        return (Publicinfo) getSession().createSQLQuery(sql)
                .addEntity("p", Publicinfo.class)
                .setString("usercode", usercode)
                .uniqueResult();
    }

    
  
    @SuppressWarnings("deprecation")
    public int countFilecodeUsed(String filecode) {
        String sql = getSession().getNamedQuery("COUNT_FILECODE_USED").getQueryString();
        
        return (Integer) getSession().createSQLQuery(sql)
                .addScalar("num",Hibernate.INTEGER)
                .setString("filecode", filecode)
                .uniqueResult();
    }
    /**
     * 列出指定公共文件夹首页下用户可见文件
     * 
     * @param infocode
     * @param usercode
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Publicinfo> listRootUnitShares(String infocode, String unitcode) {
        String sql = getSession().getNamedQuery("LIST_ROOT_UnitShareS").getQueryString();
        
        return getSession().createSQLQuery(sql)
                .addEntity("p", Publicinfo.class)
                .setString("infocode", infocode)
                .setString("unitcode", unitcode)
                .list();
                
    } 
   
}
