package com.centit.app.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.transform.Transformers;

import com.centit.app.po.AddressBookFs;

public class AddressBookFsDao extends BaseDaoImpl<AddressBookFs>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(AddressBookFsDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("addrbookid" , CodeBook.EQUAL_HQL_ID);


			filterField.put("bodytype" , CodeBook.LIKE_HQL_ID);

			filterField.put("bodycode" , CodeBook.LIKE_HQL_ID);

			filterField.put("representation" , CodeBook.LIKE_HQL_ID);

			filterField.put("unitname" , CodeBook.LIKE_HQL_ID);

			filterField.put("deptname" , CodeBook.LIKE_HQL_ID);

			filterField.put("rankname" , CodeBook.LIKE_HQL_ID);

			filterField.put("email" , CodeBook.LIKE_HQL_ID);

			filterField.put("email2" , CodeBook.LIKE_HQL_ID);

			filterField.put("email3" , CodeBook.LIKE_HQL_ID);

			filterField.put("homepage" , CodeBook.LIKE_HQL_ID);

			filterField.put("qq" , CodeBook.LIKE_HQL_ID);

			filterField.put("msn" , CodeBook.LIKE_HQL_ID);

			filterField.put("wangwang" , CodeBook.LIKE_HQL_ID);

			filterField.put("buzphone" , CodeBook.LIKE_HQL_ID);

			filterField.put("buzphone2" , CodeBook.LIKE_HQL_ID);

			filterField.put("buzfax" , CodeBook.LIKE_HQL_ID);

			filterField.put("assiphone" , CodeBook.LIKE_HQL_ID);

			filterField.put("callbacphone" , CodeBook.LIKE_HQL_ID);

			filterField.put("carphone" , CodeBook.LIKE_HQL_ID);

			filterField.put("unitphone" , CodeBook.LIKE_HQL_ID);

			filterField.put("homephone" , CodeBook.LIKE_HQL_ID);

			filterField.put("homephone2" , CodeBook.LIKE_HQL_ID);

			filterField.put("homephone3" , CodeBook.LIKE_HQL_ID);

			filterField.put("homefax" , CodeBook.LIKE_HQL_ID);

			filterField.put("mobilephone" , CodeBook.LIKE_HQL_ID);

			filterField.put("mobilephone2" , CodeBook.LIKE_HQL_ID);

			filterField.put("mobilephone3" , CodeBook.LIKE_HQL_ID);

			filterField.put("unitzip" , CodeBook.LIKE_HQL_ID);

			filterField.put("unitprovince" , CodeBook.LIKE_HQL_ID);

			filterField.put("unitcity" , CodeBook.LIKE_HQL_ID);

			filterField.put("unitdistrict" , CodeBook.LIKE_HQL_ID);

			filterField.put("unitstreet" , CodeBook.LIKE_HQL_ID);

			filterField.put("unitaddress" , CodeBook.LIKE_HQL_ID);

			filterField.put("homezip" , CodeBook.LIKE_HQL_ID);

			filterField.put("homeprovince" , CodeBook.LIKE_HQL_ID);

			filterField.put("homecity" , CodeBook.LIKE_HQL_ID);

			filterField.put("homedistrict" , CodeBook.LIKE_HQL_ID);

			filterField.put("homestreet" , CodeBook.LIKE_HQL_ID);

			filterField.put("homeaddress" , CodeBook.LIKE_HQL_ID);

			filterField.put("home2zip" , CodeBook.LIKE_HQL_ID);

			filterField.put("home2province" , CodeBook.LIKE_HQL_ID);

			filterField.put("home2city" , CodeBook.LIKE_HQL_ID);

			filterField.put("home2district" , CodeBook.LIKE_HQL_ID);

			filterField.put("home2street" , CodeBook.LIKE_HQL_ID);

			filterField.put("home2address" , CodeBook.LIKE_HQL_ID);

			filterField.put("inuseaddress" , CodeBook.LIKE_HQL_ID);

			filterField.put("searchstring" , CodeBook.LIKE_HQL_ID);

			filterField.put("memo" , CodeBook.LIKE_HQL_ID);

			filterField.put("lastmodifydate" , CodeBook.LIKE_HQL_ID);

			filterField.put("createdate" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	}

	@SuppressWarnings("unchecked")
    public List<Object> listBodyType() {
        StringBuffer hql = new StringBuffer();
        hql.append("select t.datacode code,t.datavalue as value from f_datadictionary t where t.catalogcode='TXLRYLB'");
        return this.getHibernateTemplate().getSessionFactory().openSession()
                .createSQLQuery(hql.toString())
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }
    
    @SuppressWarnings("unchecked")
    public List<Object> listUnits() {
        StringBuffer hql = new StringBuffer();
        hql.append("select t.unitcode as  code,t.unitname as value from f_unitinfo t where t.isvalid='T' and t.parentunit<>'0'");
        return this.getHibernateTemplate().getSessionFactory().openSession()
                .createSQLQuery(hql.toString())
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }
    @SuppressWarnings("unchecked")
    public AddressBookFs getObjectByCode(String bodycode,String bodytype) {
        AddressBookFs addressBook = new AddressBookFs();
        List<AddressBookFs> list = new ArrayList<AddressBookFs>();
        if("2".equals(bodytype)){//单位
            list = getHibernateTemplate().find(" from AddressBookFs where addrbookid in (" +
                            		" select a.addrbookid from AddressBookFs a,FUnitinfo f where a.bodycode=f.unitcode and " +
                            		" bodycode = ? and bodytype = ? )", new Object[] {bodycode,bodytype});
        }else if("1".equals(bodytype)){//用户
            list = getHibernateTemplate().find(" from AddressBookFs where addrbookid in (" +
                            " select a.addrbookid from AddressBookFs a,FUserinfo f where a.bodycode=f.usercode and " +
                            " bodycode = ? and bodytype = ? )", new Object[] {bodycode,bodytype});
        }
        if(list.size() > 0){
            addressBook = list.get(0);
        }
        
        return addressBook;
    }
    @SuppressWarnings("unchecked")
    public AddressBookFs getObjectByUsername(String username,String bodytype){
        AddressBookFs addressBook = new AddressBookFs();
        List<AddressBookFs> list = new ArrayList<AddressBookFs>();
        if("2".equals(bodytype)){//单位
            list = getHibernateTemplate().find(" from AddressBookFs where addrbookid in (" +
                                    " select a.addrbookid from AddressBookFs a,FUnitinfo f " +
                                    "where a.bodycode=f.unitcode and " +
                                    " unitname like ? and bodytype = ? )", new Object[] {"%"+username+"%",bodytype});
        }else if("1".equals(bodytype)){//用户
            list = getHibernateTemplate().find(" from AddressBookFs where addrbookid in (" +
                            " select a.addrbookid from AddressBookFs a,FUserinfo f " +
                            " where a.bodycode=f.usercode and " +
                            " username like ? and bodytype = ? )", new Object[] {"%"+username+"%",bodytype});
        }
        if(list.size() > 0){
            addressBook = list.get(0);
        }        
        return addressBook;
    } 
    @SuppressWarnings("rawtypes")
    public Object getObjectByPhone(String phone,String bodytype){
        Object obj=null;
        List list = new ArrayList();
        if("2".equals(bodytype)){//单位
            list = getHibernateTemplate().find(" from FUnitinfo where unitcode in (" +
                                    " select a.bodycode from AddressBookFs a where  " +
                                    " a.mobilephone = ? or a.mobilephone2=? or a.mobilephone3=?  and a.bodytype = ? )",
                                    new Object[] {phone,phone,phone,bodytype});
        }else if("1".equals(bodytype)){//用户
            list = getHibernateTemplate().find(" from FUserinfo where usercode in (" +
                            " select a.bodycode from AddressBookFs a  where  " +
                            " a.mobilephone = ? or a.mobilephone2=? or a.mobilephone3=?  and a.bodytype = ? )", 
                            new Object[] {phone,phone,phone,bodytype});
        }
        if(list.size() > 0){
            obj = list.get(0);
        }        
        return obj;
    }
    @SuppressWarnings("unchecked")
    public Object getObjectByPPhone(String phone,String bodytype){
        Object obj=null;
        List<AddressBookFs> list = new ArrayList<AddressBookFs>();
        if("2".equals(bodytype)){//单位
            list = getHibernateTemplate().find(" from AddressBookFs a where  " +
                                    " a.mobilephone like ? or a.mobilephone2 like ? " +
                                    " or a.mobilephone3 like ?  and a.bodytype = ? )",
                                    new Object[] {phone+"%",phone+"%",phone+"%",bodytype});
        }else if("1".equals(bodytype)){//用户
            list = getHibernateTemplate().find("  from AddressBookFs a  where  " +
                            " a.mobilephone like ? or a.mobilephone2 like ?  or a.mobilephone3 " +
                            " like ?  and a.bodytype = ? )", 
                            new Object[] {phone+"%",phone+"%",phone+"%",bodytype});
        }
        if(list.size() > 0){
            obj = list.get(0);
        }        
        return obj;
    }
}
