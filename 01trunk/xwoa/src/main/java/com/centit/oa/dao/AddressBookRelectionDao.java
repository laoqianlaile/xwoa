package com.centit.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.oa.po.AddressBookRelection;

public class AddressBookRelectionDao extends BaseDaoImpl<AddressBookRelection> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(AddressBookRelectionDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("addrbookid", CodeBook.EQUAL_HQL_ID);
            filterField.put("shareman", CodeBook.EQUAL_HQL_ID);
            filterField.put("bizType", CodeBook.EQUAL_HQL_ID);

        }
        return filterField;
    }

    public List<AddressBookRelection> getUserlist(String djId, String biztype) {
        String shql = " from AddressBookRelection where cid.addrbookid=? and cid.bizType=?  ";
        Object[] objects = new Object[] { djId, biztype };
        List<AddressBookRelection> list = this.listObjects(shql, objects);
        return list;
    }

    public void deleteuser(String djId) {

        doExecuteHql(
                "delete from AddressBookRelection  where cid.addrbookid=?) ",
                new Object[] { djId });
    }
    public void deleteuser(String djId, String biztype) {
        doExecuteHql(
                "delete from AddressBookRelection  where cid.addrbookid=? and cid.bizType=?) ",
                new Object[] { djId,biztype });
    }
    public List<AddressBookRelection> getUserlist(String djId) {
        String shql = " from AddressBookRelection where cid.addrbookid=?";
        Object[] objects = new Object[] { djId };
        List<AddressBookRelection> list = this.listObjects(shql, objects);
        return list;
    }
}
