package com.centit.powerruntime.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.dao.CodeBook;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.powerruntime.po.TemplateFile;
import com.centit.powerruntime.service.TemplateFileManager;

public class TemplateFileAction extends BaseEntityExtremeAction<TemplateFile> {
    private static final long serialVersionUID = 1L;
    private TemplateFileManager templateFileManager;
    private List<TemplateFile> templateList;

    public void setTemplateFileManager(TemplateFileManager tempFileManager) {
        this.templateFileManager = tempFileManager;
        this.setBaseEntityManager(templateFileManager);
    }
    @SuppressWarnings("unchecked")
    public String list() {
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);

           /* String orderField = request.getParameter("orderField");
            String orderDirection = request.getParameter("orderDirection");
*/
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
       /*     if (!StringUtils.isBlank(orderField) && !StringUtils.isBlank(orderDirection)) {

                filterMap.put(CodeBook.SELF_ORDER_BY, orderField + " " + orderDirection);
            }*/
            PageDesc pageDesc = makePageDesc();
            objList = templateFileManager.listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            setbackSearchColumn(filterMap);
            this.pageDesc = pageDesc;
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    public String listSelect() {
        @SuppressWarnings("unchecked")
        Map<Object,Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        
        Map<String,Object> filterMap = convertSearchColumn( paramMap );
        Limit limit=ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc =  ExtremeTableUtils.makePageDesc(limit); 
        templateList  = templateFileManager.listObjects(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        return "listSelect";
    }
    public String delete(){  
        super.delete();
        return this.list();
    }
    public List<TemplateFile> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<TemplateFile> templateList) {
        this.templateList = templateList;
    }
}
