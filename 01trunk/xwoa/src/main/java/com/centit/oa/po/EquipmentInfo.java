package com.centit.oa.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.centit.app.po.ColorAndValue;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class EquipmentInfo implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private Long equipmentId;//固定资产编号

	private String  equipmentCode;//资产编码
	private String  equipmentName;//资产名称
	private String  equipmentType;//资产类别 
	private String  equipmentState;//资产状态 T 在用 F 注销
	private String  equipmentDesc;//资产其他说明
	private Date  inuseTime;//投入使用时间
	private String  equipmentCharge;//资产负责人
	private Double  equipmentPrice;//资产初始价值
	private Double  yearlyDepreciation;//年折旧比率
	private String  recorder;//录入人员
	private Date  recordeDate;//录入时间
	private Date  discardDate;//注销时间
	private String  discardType;//注销类别
	private String  discardReason;//注销原因
	private String  discardOperator;//注销经办人
	
	public Date getDiscardDate() {
        return discardDate;
    }
    public void setDiscardDate(Date discardDate) {
        this.discardDate = discardDate;
    }
    public String getDiscardType() {
        return discardType;
    }
    public void setDiscardType(String discardType) {
        this.discardType = discardType;
    }
    public String getDiscardReason() {
        return discardReason;
    }
    public void setDiscardReason(String discardReason) {
        this.discardReason = discardReason;
    }
    public String getDiscardOperator() {
        return discardOperator;
    }
    public void setDiscardOperator(String discardOperator) {
        this.discardOperator = discardOperator;
    }
    private Set<EquipmentUsing> equipmentUsings = null;// new ArrayList<EquipmentUsing>();
    private List<ColorAndValue> chartList=new ArrayList<ColorAndValue>();
	// Constructors
	/** default constructor */
	public EquipmentInfo() {
	}
	/** minimal constructor */
	public EquipmentInfo(
		Long equipmentId		
		) {
	
	
		this.equipmentId = equipmentId;		
			
	}

/** full constructor */
	public EquipmentInfo(
	 Long equipmentId		
	,String  equipmentCode,String  equipmentName,String  equipmentType,String  equipmentState,
	String  equipmentDesc,Date  inuseTime,String  equipmentCharge,Double  equipmentPrice,Double  yearlyDepreciation,
	String  recorder,Date  recordeDate, Date discardDate ,String  discardType ,String discardReason , String  discardOperator) {
	
	
		this.equipmentId = equipmentId;		
	
		this.equipmentCode= equipmentCode;
		this.equipmentName= equipmentName;
		this.equipmentType= equipmentType;
		this.equipmentState= equipmentState;
		this.equipmentDesc= equipmentDesc;
		this.inuseTime= inuseTime;
		this.equipmentCharge= equipmentCharge;
		this.equipmentPrice= equipmentPrice;
		this.yearlyDepreciation= yearlyDepreciation;
		this.recorder= recorder;
		this.recordeDate= recordeDate;		
		this.discardDate= discardDate;
		this.discardType= discardType;
		this.discardReason= discardReason;
		this.discardOperator=discardOperator;
	}
	

  
	public Long getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(Long equipmentId) {
		this.equipmentId = equipmentId;
	}
	// Property accessors
  
	public String getEquipmentCode() {
		return this.equipmentCode;
	}
	
	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}
  
	public String getEquipmentName() {
        return this.equipmentName;
    }
    
    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
  
	public String getEquipmentType() {
		return this.equipmentType;
	}
	
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
  
	public String getEquipmentState() {
		return this.equipmentState;
	}
	
	public void setEquipmentState(String equipmentState) {
		this.equipmentState = equipmentState;
	}
  
	public String getEquipmentDesc() {
		return this.equipmentDesc;
	}  
	
	public void setEquipmentDesc(String equipmentDesc) {
		this.equipmentDesc = equipmentDesc;
	}
  
	public Date getInuseTime() {
		return this.inuseTime;
	}
	
	public void setInuseTime(Date inuseTime) {
		this.inuseTime = inuseTime;
	}
  
	public String getEquipmentCharge() {
		return this.equipmentCharge;
	}
	
	public void setEquipmentCharge(String equipmentCharge) {
		this.equipmentCharge = equipmentCharge;
	}
  
	public Double getEquipmentPrice() {
		return this.equipmentPrice;
	}
	
	public void setEquipmentPrice(Double equipmentPrice) {
		this.equipmentPrice = equipmentPrice;
	}
  
	public Double getYearlyDepreciation() {
		return this.yearlyDepreciation;
	}
	
	public void setYearlyDepreciation(Double yearlyDepreciation) {
		this.yearlyDepreciation = yearlyDepreciation;
	}
  
	public String getRecorder() {
		return this.recorder;
	}
	
	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}
  
	public Date getRecordeDate() {
		return this.recordeDate;
	}
	
	public void setRecordeDate(Date recordeDate) {
		this.recordeDate = recordeDate;
	}


	public Set<EquipmentUsing> getEquipmentUsings(){
		if(this.equipmentUsings==null)
			this.equipmentUsings = new HashSet<EquipmentUsing>();
		return this.equipmentUsings;
	}

	public void setEquipmentUsings(Set<EquipmentUsing> equipmentUsings) {
		this.equipmentUsings = equipmentUsings;
	}	

	public void addEquipmentUsing(EquipmentUsing equipmentUsing ){
		if (this.equipmentUsings==null)
			this.equipmentUsings = new HashSet<EquipmentUsing>();
		this.equipmentUsings.add(equipmentUsing);
	}
	
	public void removeEquipmentUsing(EquipmentUsing equipmentUsing ){
		if (this.equipmentUsings==null)
			return;
		this.equipmentUsings.remove(equipmentUsing);
	}
	
	public EquipmentUsing newEquipmentUsing(){
		EquipmentUsing res = new EquipmentUsing();
  
		res.setEquipmentId(this.getEquipmentId());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
	 * 
	 */
	public void replaceEquipmentUsings(List<EquipmentUsing> equipmentUsings) {
		List<EquipmentUsing> newObjs = new ArrayList<EquipmentUsing>();
		for(EquipmentUsing p :equipmentUsings){
			if(p==null)
				continue;
			EquipmentUsing newdt = newEquipmentUsing();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<EquipmentUsing> oldObjs = new HashSet<EquipmentUsing>();
		oldObjs.addAll(getEquipmentUsings());
		
		for(Iterator<EquipmentUsing> it=oldObjs.iterator(); it.hasNext();){
			EquipmentUsing odt = it.next();
			found = false;
			for(EquipmentUsing newdt :newObjs){
				if(odt.getUseRequestId().equals( newdt.getUseRequestId())){
					found = true;
					break;
				}
			}
			if(! found)
				removeEquipmentUsing(odt);
		}
		oldObjs.clear();
		//insert or update
		for(EquipmentUsing newdt :newObjs){
			found = false;
			for(Iterator<EquipmentUsing> it=getEquipmentUsings().iterator();
			 it.hasNext();){
				EquipmentUsing odt = it.next();
				if(odt.getUseRequestId().equals( newdt.getUseRequestId())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addEquipmentUsing(newdt);
		} 	
	}	


	public void copy(EquipmentInfo other){
  
		this.setEquipmentId(other.getEquipmentId());
  
		this.equipmentCode= other.getEquipmentCode();  
		this.equipmentName= other.getEquipmentName();  
		this.equipmentType= other.getEquipmentType();  
		this.equipmentState= other.getEquipmentState();  
		this.equipmentDesc= other.getEquipmentDesc();  
		this.inuseTime= other.getInuseTime();  
		this.equipmentCharge= other.getEquipmentCharge();  
		this.equipmentPrice= other.getEquipmentPrice();  
		this.yearlyDepreciation= other.getYearlyDepreciation();  
		this.recorder= other.getRecorder();  
		this.recordeDate= other.getRecordeDate();
		this.discardDate= other.getDiscardDate();
		this.discardType= other.getDiscardType();
		this.discardReason= other.getDiscardReason();
		this.discardOperator= other.getDiscardOperator();
		
		this.equipmentUsings = other.getEquipmentUsings();
	}
	
	public void copyNotNullProperty(EquipmentInfo other){
  
	if( other.getEquipmentId() != null)
		this.setEquipmentId(other.getEquipmentId());
  
		if( other.getEquipmentCode() != null)
			this.equipmentCode= other.getEquipmentCode();  
		if( other.getEquipmentName() != null)
			this.equipmentName= other.getEquipmentName();  
		if( other.getEquipmentType() != null)
			this.equipmentType= other.getEquipmentType();
		if( other.getEquipmentState() != null)
			this.equipmentState= other.getEquipmentState();  
		if( other.getEquipmentDesc() != null)
			this.equipmentDesc= other.getEquipmentDesc();  
		if( other.getInuseTime() != null)
			this.inuseTime= other.getInuseTime();  
		if( other.getEquipmentCharge() != null)
			this.equipmentCharge= other.getEquipmentCharge();  
		if( other.getEquipmentPrice() != null)
			this.equipmentPrice= other.getEquipmentPrice();  
		if( other.getYearlyDepreciation() != null)
			this.yearlyDepreciation= other.getYearlyDepreciation();  
		if( other.getRecorder() != null)
			this.recorder= other.getRecorder();  
		if( other.getRecordeDate() != null)
			this.recordeDate= other.getRecordeDate();
		if( other.getDiscardDate() != null)
            this.discardDate= other.getDiscardDate();
		if( other.getDiscardType() != null)
            this.discardType= other.getDiscardType();
		if( other.getDiscardReason()!= null)
            this.discardReason= other.getDiscardReason();
		if( other.getDiscardOperator() != null)
            this.discardOperator= other.getDiscardOperator();
		
		this.equipmentUsings = other.getEquipmentUsings();
	}
	
	public void clearProperties(){
  
		this.equipmentCode= null;  
		this.equipmentName= null;  
		this.equipmentType= null;  
		this.equipmentState= null;  
		this.equipmentDesc= null;  
		this.inuseTime= null;  
		this.equipmentCharge= null;  
		this.equipmentPrice= null;  
		this.yearlyDepreciation= null;  
		this.recorder= null;  
		this.recordeDate= null;
		this.discardDate= null;
		this.discardType= null;
		this.discardReason= null;
		this.discardOperator= null;
		
		this.equipmentUsings = new HashSet<EquipmentUsing>();
	}
    public List<ColorAndValue> getChartList() {
        return chartList;
    }
    public void setChartList(List<ColorAndValue> chartList) {
        this.chartList = chartList;
    }

}
