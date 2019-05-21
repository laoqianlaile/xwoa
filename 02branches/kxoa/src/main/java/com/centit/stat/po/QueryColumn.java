package com.centit.stat.po;

import net.sf.json.JSONObject;

import com.centit.support.utils.JSONOpt;


/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class QueryColumn implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	private QueryColumnId cid;
	private String  optType;
	private String  drawChart;
	private String  colType;
	private String colFormat;
	private String  colLogic;
	private Integer colOrder;
    private String isShow;
    /**
     * R 行头  C 列头  D 数值
     */
    private String showType; 
    private Double averageValue;
    private Double sumValue;
    private String colProperty; 
    
    /**
     * 链接类型 navTab dialog blank等
     */
    private String linkType;
    
    private String defaultValue;
    
    public String getDefaultValue() {
        return defaultValue;
    }
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
    public String getLinkType() {
        return linkType;
    }
    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }
    
    // Constructors
	/** default constructor */
	public QueryColumn() {
	}
	/** minimal constructor */
	public QueryColumn(QueryColumnId id 
				
		) {
		this.cid = id; 
			
			
	}
	
	public QueryColumn(Integer colOrder, String colName,String colType,String colFormat, String optType,String drawChart,String colLogic, String isShow){
	    this.cid = new QueryColumnId();
        this.cid.setColName(colName);
        this.optType = optType;
        this.colType = colType;
        this.drawChart = drawChart;
        this.averageValue = 0.0;
        this.sumValue = 0.0;
        this.colLogic = colLogic;
        this.isShow = isShow;
        this.colFormat = colFormat;
        this.colOrder = colOrder;
    }
    
    public QueryColumn(String colName,String colType,String optType,String drawChart){
        this(0, colName,colType,optType,"",drawChart,null,"T");
    }
    
    public QueryColumn(String colName,String colType,String optType){
        this(0, colName,colType,optType,"","F",null,"T");
    } 
    
    public QueryColumn(String colName,String colType){
        this(0, colName,colType,"","0","F",null,"T");
    }
    

/** full constructor */
	public QueryColumn(QueryColumnId id
			
	,String  optType,String  drawChart,String  colType,String  colLogic,Integer colorder,String isShow,String showType) {
		this.cid = id; 
			
	
		this.optType= optType;
		this.drawChart= drawChart;
		this.colType= colType;
		this.colLogic= colLogic;	
		this.colOrder=colorder;
		this.isShow = isShow;
        this.showType= showType;

	}

	public QueryColumnId getCid() {
		return this.cid;
	}
	
	public void setCid(QueryColumnId id) {
		this.cid = id;
	}
	public String getIsShow() {
        return isShow;
    }
    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }
	public String getModelName() {
		if(this.cid==null)
			this.cid = new QueryColumnId();
		return this.cid.getModelName();
	}
	
	public void setModelName(String modelName) {
		if(this.cid==null)
			this.cid = new QueryColumnId();
		this.cid.setModelName(modelName);
	}
  
	public String getColName() {
		if(this.cid==null)
			this.cid = new QueryColumnId();
		return this.cid.getColName();
	}
	
	public void setColName(String colName) {
		if(this.cid==null)
			this.cid = new QueryColumnId();
		this.cid.setColName(colName);
	}
	
	

	// Property accessors
  
	public String getOptType() {
		return this.optType;
	}
	
	public void setOptType(String optType) {
		this.optType = optType;
	}
  
	public String getDrawChart() {
		return this.drawChart;
	}
	
	public void setDrawChart(String drawChart) {
		this.drawChart = drawChart;
	}
  
	public String getColType() {
		return this.colType;
	}
	
	public void setColType(String colType) {
		this.colType = colType;
	}
  
	public String getColLogic() {
		return this.colLogic;
	}
	
	public void setColLogic(String colLogic) {
		this.colLogic = colLogic;
	}

	public Integer getColOrder() {
        return colOrder;
    }
    public void setColOrder(Integer colorder) {
        this.colOrder = colorder;
    }

	public QueryColumn copy(QueryColumn other){
  
		this.setModelName(other.getModelName());  
		this.setColName(other.getColName());
  
		this.optType= other.getOptType();  
		this.drawChart= other.getDrawChart();  
		this.colType= other.getColType();  
		this.colLogic= other.getColLogic();
        this.colOrder=other.getColOrder();
        this.isShow = other.getIsShow();
        this.showType= other.getShowType();
        this.colFormat = other.getColFormat();
        this.linkType = other.getLinkType();
        this.defaultValue = other.getDefaultValue();
        return this;
	}
	
	public QueryColumn copyNotNullProperty(QueryColumn other){
  
    	if( other.getModelName() != null)
    		this.setModelName(other.getModelName());  
    	if( other.getColName() != null)
    		this.setColName(other.getColName());
  
		if( other.getOptType() != null)
			this.optType= other.getOptType();  
		if( other.getDrawChart() != null)
			this.drawChart= other.getDrawChart();  
		if( other.getColType() != null)
			this.colType= other.getColType();  
		if( other.getColLogic() != null)
			this.colLogic= other.getColLogic();
		if( other.getColOrder() != null)
            this.colOrder= other.getColOrder();
		if( other.getIsShow() != null)
            this.isShow= other.getIsShow();
        if( other.getShowType() != null)
            this.showType= other.getShowType();
        if(other.getLinkType()!=null) 
            this.linkType = other.getLinkType();
		if (other.getColFormat() != null) {
		    this.colFormat = other.getColFormat();
		}
		if (other.getDefaultValue() != null) {
            this.defaultValue = other.getDefaultValue();
        }
		return this;
	}
	
	public QueryColumn clearProperties(){
  
		this.optType= null;  
		this.drawChart= null;  
		this.colType= null;  
		this.colLogic= null;
		this.colOrder=null;
		this.isShow=null;
		this.showType="D";
		this.colFormat = null;
		this.linkType = null;
		return this;
	}
    public String getColFormat() {
        return colFormat;
    }
    public void setColFormat(String colFormat) {
        this.colFormat = colFormat;
    }
    /**
     * R 行头  C 列头  D 数值
     * @return
     */
    public String getShowType() {
        return showType;
    }
    /**
     * R 行头  C 列头  D 数值
     * @param showType
     */
    public void setShowType(String showType) {
        this.showType = showType;
    }
    
    public Double getAverageValue() {
        return averageValue;
    }
    public void setAverageValue(Double averageValue) {
        this.averageValue = averageValue;
    }
    public Double getSumValue() {
        return sumValue;
    }
    public void setSumValue(Double sumValue) {
        this.sumValue = sumValue;
    }
    public String getColProperty() {
        return colProperty;
    }
    public void setColProperty(String colProperty) {
        this.colProperty = colProperty;
    }
	
    public JSONObject toJsonData()
    {
        JSONObject objJson = new JSONObject();
        JSONOpt.setAttribute(objJson, "colName", this.getColName());
        JSONOpt.setAttribute(objJson, "averageValue", averageValue);
        JSONOpt.setAttribute(objJson, "sumValue", sumValue);
        JSONOpt.setAttribute(objJson, "optType", optType);
        JSONOpt.setAttribute(objJson, "drawChart", drawChart);
        JSONOpt.setAttribute(objJson, "colType", colType);
        JSONOpt.setAttribute(objJson, "colOrder", colOrder);
        JSONOpt.setAttribute(objJson, "colFormat", colFormat);
        JSONOpt.setAttribute(objJson, "colLogic", colLogic);
        JSONOpt.setAttribute(objJson, "isShow", isShow);
        JSONOpt.setAttribute(objJson, "showType", showType); 
        
        return objJson;
    }  
}
