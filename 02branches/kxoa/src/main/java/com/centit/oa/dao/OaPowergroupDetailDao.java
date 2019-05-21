package com.centit.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.po.OaPowergroupDetail;
import com.centit.oa.po.OaPowerrolergroup;

public class OaPowergroupDetailDao extends BaseDaoImpl<OaPowergroupDetail> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(OaPowergroupDetailDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("id", CodeBook.EQUAL_HQL_ID);

            filterField.put("no", CodeBook.LIKE_HQL_ID);

            filterField.put("usercode", CodeBook.LIKE_HQL_ID);

            filterField.put("creatertime", CodeBook.LIKE_HQL_ID);

            filterField.put("depid", CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    }

    /**
     * 批量删除某个分组详情
     * 
     * @param rolergroup
     */
    public void deleteDetails(OaPowerrolergroup rolergroup) {
        // TODO Auto-generated method stub

        if (StringUtils.isNotBlank(rolergroup.getNo())) {
            doExecuteHql("delete from OaPowergroupDetail  where no=?) ",
                    new Object[] { rolergroup.getNo() });
        }
    }
    /**
     *根据权限分组No获取分组详细
     * @return
     */
    public List<OaPowergroupDetail> getUserlist(OaPowerrolergroup rolergroup) {
        String shql = " from OaPowergroupDetail where no=?";
        Object[] objects = new Object[] { rolergroup.getNo() };
        List<OaPowergroupDetail> list = this.listObjects(shql, objects);
        return list;
    }
    
    /**
     *根据权限分组No获取分组详细的usercode
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<String> listUsercodesByNo(String no) {
        String hsql=" select t.usercode from OaPowergroupDetail t where t.no=?";
        return (List<String>) this.findObjectsByHql(hsql, no);
    }

    @SuppressWarnings("unchecked")
    public List<OaPowergroupDetail> getDetailsByUsercode(String usercode) {
        String hsql=" from OaPowergroupDetail t where t.no in ( select no from OaPowerrolergroup t  where  t.state='T' and (t.groupType='2' or t.groupType='1' and t.creater=?))";
        return (List<OaPowergroupDetail>) this.findObjectsByHql(hsql, usercode);
       
    }
   
}
