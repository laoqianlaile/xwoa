package com.centit.powerruntime.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.powerruntime.po.VOrgSupPower;

public class VOrgSupPowerDao extends BaseDaoImpl<VOrgSupPower> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(VOrgSupPowerDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("itemId", CodeBook.LIKE_HQL_ID);

            filterField.put("orgId", CodeBook.EQUAL_HQL_ID);

            filterField.put("itemName", CodeBook.LIKE_HQL_ID);

            filterField.put("itemStaName", CodeBook.LIKE_HQL_ID);

            filterField.put("itemType", CodeBook.EQUAL_HQL_ID);

            filterField.put("timeLimit", CodeBook.LIKE_HQL_ID);

            filterField.put("isNetwork", CodeBook.LIKE_HQL_ID);

            filterField.put("isFormula", CodeBook.LIKE_HQL_ID);

            filterField.put("phone", CodeBook.LIKE_HQL_ID);

            filterField.put("address", CodeBook.LIKE_HQL_ID);

            filterField.put("isinuse", CodeBook.LIKE_HQL_ID);

            filterField.put("lastmodifytime", CodeBook.LIKE_HQL_ID);

            filterField.put("ischange", CodeBook.LIKE_HQL_ID);

            filterField.put("fileName", CodeBook.LIKE_HQL_ID);

            filterField.put("auditSign", CodeBook.LIKE_HQL_ID);

            filterField.put("monitorPhone", CodeBook.LIKE_HQL_ID);

            filterField.put("acceptLink", CodeBook.LIKE_HQL_ID);

            filterField.put("legalTimeLimit", CodeBook.LIKE_HQL_ID);

            filterField.put("charge", CodeBook.LIKE_HQL_ID);

            filterField.put("formName", CodeBook.LIKE_HQL_ID);

            filterField.put("inFlowImgName", CodeBook.LIKE_HQL_ID);

            filterField.put("ischarge", CodeBook.LIKE_HQL_ID);

            filterField.put("punishClass", CodeBook.LIKE_HQL_ID);

            filterField.put("transactDepname", CodeBook.LIKE_HQL_ID);

            filterField.put("promiseType", CodeBook.LIKE_HQL_ID);

            filterField.put("anticipateType", CodeBook.LIKE_HQL_ID);

            filterField.put("qlDepid", CodeBook.LIKE_HQL_ID);

            filterField.put("qlDepstate", CodeBook.LIKE_HQL_ID);

            filterField.put("entrustName", CodeBook.LIKE_HQL_ID);

            filterField.put("qlState", CodeBook.LIKE_HQL_ID);

            filterField.put("applyItemType", CodeBook.LIKE_HQL_ID);
            
            filterField.put("riskType", CodeBook.LIKE_HQL_ID);
            
            filterField.put("riskDesc", CodeBook.LIKE_HQL_ID);
            
            filterField.put("riskResult", CodeBook.LIKE_HQL_ID);
            
            filterField.put("groupId", CodeBook.LIKE_HQL_ID);
            
            filterField.put("riskId", CodeBook.LIKE_HQL_ID);
            
            filterField.put("optFlowCode", CodeBook.LIKE_HQL_ID);
            
            filterField.put("orgFlowCode", CodeBook.LIKE_HQL_ID);
            
            filterField.put(CodeBook.ORDER_BY_HQL_ID, " itemId ");
            
        }
        return filterField;
    }

    public String getFlowCodeListByItemIdOrgId(String itemId,
            String org_id) {
        String flowCode= "";
        String hql = "from VOrgSupPower where itemId = ? and orgId=? ";
        @SuppressWarnings("unchecked")
        List<VOrgSupPower> sqclList = (List<VOrgSupPower>) this
                .findObjectsByHql(hql, new String[]{itemId,org_id});
        if(sqclList != null && sqclList.size() >= 1){
            VOrgSupPower orgSup = sqclList.get(0);
           flowCode = orgSup.getOrgFlowCode();//优先从部门关联中获取
            
            if(StringUtils.isBlank(flowCode)){//部门关联中没有，再从业务关联中获取
                flowCode = orgSup.getOptFlowCode();
            }
        }
        return flowCode;
    }

    /**
     * 根据权力编码和部门获取权力信息
     * @param item_id
     * @param orgid
     * @return
     */
    @SuppressWarnings("unchecked")
    public VOrgSupPower getSupPowerInfo(String item_id, String orgid) {
        List<VOrgSupPower> po =getHibernateTemplate().find("From VOrgSupPower where itemId=? and orgId=? ", new String[] {item_id,orgid});
        if (po != null && po.size() > 0) {
            return po.get(0);
        }
        return new VOrgSupPower();
    }
}
