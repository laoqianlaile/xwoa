package com.centit.stat.po;

import net.sf.json.JSONObject;
import com.centit.support.utils.JSONOpt;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class QueryCondition implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	private QueryConditionId cid;

    private String condLabel;
    private Integer condOrder;
    private String condType;
    private String condValue;
    private String condStyle;//N:普通 nomal H 隐藏 hide R 只读 readonly
    private String condPlace;//条件语句目标位置
    private String condFilterSql;//条件过滤语句
 	private String  compareType;
 	private String condDefault;
	
    // Constructors
	/** default constructor */
	public QueryCondition() {
	    clearProperties();
	}
	
   /**
     * 正常变量的构造函数
     * @param condName
     * @param condLabel
     * @param condType
     * @param condValue
     */
    public QueryCondition(String modelName, String condName,String condLabel,String condType,String condValue){
        this.cid = new QueryConditionId(modelName,condName);
        this.condLabel= condLabel;  
        this.condType= condType;  
        this.condStyle= "N";
        this.condValue= condValue;
        this.condOrder = null;
        this.compareType= "0";  
        this.condPlace = null;
        this.condFilterSql = null;
    }
    
    /**
     * 异常变量的构造函数
     * @param condName
     * @param condStyle //N:普通 nomal H 隐藏 hide R 只读 readonly
     * @param condValue
     */
    public QueryCondition(String modelName, String condName,String condStyle, String condValue){
        this(condName,condName,condStyle,null,condValue);
    }

    
	/** minimal constructor */
	public QueryCondition(QueryConditionId id 
				
		,String  condLabel,String  condStyle) {
		this.cid = id; 
			
	
		this.condLabel= condLabel; 
		this.condStyle= condStyle; 		
	}

/** full constructor */
	public QueryCondition(QueryConditionId id
			
	,String  condLabel,String  condType,String  condStyle,String compareType,String condValue,Integer condOrder,String condPlace,String condFilterSql) {
		this.cid = id; 
			
	
		this.condLabel= condLabel;
		this.condType= condType;
		this.condStyle= condStyle;
		this.condValue = condValue;
		this.compareType = compareType;
		this.condOrder = condOrder;
		this.condPlace = condPlace;
		this.condFilterSql = condFilterSql;
	}
    
	public QueryConditionId getCid() {
		return this.cid;
	}
	
	public void setCid(QueryConditionId id) {
		this.cid = id;
	}
  
	public String getModelName() {
		if(this.cid==null)
			this.cid = new QueryConditionId();
		return this.cid.getModelName();
	}
	
	public void setModelName(String modelName) {
		if(this.cid==null)
			this.cid = new QueryConditionId();
		this.cid.setModelName(modelName);
	}
  
	public String getCondName() {
		if(this.cid==null)
			this.cid = new QueryConditionId();
		return this.cid.getCondName();
	}
	
	public void setCondName(String condName) {
		if(this.cid==null)
			this.cid = new QueryConditionId();
		this.cid.setCondName(condName);
	}

	// Property accessors
  
	public String getCondDefault() {
        return condDefault;
    }

    public void setCondDefault(String condDefault) {
        this.condDefault = condDefault;
    }

    public String getCondLabel() {
		return this.condLabel;
	}
	
	public void setCondLabel(String condLabel) {
		this.condLabel = condLabel;
	}
  
	public String getCondType() {
		return this.condType;
	}
	
	public void setCondType(String condType) {
		this.condType = condType;
	}
	/**
    * N:普通 nomal H 隐藏 hide R 只读 readonly
    * @return
    */
	public String getCondStyle() {
		return this.condStyle;
	}
	/**
	 * N:普通 nomal H 隐藏 hide R 只读 readonly
	 * @param condStyle
	 */
	public void setCondStyle(String condStyle) {
		this.condStyle = condStyle;
	}

	public String getCondValue() {
        return this.condValue;
    }
    
    public void setCondValue(String condValue) {
        this.condValue = condValue;
    }
    public Integer getCondOrder() {
        return condOrder;
    }
    public void setCondOrder(Integer condOrder) {
        this.condOrder = condOrder;
    }
    public String getCondPlace() {
        return condPlace;
    }
    public void setCondPlace(String condPlace) {
        this.condPlace = condPlace;
    }
    public String getCondFilterSql() {
        return condFilterSql;
    }
    public void setCondFilterSql(String condFilterSql) {
        this.condFilterSql = condFilterSql;
    }
    
	public QueryCondition copy(QueryCondition other){
  
		this.setModelName(other.getModelName());  
		this.setCondName(other.getCondName());
  
		this.condLabel= other.getCondLabel();  
		this.condType= other.getCondType();  
		this.condStyle= other.getCondStyle();
		this.condValue = other.getCondValue();
		this.condOrder = other.getCondOrder();
		this.condPlace = other.getCondPlace();
		this.condFilterSql = other.getCondFilterSql();
		this.compareType= other.getCompareType(); 
		this.condDefault = other.getCondDefault();
		return this;
	}
	
	public QueryCondition copyNotNullProperty(QueryCondition other){
  
    	if( other.getModelName() != null)
    		this.setModelName(other.getModelName());  
    	if( other.getCondName() != null)
    		this.setCondName(other.getCondName());
  
		if( other.getCondLabel() != null)
			this.condLabel= other.getCondLabel();  
		if( other.getCondType() != null)
			this.condType= other.getCondType();  
        if( other.getCompareType() != null)
            this.compareType= other.getCompareType();  
		if( other.getCondStyle() != null)
			this.condStyle= other.getCondStyle();
		if( other.getCondValue() != null)
            this.condValue= other.getCondValue();
        if (other.getCondOrder()!=null)
            this.condOrder = other.getCondOrder();
        if (other.getCondPlace()!=null)
            this.condPlace = other.getCondPlace();
        if (other.getCondFilterSql()!=null)
            this.condFilterSql = other.getCondFilterSql();
        if (other.getCondDefault()!=null)
            this.condDefault = other.getCondDefault();
        return this;
	}
	
	public QueryCondition clearProperties(){
  
		this.condLabel= null;  
		this.condType= null;  
		this.condStyle= "N";
		this.condValue= null;
		this.condOrder = null;
		this.compareType= "0";  
		this.condPlace = null;
		this.condFilterSql = null;
		this.condDefault = null;
		return this;
	}
	/**
	 * 必需是时间字段， 3 同比分析  4 环比分析 0 其他
	 * @return
	 */
    public String getCompareType() {
        return compareType;
    }
    /**
     * 必需是时间字段， 3 同比分析  4 环比分析 0 其他
     * @param compareType
     */
    public void setCompareType(String compareType) {
        this.compareType = compareType;
    }
    
    
    public JSONObject toJsonData()
    {
        JSONObject objJson = new JSONObject();
        JSONOpt.setAttribute(objJson, "condName", this.getCondName());
        JSONOpt.setAttribute(objJson, "condLabel", condLabel);
        JSONOpt.setAttribute(objJson, "condType", condType);
        JSONOpt.setAttribute(objJson, "condValue", condValue);
        JSONOpt.setAttribute(objJson, "condStyle", condStyle);
        JSONOpt.setAttribute(objJson, "condPlace", condPlace);
        JSONOpt.setAttribute(objJson, "connFilterSql", condFilterSql);
        JSONOpt.setAttribute(objJson, "condDefault", condDefault);
        return objJson;
    }  
}
