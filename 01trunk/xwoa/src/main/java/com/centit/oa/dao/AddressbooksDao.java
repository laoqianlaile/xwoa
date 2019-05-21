package com.centit.oa.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.oa.po.Addressbooks;


public class AddressbooksDao extends BaseDaoImpl<Addressbooks>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(AddressbooksDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("addrbookid" , CodeBook.EQUAL_HQL_ID);


			filterField.put("isshare" , CodeBook.LIKE_HQL_ID);

			filterField.put("userName" , CodeBook.LIKE_HQL_ID);

			filterField.put("sex" , CodeBook.LIKE_HQL_ID);

			filterField.put("belond" , CodeBook.LIKE_HQL_ID);

			filterField.put("bodyType" , CodeBook.LIKE_HQL_ID);

			filterField.put("country" , CodeBook.LIKE_HQL_ID);

			filterField.put("province" , CodeBook.LIKE_HQL_ID);

			filterField.put("city" , CodeBook.LIKE_HQL_ID);

			filterField.put("unitcode" , CodeBook.EQUAL_HQL_ID);
			
			filterField.put("unitName" , CodeBook.LIKE_HQL_ID);

			filterField.put("deptName" , CodeBook.LIKE_HQL_ID);

			filterField.put("rankName" , CodeBook.LIKE_HQL_ID);

			filterField.put("profession" , CodeBook.LIKE_HQL_ID);

			filterField.put("mobilephone" , CodeBook.LIKE_HQL_ID);

			filterField.put("telphone" , CodeBook.LIKE_HQL_ID);

			filterField.put("email" , CodeBook.LIKE_HQL_ID);

			filterField.put("otherphone" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

			filterField.put("creater" , CodeBook.EQUAL_HQL_ID);

			filterField.put("createdate" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("type" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	}
	 /**
     *根据通讯主体ID获得该条记录的共享人
     * @param addrbookid
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<String> listUsercodesByTrainId(String addrbookid) {
        String hsql=" select t.cid.shareman from AddressBookRelection t where t.cid.addrbookid=?";
        return (List<String>) this.findObjectsByHql(hsql, addrbookid);
    } 
    /**
     * 获取所有公共通讯录记录--isshare交通讯录开关
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Addressbooks> addressbooksCList() {
        return  getHibernateTemplate().find(" from Addressbooks where type='C' and isshare='1' )");
    } 
    
    /**
     * 获取所有公共通讯录记录--当前部门下所有人员（isshare交通讯录开关未使用）
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Addressbooks> addressbooksCList(String unitcode) {
        return  getHibernateTemplate().find(" from Addressbooks where type='C' and unitcode=? )",new Object[]{unitcode});
    } 
    
    public void margin(Addressbooks object) {
        getHibernateTemplate().merge(object);
    }
    @SuppressWarnings("unchecked")
    public Addressbooks getObjectByCode(String unitcode, String type) {
        Addressbooks addressBook = new Addressbooks();
        List<Addressbooks> list = new ArrayList<Addressbooks>();
        //个人person ：P、公共company:C、机关单位organ:O
        if("O".equals(type)){//单位
            list = getHibernateTemplate().find(" from Addressbooks where addrbookid in (" +
                                    " select a.addrbookid from Addressbooks a,FUnitinfo f where a.unitcode=f.unitcode and " +
                                    " a.unitcode = ? and a.type = ? )", new Object[] {unitcode,type});
        }
        if(list.size() > 0){
            addressBook = list.get(0);
        }
        
        return addressBook;
    }
    @SuppressWarnings("unchecked")
    public Addressbooks getUserByCode(String usercode, String type) {
        Addressbooks addressBook = new Addressbooks();
        List<Addressbooks> list = new ArrayList<Addressbooks>();
        //个人person ：P、公共company:C、机关单位organ:O 确保Addressbooks中数据在人员表中存在
        if("C".equals(type)){//单位
            list = getHibernateTemplate().find(" from Addressbooks where addrbookid in (" +
                                    " select a.addrbookid from Addressbooks a,FUserinfo f where a.userName=f.usercode and " +
                                    " a.userName = ? and a.type = ? )", new Object[] {usercode,type});
        }
        if(list.size() > 0){
            addressBook = list.get(0);
        }
        
        return addressBook;
    }
    
    
   
}
