package com.centit.powerbase.dao;

import java.util.HashMap;
import java.util.Map;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.powerbase.po.Dataenterprise;
import com.centit.support.utils.DatetimeOpt;

@SuppressWarnings("serial")
public class DataenterpriseDao extends BaseDaoImpl<Dataenterprise> {
    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("enterpriseid", CodeBook.EQUAL_HQL_ID);

            filterField.put("applicant", CodeBook.LIKE_HQL_ID);

            filterField.put("applicantPaperType", CodeBook.EQUAL_HQL_ID);

            filterField.put("applicantPaperNumber", CodeBook.LIKE_HQL_ID);

            filterField.put("applicantPhone", CodeBook.LIKE_HQL_ID);

            filterField.put("applicantMobile", CodeBook.LIKE_HQL_ID);

            filterField.put("applicantAddress", CodeBook.LIKE_HQL_ID);

            filterField.put("applicantZipcode", CodeBook.LIKE_HQL_ID);

            filterField.put("applicantEmail", CodeBook.LIKE_HQL_ID);

            filterField.put("unitType", CodeBook.LIKE_HQL_ID);

            filterField.put("corpDomain", CodeBook.LIKE_HQL_ID);

            filterField.put("regType", CodeBook.LIKE_HQL_ID);

            filterField.put("fax", CodeBook.LIKE_HQL_ID);

            filterField.put("linkman", CodeBook.LIKE_HQL_ID);

            filterField.put("linkmanName", CodeBook.LIKE_HQL_ID);

            filterField.put("linkmanPaperType", CodeBook.LIKE_HQL_ID);

            filterField.put("linkmanPaperCode", CodeBook.LIKE_HQL_ID);

            filterField.put("linkmanPhone", CodeBook.LIKE_HQL_ID);

            filterField.put("linkmanMobile", CodeBook.LIKE_HQL_ID);

            filterField.put("linkmanAddress", CodeBook.LIKE_HQL_ID);

            filterField.put("linkmanZipcode", CodeBook.LIKE_HQL_ID);

            filterField.put("linkmanEmail", CodeBook.LIKE_HQL_ID);

            filterField.put("isInuse", CodeBook.EQUAL_HQL_ID);

            filterField.put("begTime",
                    " lastModdate >= to_date(?,'yyyy-mm-dd')");
            filterField.put("endTime",
                    " lastModdate <= to_date(?,'yyyy-mm-dd')+1");

            filterField.put(CodeBook.ORDER_BY_HQL_ID, " lastModdate desc");

        }
        return filterField;
    }

    /**
     * 获取下一个编码
     * 
     * @return
     */
    public Long genNextID() {
        Long no = super.getNextLongSequence("S_Enterprise_ID");
        return no;
    }

    /**
     * 修改组织机构是否使用状态
     */
    public void disableObject(Dataenterprise object) {
        String isInuse = object.getIsInuse();
        if ("T".equals(isInuse)) {
            isInuse = "F";
        } else {
            isInuse = "T";
        }
        object.setIsInuse(isInuse);
        object.setLastModdate(DatetimeOpt.currentUtilDate());
        saveObject(object);
    }
}
