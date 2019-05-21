package com.centit.powerruntime.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.powerruntime.po.GeneralModuleParam;

public class GeneralModuleParamDao extends BaseDaoImpl<GeneralModuleParam> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(GeneralModuleParamDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("moduleCode", CodeBook.LIKE_HQL_ID);

            filterField.put("ideaLabel", CodeBook.LIKE_HQL_ID);

            filterField.put("ideaCatalog", CodeBook.LIKE_HQL_ID);

            filterField.put("ideaContent", CodeBook.LIKE_HQL_ID);

            filterField.put("hasAttention", CodeBook.LIKE_HQL_ID);

            filterField.put("attentionLabel", CodeBook.LIKE_HQL_ID);

            filterField.put("attentionFilter", CodeBook.LIKE_HQL_ID);

            filterField.put("hasStuff", CodeBook.LIKE_HQL_ID);

            filterField.put("stuffGroupId", CodeBook.LIKE_HQL_ID);

            filterField.put("hasDocument", CodeBook.LIKE_HQL_ID);

            filterField.put("isInUse", CodeBook.LIKE_HQL_ID);

            filterField.put("documentLabel", CodeBook.LIKE_HQL_ID);

            filterField.put("documentType", CodeBook.LIKE_HQL_ID);

            filterField.put("documentTemplate", CodeBook.LIKE_HQL_ID);

            filterField.put("canDefer", CodeBook.LIKE_HQL_ID);

            filterField.put("canRollback", CodeBook.LIKE_HQL_ID);

            filterField.put("canClose", CodeBook.LIKE_HQL_ID);

            filterField.put("riskId", CodeBook.LIKE_HQL_ID);

            filterField.put("assignTeamRole", CodeBook.LIKE_HQL_ID);

            filterField.put("teamRoleCode", CodeBook.LIKE_HQL_ID);

            filterField.put("teamRoleName", CodeBook.LIKE_HQL_ID);

            filterField.put("teamRoleFilter", CodeBook.LIKE_HQL_ID);

            filterField.put("hasPF", CodeBook.LIKE_HQL_ID);

            filterField.put("docReadOnly", CodeBook.LIKE_HQL_ID);

            filterField.put("hasPreIdea", CodeBook.LIKE_HQL_ID);
            
            filterField.put("nodeName", CodeBook.LIKE_HQL_ID);
            
            filterField.put(CodeBook.ORDER_BY_HQL_ID, "lastUpdateTime desc ");

        }
        return filterField;
    }
}
