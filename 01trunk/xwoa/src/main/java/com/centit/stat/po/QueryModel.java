package com.centit.stat.po;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class QueryModel implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String modelName;

	/**
	 * 2 ： 二维表  3 ：同比分析 4：环比分析 5：交叉制表
	 */
	private String  modelType;
	private String  ownerType;
	private String  ownerCode;
	private String  querySql;
	private String  queryDesc;
	private String  formNameFormat;
	private String  resultName;
	private String  rowDrawChart;
	private Long  drawChartBeginCol;
	private Long  drawChartEndCol;
	private String  additionRow;
	private String  rowLogic;
	private Long  rowLogicValue;
	private String logicUrl;
	private String isTree;
	
	private String columnSql;

    private Set<QueryColumn> queryColumns = null;// new ArrayList<QueryColumn>();
	private Set<QueryCondition> queryConditions = null;// new ArrayList<QueryConditon>();

	// Constructors
	/** default constructor */
	public QueryModel() {
	}
	/** minimal constructor */
	public QueryModel(
		String modelName		
		) {
	
	
		this.modelName = modelName;		
			
	}

	public String getIsTree() {
		return isTree;
	}

	public void setIsTree(String isTree) {
		this.isTree = isTree;
	}
/** full constructor */
	public QueryModel(
	 String modelName		
	,String  modelType,String  ownerType,String  ownerCode,String  querySql
	,String  queryDesc,String  formNameFormat,String  resultName
	,String  rowDrawChart,Long  drawChartBeginCol,Long  drawChartEndCol
	,String  additionRow,String  rowLogic,Long  rowLogicValue,String logicUrl) {
	
	
		this.modelName = modelName;		
	
		this.modelType= modelType;
		this.ownerType= ownerType;
		this.ownerCode= ownerCode;
		this.querySql= querySql;
		this.queryDesc= queryDesc;
		this.formNameFormat= formNameFormat;
		this.resultName= resultName;
		this.rowDrawChart= rowDrawChart;
		this.drawChartBeginCol= drawChartBeginCol;
		this.drawChartEndCol= drawChartEndCol;
		this.additionRow= additionRow;
		this.rowLogic= rowLogic;
		this.rowLogicValue= rowLogicValue;
		this.logicUrl = logicUrl;
	}
	
	public String getLogicUrl() {
        return logicUrl;
    }
    public void setLogicUrl(String logicUrl) {
        this.logicUrl = logicUrl;
    }
  
	public String getModelName() {
		return this.modelName;
	}

	public String getColumnSql() {
        return columnSql;
    }
    public void setColumnSql(String columnSql) {
        this.columnSql = columnSql;
    }
    public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	// Property accessors
  
	/**
     * 2 ： 二维表  3 ：同比分析 4：环比分析 5：交叉制表
	 * @return
	 */
	public String getModelType() {
		return this.modelType;
	}
	/**
	 * 2 ： 二维表  3 ：同比分析 4：环比分析 5：交叉制表
	 * @param modelType
	 */
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
  
	public String getOwnerType() {
		return this.ownerType;
	}
	
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}
  
	public String getOwnerCode() {
		return this.ownerCode;
	}
	
	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}
  
	public String getQuerySql() {
		return this.querySql;
	}
	
	public void setQuerySql(String querySql) {
		this.querySql = querySql;
	}
  
	public String getQueryDesc() {
		return this.queryDesc;
	}
	
	public void setQueryDesc(String queryDesc) {
		this.queryDesc = queryDesc;
	}
  
	public String getFormNameFormat() {
		return this.formNameFormat;
	}
	
	public void setFormNameFormat(String formNameFormat) {
		this.formNameFormat = formNameFormat;
	}
  
	public String getResultName() {
		return this.resultName;
	}
	
	public void setResultName(String resultName) {
		this.resultName = resultName;
	}
  
	public String getRowDrawChart() {
		return this.rowDrawChart;
	}
	
	public void setRowDrawChart(String rowDrawChart) {
		this.rowDrawChart = rowDrawChart;
	}
  
	public Long getDrawChartBeginCol() {
		return this.drawChartBeginCol;
	}
	
	public void setDrawChartBeginCol(Long drawChartBeginCol) {
		this.drawChartBeginCol = drawChartBeginCol;
	}
  
	public Long getDrawChartEndCol() {
		return this.drawChartEndCol;
	}
	
	public void setDrawChartEndCol(Long drawChartEndCol) {
		this.drawChartEndCol = drawChartEndCol;
	}
  
	public String getAdditionRow() {
		return this.additionRow;
	}
	
	public void setAdditionRow(String additionRow) {
		this.additionRow = additionRow;
	}
  
	public String getRowLogic() {
		return this.rowLogic;
	}
	
	public void setRowLogic(String rowLogic) {
		this.rowLogic = rowLogic;
	}
  
	public Long getRowLogicValue() {
		return this.rowLogicValue;
	}
	
	public void setRowLogicValue(Long rowLogicValue) {
		this.rowLogicValue = rowLogicValue;
	}


	public Set<QueryColumn> getQueryColumns(){
		if(this.queryColumns==null)
			this.queryColumns = new HashSet<QueryColumn>();
		return this.queryColumns;
	}

	public void setQueryColumns(Set<QueryColumn> queryColumns) {
		this.queryColumns = queryColumns;
	}	

	public void addQueryColumn(QueryColumn queryColumn ){
		if (this.queryColumns==null)
			this.queryColumns = new HashSet<QueryColumn>();
		this.queryColumns.add(queryColumn);
	}
	
	public void removeQueryColumn(QueryColumn queryColumn ){
		if (this.queryColumns==null)
			return;
		this.queryColumns.remove(queryColumn);
	}
	
	public QueryColumn newQueryColumn(){
		QueryColumn res = new QueryColumn();
  
		res.setModelName(this.getModelName());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
	 * 
	 */
	public void replaceQueryColumns(List<QueryColumn> queryColumns) {
		List<QueryColumn> newObjs = new ArrayList<QueryColumn>();
		for(QueryColumn p :queryColumns){
			if(p==null)
				continue;
			QueryColumn newdt = newQueryColumn();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<QueryColumn> oldObjs = new HashSet<QueryColumn>();
		oldObjs.addAll(getQueryColumns());
		
		for(Iterator<QueryColumn> it=oldObjs.iterator(); it.hasNext();){
			QueryColumn odt = it.next();
			found = false;
			for(QueryColumn newdt :newObjs){
				if(odt.getCid().equals( newdt.getCid())){
					found = true;
					break;
				}
			}
			if(! found)
				removeQueryColumn(odt);
		}
		oldObjs.clear();
		//insert or update
		for(QueryColumn newdt :newObjs){
			found = false;
			for(Iterator<QueryColumn> it=getQueryColumns().iterator();
			 it.hasNext();){
				QueryColumn odt = it.next();
				if(odt.getCid().equals( newdt.getCid())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addQueryColumn(newdt);
		} 	
	}	

	public Set<QueryCondition> getQueryConditions(){
		if(this.queryConditions==null)
			this.queryConditions = new HashSet<QueryCondition>();
		return this.queryConditions;
	}

	public void setQueryConditions(Set<QueryCondition> queryConditons) {
		this.queryConditions = queryConditons;
	}	

	public void addQueryConditon(QueryCondition queryConditon ){
		if (this.queryConditions==null)
			this.queryConditions = new HashSet<QueryCondition>();
		this.queryConditions.add(queryConditon);
	}
	
	public void removeQueryConditon(QueryCondition queryConditon ){
		if (this.queryConditions==null)
			return;
		this.queryConditions.remove(queryConditon);
	}
	
	public QueryCondition newQueryConditon(){
		QueryCondition res = new QueryCondition();
  
		res.setModelName(this.getModelName());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
	 * 
	 */
	public void replaceQueryConditons(List<QueryCondition> queryConditons) {
		List<QueryCondition> newObjs = new ArrayList<QueryCondition>();
		for(QueryCondition p :queryConditons){
			if(p==null)
				continue;
			QueryCondition newdt = newQueryConditon();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<QueryCondition> oldObjs = new HashSet<QueryCondition>();
		oldObjs.addAll(getQueryConditions());
		
		for(Iterator<QueryCondition> it=oldObjs.iterator(); it.hasNext();){
			QueryCondition odt = it.next();
			found = false;
			for(QueryCondition newdt :newObjs){
				if(odt.getCid().equals( newdt.getCid())){
					found = true;
					break;
				}
			}
			if(! found)
				removeQueryConditon(odt);
		}
		oldObjs.clear();
		//insert or update
		for(QueryCondition newdt :newObjs){
			found = false;
			for(Iterator<QueryCondition> it=getQueryConditions().iterator();
			 it.hasNext();){
				QueryCondition odt = it.next();
				if(odt.getCid().equals( newdt.getCid())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addQueryConditon(newdt);
		} 	
	}	


	public void copy(QueryModel other){
  
		this.setModelName(other.getModelName());
  
		this.modelType= other.getModelType();  
		this.ownerType= other.getOwnerType();  
		this.ownerCode= other.getOwnerCode();  
		this.querySql= other.getQuerySql();  
		this.queryDesc= other.getQueryDesc();  
		this.formNameFormat= other.getFormNameFormat();  
		this.resultName= other.getResultName();  
		this.rowDrawChart= other.getRowDrawChart();  
		this.drawChartBeginCol= other.getDrawChartBeginCol();  
		this.drawChartEndCol= other.getDrawChartEndCol();  
		this.additionRow= other.getAdditionRow();  
		this.rowLogic= other.getRowLogic();  
		this.rowLogicValue= other.getRowLogicValue();
	    this.logicUrl = other.getLogicUrl();
		this.queryColumns = other.getQueryColumns();	
		this.queryConditions = other.getQueryConditions();
		this.columnSql = other.getColumnSql();
		this.isTree = other.getIsTree();
	}
	
	public void copyNotNullProperty(QueryModel other){
  
	if( other.getModelName() != null)
		this.setModelName(other.getModelName());
  
		if( other.getModelType() != null)
			this.modelType= other.getModelType();  
		if( other.getOwnerType() != null)
			this.ownerType= other.getOwnerType();  
		if( other.getOwnerCode() != null)
			this.ownerCode= other.getOwnerCode();  
		if( other.getQuerySql() != null)
			this.querySql= other.getQuerySql();  
		if( other.getQueryDesc() != null)
			this.queryDesc= other.getQueryDesc();  
		if( other.getFormNameFormat() != null)
			this.formNameFormat= other.getFormNameFormat();  
		if( other.getResultName() != null)
			this.resultName= other.getResultName();  
		if( other.getRowDrawChart() != null)
			this.rowDrawChart= other.getRowDrawChart();  
		if( other.getDrawChartBeginCol() != null)
			this.drawChartBeginCol= other.getDrawChartBeginCol();  
		if( other.getDrawChartEndCol() != null)
			this.drawChartEndCol= other.getDrawChartEndCol();  
		if( other.getAdditionRow() != null)
			this.additionRow= other.getAdditionRow();  
		if( other.getRowLogic() != null)
			this.rowLogic= other.getRowLogic();  
		if( other.getRowLogicValue() != null)
			this.rowLogicValue= other.getRowLogicValue();
		if(other.getLogicUrl()!=null) 
		    this.logicUrl = other.getLogicUrl();
		if(other.getColumnSql()!=null) 
            this.columnSql = other.getColumnSql();
        this.isTree = other.isTree;
		//this.queryColumns = other.getQueryColumns();	
		//this.queryConditions = other.getQueryConditions();
	}
	
	public void clearProperties(){
  
		this.modelType= null;  
		this.columnSql= null;  
		this.ownerType= null;  
		this.ownerCode= null;  
		this.querySql= null;  
		this.queryDesc= null;  
		this.formNameFormat= null;  
		this.resultName= null;  
		this.rowDrawChart= null;  
		this.drawChartBeginCol= null;  
		this.drawChartEndCol= null;  
		this.additionRow= null;  
		this.rowLogic= null;  
		this.rowLogicValue= null;
	    this.logicUrl = null;
	    this.isTree = null;
		this.queryColumns = new HashSet<QueryColumn>();	
		this.queryConditions = new HashSet<QueryCondition>();
	}
}
