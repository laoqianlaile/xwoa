package com.centit.oa.po;

import java.util.Date;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.centit.app.po.ColorAndValue;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaMeetinfo implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djid;//流水号

	private String  isuse;//启用标识
	private String  coding;//编号
	private String  meetingName;//名称
	private String  meetingType;//会议室类型
	private String  meetingNumber;//容纳人数
	private String  meetingUseing;//主要用途
	private String  meetingAddress;//地点
	private String  meetingInfloor;//所在楼层
	private String  basicConfiguration;//基础配置
	private String  accessoryEquipment;//附属设备
	private String  remark;//简介
	private String  depno;//所属机构
	private String  responsibleDep;//责任部门
	private String  responsiblePersion;//责任人
	private byte[]  meetinfPicture;//会议室图片
	private String  meetinfPictureName;//图片名称
	private String  creater;//创建人
	private Date  createtime;//创建时间
	private Date  motifytime;//最后修改时间
	private Long  orderno;//排序
	private Set<OaMeetApply> oaMeetApplys = null;// new ArrayList<OaMeetApply>();
	private Set<OaMeetroomApply> oaMeetroomApplys=null;
	 private List<ColorAndValue> chartList=new ArrayList<ColorAndValue>();

    private OaCommonType oaCommonType;

	// Constructors
	/** default constructor */
	public OaMeetinfo() {
	}
	/** minimal constructor */
	public OaMeetinfo(
		String djid		
		) {
	
	
		this.djid = djid;		
			
	}

public OaMeetinfo(String djid, String meetingName) {
        super();
        this.djid = djid;
        this.meetingName = meetingName;
    }
/** full constructor */
	public OaMeetinfo(
	 String djid		
	,String  isuse,String  coding,String  meetingName,String  meetingType,String  meetingNumber,String  meetingUseing,String  meetingAddress,String  meetingInfloor,String  basicConfiguration,String  accessoryEquipment,String  remark,String  depno,String  responsibleDep,String  responsiblePersion,String  meetinfPictureName,String  creater,Date  createtime,Date  motifytime,Long  orderno) {
	
	
		this.djid = djid;		
	
		this.isuse= isuse;
		this.coding= coding;
		this.meetingName= meetingName;
		this.meetingType= meetingType;
		this.meetingNumber= meetingNumber;
		this.meetingUseing= meetingUseing;
		this.meetingAddress= meetingAddress;
		this.meetingInfloor= meetingInfloor;
		this.basicConfiguration= basicConfiguration;
		this.accessoryEquipment= accessoryEquipment;
		this.remark= remark;
		this.depno= depno;
		this.responsibleDep= responsibleDep;
		this.responsiblePersion= responsiblePersion;
		this.meetinfPictureName= meetinfPictureName;
		this.creater= creater;
		this.createtime= createtime;
		this.motifytime= motifytime;
		this.orderno= orderno;		
	}
	/** full constructor */
    public OaMeetinfo(
     String djid        
    ,String  isuse,String  coding,String  meetingName,String  meetingType,String  meetingNumber,String  meetingUseing,String  meetingAddress,String  meetingInfloor,String  basicConfiguration,String  accessoryEquipment,String  remark,String  depno,String  responsibleDep,String  responsiblePersion,byte[]  meetinfPicture,String  meetinfPictureName,String  creater,Date  createtime,Date  motifytime,Long  orderno) {
    
    
        this.djid = djid;       
    
        this.isuse= isuse;
        this.coding= coding;
        this.meetingName= meetingName;
        this.meetingType= meetingType;
        this.meetingNumber= meetingNumber;
        this.meetingUseing= meetingUseing;
        this.meetingAddress= meetingAddress;
        this.meetingInfloor= meetingInfloor;
        this.basicConfiguration= basicConfiguration;
        this.accessoryEquipment= accessoryEquipment;
        this.remark= remark;
        this.depno= depno;
        this.responsibleDep= responsibleDep;
        this.responsiblePersion= responsiblePersion;
        this.meetinfPicture=meetinfPicture;
        this.meetinfPictureName= meetinfPictureName;
        this.creater= creater;
        this.createtime= createtime;
        this.motifytime= motifytime;
        this.orderno= orderno;      
    }

  
	public String getDjid() {
		return this.djid;
	}

	public void setDjid(String djid) {
		this.djid = djid;
	}
	// Property accessors
  
	public String getIsuse() {
		return this.isuse;
	}
	
	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}
  
	public String getCoding() {
		return this.coding;
	}
	
	public void setCoding(String coding) {
		this.coding = coding;
	}
  
	public String getMeetingName() {
		return this.meetingName;
	}
	
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
  
	public String getMeetingType() {
		return this.meetingType;
	}
	
	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}
  
	public String getMeetingNumber() {
		return this.meetingNumber;
	}
	
	public void setMeetingNumber(String meetingNumber) {
		this.meetingNumber = meetingNumber;
	}
  
	public String getMeetingUseing() {
		return this.meetingUseing;
	}
	
	public void setMeetingUseing(String meetingUseing) {
		this.meetingUseing = meetingUseing;
	}
  
	public String getMeetingAddress() {
		return this.meetingAddress;
	}
	
	public void setMeetingAddress(String meetingAddress) {
		this.meetingAddress = meetingAddress;
	}
  
	public String getMeetingInfloor() {
		return this.meetingInfloor;
	}
	
	public void setMeetingInfloor(String meetingInfloor) {
		this.meetingInfloor = meetingInfloor;
	}
  
	public String getBasicConfiguration() {
		return this.basicConfiguration;
	}
	
	public void setBasicConfiguration(String basicConfiguration) {
		this.basicConfiguration = basicConfiguration;
	}
  
	public String getAccessoryEquipment() {
		return this.accessoryEquipment;
	}
	
	public void setAccessoryEquipment(String accessoryEquipment) {
		this.accessoryEquipment = accessoryEquipment;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
  
	public String getDepno() {
		return this.depno;
	}
	
	public void setDepno(String depno) {
		this.depno = depno;
	}
  
	public String getResponsibleDep() {
		return this.responsibleDep;
	}
	
	public void setResponsibleDep(String responsibleDep) {
		this.responsibleDep = responsibleDep;
	}
  
	public String getResponsiblePersion() {
		return this.responsiblePersion;
	}
	
	public void setResponsiblePersion(String responsiblePersion) {
		this.responsiblePersion = responsiblePersion;
	}
  
	public byte[] getMeetinfPicture() {
		return this.meetinfPicture;
	}
	
	public void setMeetinfPicture(byte[] meetinfPicture) {
		this.meetinfPicture = meetinfPicture;
	}
  
	public String getMeetinfPictureName() {
		return this.meetinfPictureName;
	}
	
	public void setMeetinfPictureName(String meetinfPictureName) {
		this.meetinfPictureName = meetinfPictureName;
	}
  
	public String getCreater() {
		return this.creater;
	}
	
	public void setCreater(String creater) {
		this.creater = creater;
	}
  
	public Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
  
	public Date getMotifytime() {
		return this.motifytime;
	}
	
	public void setMotifytime(Date motifytime) {
		this.motifytime = motifytime;
	}
  
	public Long getOrderno() {
		return this.orderno;
	}
	
	public void setOrderno(Long orderno) {
		this.orderno = orderno;
	}


	public Set<OaMeetroomApply> getOaMeetroomApplys(){
		if(this.oaMeetroomApplys==null)
			this.oaMeetroomApplys = new HashSet<OaMeetroomApply>();
		return this.oaMeetroomApplys;
	}

	public void setOaMeetroomApplys(Set<OaMeetroomApply> oaMeetroomApply) {
		this.oaMeetroomApplys = oaMeetroomApply;
	}	

	public void addOaMeetroomApply(OaMeetroomApply oaMeetroomApply ){
		if (this.oaMeetroomApplys==null)
			this.oaMeetroomApplys = new HashSet<OaMeetroomApply>();
		this.oaMeetroomApplys.add(oaMeetroomApply);
	}
	
	public void removeOaMeetroomApply(OaMeetroomApply oaMeetroomApply ){
		if (this.oaMeetroomApplys==null)
			return;
		this.oaMeetroomApplys.remove(oaMeetroomApply);
	}
	
	public OaMeetroomApply newOaMeetroomApply(){
	    OaMeetroomApply res = new OaMeetroomApply();
  
		res.setMeetingNo(this.getDjid());

		return res;
	}
	
	   public Set<OaMeetApply> getOaMeetApplys(){
	        if(this.oaMeetApplys==null)
	            this.oaMeetApplys = new HashSet<OaMeetApply>();
	        return this.oaMeetApplys;
	    }

	    public void setOaMeetApplys(Set<OaMeetApply> oaMeetApplys) {
	        this.oaMeetApplys = oaMeetApplys;
	    }   

	    public void addOaMeetApply(OaMeetApply oaMeetApply ){
	        if (this.oaMeetApplys==null)
	            this.oaMeetApplys = new HashSet<OaMeetApply>();
	        this.oaMeetApplys.add(oaMeetApply);
	    }
	    
	    public void removeOaMeetApply(OaMeetApply oaMeetApply ){
	        if (this.oaMeetApplys==null)
	            return;
	        this.oaMeetApplys.remove(oaMeetApply);
	    }
	    
	    public OaMeetApply newOaMeetApply(){
	        OaMeetApply res = new OaMeetApply();
	  
	        res.setMeetingNo(this.getDjid());

	        return res;
	    }
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态
	 * 
	 */
	public void replaceOaMeetApplys(List<OaMeetApply> oaMeetApplys) {
		List<OaMeetApply> newObjs = new ArrayList<OaMeetApply>();
		for(OaMeetApply p :oaMeetApplys){
			if(p==null)
				continue;
			OaMeetApply newdt = newOaMeetApply();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<OaMeetApply> oldObjs = new HashSet<OaMeetApply>();
		oldObjs.addAll(getOaMeetApplys());
		
		for(Iterator<OaMeetApply> it=oldObjs.iterator(); it.hasNext();){
			OaMeetApply odt = it.next();
			found = false;
			for(OaMeetApply newdt :newObjs){
				if(odt.getDjId().equals( newdt.getDjId())){
					found = true;
					break;
				}
			}
			if(! found)
				removeOaMeetApply(odt);
		}
		oldObjs.clear();
		//insert or update
		for(OaMeetApply newdt :newObjs){
			found = false;
			for(Iterator<OaMeetApply> it=getOaMeetApplys().iterator();
			 it.hasNext();){
				OaMeetApply odt = it.next();
				if(odt.getDjId().equals( newdt.getDjId())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addOaMeetApply(newdt);
		} 	
	}	


	public void copy(OaMeetinfo other){
  
		this.setDjid(other.getDjid());
  
		this.isuse= other.getIsuse();  
		this.coding= other.getCoding();  
		this.meetingName= other.getMeetingName();  
		this.meetingType= other.getMeetingType();  
		this.meetingNumber= other.getMeetingNumber();  
		this.meetingUseing= other.getMeetingUseing();  
		this.meetingAddress= other.getMeetingAddress();  
		this.meetingInfloor= other.getMeetingInfloor();  
		this.basicConfiguration= other.getBasicConfiguration();  
		this.accessoryEquipment= other.getAccessoryEquipment();  
		this.remark= other.getRemark();  
		this.depno= other.getDepno();  
		this.responsibleDep= other.getResponsibleDep();  
		this.responsiblePersion= other.getResponsiblePersion(); 
		//edit()里用到了copy方法，干掉后图片查不出来了
		this.meetinfPicture= other.getMeetinfPicture();  
		this.meetinfPictureName= other.getMeetinfPictureName();  
		this.creater= other.getCreater();  
		this.createtime= other.getCreatetime();  
		this.motifytime= other.getMotifytime();  
		this.orderno= other.getOrderno();
	
		this.oaMeetApplys = other.getOaMeetApplys();
//		this.oaCommonType=other.getOaCommonType();
	}
	
	public void copyNotNullProperty(OaMeetinfo other){
  
	if( other.getDjid() != null)
		this.setDjid(other.getDjid());
  
		if( other.getIsuse() != null)
			this.isuse= other.getIsuse();  
		if( other.getCoding() != null)
			this.coding= other.getCoding();  
		if( other.getMeetingName() != null)
			this.meetingName= other.getMeetingName();  
		if( other.getMeetingType() != null)
			this.meetingType= other.getMeetingType();  
		if( other.getMeetingNumber() != null)
			this.meetingNumber= other.getMeetingNumber();  
		if( other.getMeetingUseing() != null)
			this.meetingUseing= other.getMeetingUseing();  
		if( other.getMeetingAddress() != null)
			this.meetingAddress= other.getMeetingAddress();  
		if( other.getMeetingInfloor() != null)
			this.meetingInfloor= other.getMeetingInfloor();  
		if( other.getBasicConfiguration() != null)
			this.basicConfiguration= other.getBasicConfiguration();  
		if( other.getAccessoryEquipment() != null)
			this.accessoryEquipment= other.getAccessoryEquipment();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getDepno() != null)
			this.depno= other.getDepno();  
		if( other.getResponsibleDep() != null)
			this.responsibleDep= other.getResponsibleDep();  
		if( other.getResponsiblePersion() != null)
			this.responsiblePersion= other.getResponsiblePersion();  
		if( other.getMeetinfPicture() != null)
			this.meetinfPicture= other.getMeetinfPicture();  
		if( other.getMeetinfPictureName() != null)
			this.meetinfPictureName= other.getMeetinfPictureName();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();  
		if( other.getMotifytime() != null)
			this.motifytime= other.getMotifytime();  
		if( other.getOrderno() != null)
			this.orderno= other.getOrderno();
	
		this.oaMeetApplys = other.getOaMeetApplys();
	}
	
	public void clearProperties(){
  
		this.isuse= null;  
		this.coding= null;  
		this.meetingName= null;  
		this.meetingType= null;  
		this.meetingNumber= null;  
		this.meetingUseing= null;  
		this.meetingAddress= null;  
		this.meetingInfloor= null;  
		this.basicConfiguration= null;  
		this.accessoryEquipment= null;  
		this.remark= null;  
		this.depno= null;  
		this.responsibleDep= null;  
		this.responsiblePersion= null;  
		this.meetinfPicture= null;  
		this.meetinfPictureName= null;  
		this.creater= null;  
		this.createtime= null;  
		this.motifytime= null;  
		this.orderno= null;
	
		this.oaMeetApplys = new HashSet<OaMeetApply>();
	}
    public OaCommonType getOaCommonType() {
        return oaCommonType;
    }
    public void setOaCommonType(OaCommonType oaCommonType) {
        this.oaCommonType = oaCommonType;
    }
    public List<ColorAndValue> getChartList() {
        return chartList;
    }
    public void setChartList(List<ColorAndValue> chartList) {
        this.chartList = chartList;
    }
    
}
