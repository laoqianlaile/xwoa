package com.centit.stat.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.stat.po.QueryColumn;
import com.centit.stat.po.QueryCondition;
import com.centit.stat.po.QueryModel;
import com.centit.stat.service.QueryModelManager;

public class QueryModelAction  extends BaseEntityExtremeAction<QueryModel>  {
	@SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(QueryModelAction.class);
	private static final long serialVersionUID = 1L;
	
	private QueryModelManager queryModelMag;
	
	public void setQueryModelManager(QueryModelManager basemgr)
	{
		queryModelMag = basemgr;
		this.setBaseEntityManager(queryModelMag);
	}

	private List<QueryColumn> queryColumns;
	
	public List<QueryColumn> getNewQueryColumns() {
		return this.queryColumns;
	}
	
	public void setNewQueryColumns(List<QueryColumn> queryColumns) {
		this.queryColumns = queryColumns;
	}
	
	private List<QueryCondition> queryConditons;
	
	public List<QueryCondition> getNewQueryConditons() {
		return this.queryConditons;
	}
	
	public void setNewQueryConditons(List<QueryCondition> queryConditons) {
		this.queryConditons = queryConditons;
	}
	
	public String toCopy() {
	    object = queryModelMag.getObject(object);
	    return "copy";
	}
	
	public String copy() {
	    QueryModel queryModel = queryModelMag.getObject(object);
	    
	    String newID = request.getParameter("newModelName");
	    QueryModel newQueryModel = new QueryModel();
	    newQueryModel.copyNotNullProperty(object);
	    newQueryModel.setModelName(newID);
	    
	    Set<QueryColumn> columns = new HashSet<QueryColumn>();
	    Set<QueryCondition> conditions = new HashSet<QueryCondition>();
	    
	    for (QueryColumn column : queryModel.getQueryColumns()) {
	        QueryColumn temp = new QueryColumn();
	        temp.copyNotNullProperty(column);
	        temp.setModelName(newID);
	        columns.add(temp);
	    }
	    
	    for (QueryCondition condition : queryModel.getQueryConditions()) {
	        QueryCondition temp = new QueryCondition();
            temp.copyNotNullProperty(condition);
            temp.setModelName(newID);
            conditions.add(temp);
        }
	    
	    newQueryModel.setQueryColumns(columns);
	    newQueryModel.setQueryConditions(conditions);
	    
	    try {
	        queryModelMag.saveObject(newQueryModel);
	        saveMessage("复制成功。");
	    }
	    catch (Exception e) {
	        saveError("复制时发生错误：\n" + e.getMessage());
	    }
	    
	    return "copySuccess";
	}
	
	public String save(){
	    if (null == queryColumns) {
	        queryColumns = new ArrayList<QueryColumn>();
	    }	    
	    if (null == queryConditons) {
	        queryConditons = new ArrayList<QueryCondition>();
	    }
        QueryModel dbObject = baseEntityManager.getObject(object);
        if (dbObject != null) {
            dbObject.copyNotNullProperty(object);
            object = dbObject;
        }
        
		object.replaceQueryColumns( queryColumns);
		object.replaceQueryConditons( queryConditons);
		baseEntityManager.saveObject(object);
        savedMessage();
        return SUCCESS;
	}
		
}
