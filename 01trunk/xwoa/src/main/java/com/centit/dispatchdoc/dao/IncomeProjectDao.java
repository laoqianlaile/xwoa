package com.centit.dispatchdoc.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.dispatchdoc.po.IncomeProject;

public class IncomeProjectDao extends BaseDaoImpl<IncomeProject> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(IncomeProjectDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("djId", CodeBook.EQUAL_HQL_ID);

            filterField.put("projectUnitName", CodeBook.LIKE_HQL_ID);
            
            filterField.put("projectUnitType", CodeBook.LIKE_HQL_ID);

            filterField.put("projectOrgCode", CodeBook.LIKE_HQL_ID);

            filterField.put("economicType", CodeBook.LIKE_HQL_ID);

            filterField.put("industryField", CodeBook.LIKE_HQL_ID);

            filterField.put("membership", CodeBook.LIKE_HQL_ID);

            filterField.put("businessScope", CodeBook.LIKE_HQL_ID);

            filterField.put("registeredCapital", CodeBook.LIKE_HQL_ID);

            filterField.put("countryArea", CodeBook.LIKE_HQL_ID);

            filterField.put("registeredAddr", CodeBook.LIKE_HQL_ID);

            filterField.put("buildLegal", CodeBook.LIKE_HQL_ID);

            filterField.put("adminAreaCode", CodeBook.LIKE_HQL_ID);

            filterField.put("adminAreaZip", CodeBook.LIKE_HQL_ID);

            filterField.put("contactName", CodeBook.LIKE_HQL_ID);

            filterField.put("contactPhone", CodeBook.LIKE_HQL_ID);

            filterField.put("contactEmail", CodeBook.LIKE_HQL_ID);

            filterField.put("projectName", CodeBook.LIKE_HQL_ID);

            filterField.put("projectBuildAddr", CodeBook.LIKE_HQL_ID);

            filterField.put("projectAreaCode", CodeBook.LIKE_HQL_ID);

            filterField.put("projectIndustryField", CodeBook.LIKE_HQL_ID);

            filterField.put("projectBuildType", CodeBook.LIKE_HQL_ID);

            filterField.put("projectMembership", CodeBook.LIKE_HQL_ID);

            filterField.put("projectType", CodeBook.LIKE_HQL_ID);

            filterField.put("projectNature", CodeBook.LIKE_HQL_ID);

            filterField.put("projectBuildLand", CodeBook.LIKE_HQL_ID);

            filterField.put("buildBeginDate", CodeBook.LIKE_HQL_ID);

            filterField.put("industryMinistries", CodeBook.LIKE_HQL_ID);

            filterField.put("buildContent", CodeBook.LIKE_HQL_ID);

            filterField.put("totalInvestment", CodeBook.LIKE_HQL_ID);

            filterField.put("allowType", CodeBook.LIKE_HQL_ID);

            filterField.put("allowDocNo", CodeBook.LIKE_HQL_ID);

            filterField.put("allowTime", CodeBook.LIKE_HQL_ID);

            filterField.put("allowUnit", CodeBook.LIKE_HQL_ID);

            filterField.put("auditType", CodeBook.LIKE_HQL_ID);

            filterField.put("auditTime", CodeBook.LIKE_HQL_ID);

            filterField.put("auditDocNo", CodeBook.LIKE_HQL_ID);

            filterField.put("auditUnit", CodeBook.LIKE_HQL_ID);

            filterField.put("eiaType", CodeBook.LIKE_HQL_ID);

            filterField.put("eiaDocNo", CodeBook.LIKE_HQL_ID);

            filterField.put("eiaTime", CodeBook.LIKE_HQL_ID);

            filterField.put("eiaUnit", CodeBook.LIKE_HQL_ID);

            filterField.put("officialType", CodeBook.LIKE_HQL_ID);

            filterField.put("officialDocNo", CodeBook.LIKE_HQL_ID);

            filterField.put("officialTime", CodeBook.LIKE_HQL_ID);

            filterField.put("officialUnit", CodeBook.LIKE_HQL_ID);

            filterField.put("officialBuildContent", CodeBook.LIKE_HQL_ID);

            filterField.put("officialTotalInvestment", CodeBook.LIKE_HQL_ID);

            filterField.put("centreInvestment", CodeBook.LIKE_HQL_ID);

            filterField.put("provinceInvestment", CodeBook.LIKE_HQL_ID);

            filterField.put("cityInvestment", CodeBook.LIKE_HQL_ID);

            filterField.put("countyInvestment", CodeBook.LIKE_HQL_ID);

            filterField.put("townInvestment", CodeBook.LIKE_HQL_ID);

            filterField.put("foreignInvestment", CodeBook.LIKE_HQL_ID);

            filterField.put("unitSelfRaise", CodeBook.LIKE_HQL_ID);

            filterField.put("unitSelfOwner", CodeBook.LIKE_HQL_ID);

            filterField.put("bankAdvance", CodeBook.LIKE_HQL_ID);

            filterField.put("otherAdvance", CodeBook.LIKE_HQL_ID);
            
            filterField.put("otherSources", CodeBook.LIKE_HQL_ID);
            
            filterField.put("eiaApprovalType", CodeBook.LIKE_HQL_ID);
            
            filterField.put("eiaApprovalDocNo", CodeBook.LIKE_HQL_ID);
            
            filterField.put("eiaApprovalTime", CodeBook.LIKE_HQL_ID);
            
            filterField.put("eiaApprovalUnit", CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    }
}
