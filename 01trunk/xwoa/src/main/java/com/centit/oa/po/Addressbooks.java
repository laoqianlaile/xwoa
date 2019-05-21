package com.centit.oa.po;

import java.util.Date;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class Addressbooks implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String addrbookid;//通讯主体ID
	private String no;//导出编号：递增

	private String  isshare;//是否共享
	private String  userName;//名称
	private String  sex;//性别
	private String  belond;//分组
	private String  bodyType;//通讯主体类别
	private String  country;//国家
	private String  province;//省份
	private String  city;//城市
	private String  unitName;//公司名称
	private String  deptName;//部门名称
	private String  rankName;//职位
	private String  profession;//行业
	private String  mobilephone;//手机
	private String  telphone;//固定电话
	private String  email;//Email
	private String  otherphone;//其他联系方式
	private String  remark;//备注
	private String  creater;//所有者
	private Date  createdate;//创建时间
	private String  type;//类型 个人person ：P、企业company:C、机关单位organ:O
//	private String bodycode;//C:addressbookid,O unitcode
	
	private String unitcode;//公共通讯录与机构关联字段
	
	
	private String shareNames;//共享人員名稱
	
	private Set<AddressBookRelection> addressBookRelections = null;// new ArrayList<AddressBookRelection>();

	private String shareUserCode;
	
	// Constructors
	/** default constructor */
	public Addressbooks() {
	}
	/** minimal constructor */
	public Addressbooks(
		String addrbookid		
		) {
	
	
		this.addrbookid = addrbookid;		
			
	}

/** full constructor */
	public Addressbooks(
	 String addrbookid		
	,String  isshare,String  userName,String  sex,String  belond,String  bodyType,String  country,String  province,String  city,String  unitName,String  deptName,String  rankName,String  profession,String  mobilephone,String  telphone,String  email,String  otherphone,String  remark,String  creater,Date  createdate,String type,String unitcode) {
	
	
		this.addrbookid = addrbookid;		
	
		this.isshare= isshare;
		this.userName= userName;
		this.sex= sex;
		this.belond= belond;
		this.bodyType= bodyType;
		this.country= country;
		this.province= province;
		this.city= city;
		this.unitName= unitName;
		this.deptName= deptName;
		this.rankName= rankName;
		this.profession= profession;
		this.mobilephone= mobilephone;
		this.telphone= telphone;
		this.email= email;
		this.otherphone= otherphone;
		this.remark= remark;
		this.creater= creater;
		this.createdate= createdate;
		this.unitcode= unitcode;
		this.setType(type);
	}
	

  
	public String getAddrbookid() {
		return this.addrbookid;
	}

	public void setAddrbookid(String addrbookid) {
		this.addrbookid = addrbookid;
	}
	// Property accessors
  
	public String getIsshare() {
		return this.isshare;
	}
	
	public void setIsshare(String isshare) {
		this.isshare = isshare;
	}
  
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
  
	public String getSex() {
		return this.sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
  
	public String getBelond() {
		return this.belond;
	}
	
	public void setBelond(String belond) {
		this.belond = belond;
	}
  
	public String getBodyType() {
		return this.bodyType;
	}
	
	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}
  
	public String getCountry() {
		return this.country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
  
	public String getProvince() {
		return this.province;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
  
	public String getCity() {
		return this.city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
  
	public String getUnitName() {
		return this.unitName;
	}
	
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
  
	public String getDeptName() {
		return this.deptName;
	}
	
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
  
	public String getRankName() {
		return this.rankName;
	}
	
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
  
	public String getProfession() {
		return this.profession;
	}
	
	public void setProfession(String profession) {
		this.profession = profession;
	}
  
	public String getMobilephone() {
		return this.mobilephone;
	}
	
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
  
	public String getTelphone() {
		return this.telphone;
	}
	
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
  
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
  
	public String getOtherphone() {
		return this.otherphone;
	}
	
	public void setOtherphone(String otherphone) {
		this.otherphone = otherphone;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
  
	public String getCreater() {
		return this.creater;
	}
	
	public void setCreater(String creater) {
		this.creater = creater;
	}
  
	public Date getCreatedate() {
		return this.createdate;
	}
	
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}


	public Set<AddressBookRelection> getAddressBookRelections(){
		if(this.addressBookRelections==null)
			this.addressBookRelections = new HashSet<AddressBookRelection>();
		return this.addressBookRelections;
	}

	public void setAddressBookRelections(Set<AddressBookRelection> addressBookRelections) {
		this.addressBookRelections = addressBookRelections;
	}	

	public void addAddressBookRelection(AddressBookRelection addressBookRelection ){
		if (this.addressBookRelections==null)
			this.addressBookRelections = new HashSet<AddressBookRelection>();
		this.addressBookRelections.add(addressBookRelection);
	}
	
	public void removeAddressBookRelection(AddressBookRelection addressBookRelection ){
		if (this.addressBookRelections==null)
			return;
		this.addressBookRelections.remove(addressBookRelection);
	}
	
	public AddressBookRelection newAddressBookRelection(){
		AddressBookRelection res = new AddressBookRelection();
  
		res.setAddrbookid(this.getAddrbookid());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不�?��的问�?
	 * 
	 */
	public void replaceAddressBookRelections(List<AddressBookRelection> addressBookRelections) {
		List<AddressBookRelection> newObjs = new ArrayList<AddressBookRelection>();
		for(AddressBookRelection p :addressBookRelections){
			if(p==null)
				continue;
			AddressBookRelection newdt = newAddressBookRelection();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<AddressBookRelection> oldObjs = new HashSet<AddressBookRelection>();
		oldObjs.addAll(getAddressBookRelections());
		
		for(Iterator<AddressBookRelection> it=oldObjs.iterator(); it.hasNext();){
			AddressBookRelection odt = it.next();
			found = false;
			for(AddressBookRelection newdt :newObjs){
				if(odt.getCid().equals( newdt.getCid())){
					found = true;
					break;
				}
			}
			if(! found)
				removeAddressBookRelection(odt);
		}
		oldObjs.clear();
		//insert or update
		for(AddressBookRelection newdt :newObjs){
			found = false;
			for(Iterator<AddressBookRelection> it=getAddressBookRelections().iterator();
			 it.hasNext();){
				AddressBookRelection odt = it.next();
				if(odt.getCid().equals( newdt.getCid())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addAddressBookRelection(newdt);
		} 	
	}	


	public void copy(Addressbooks other){
  
		this.setAddrbookid(other.getAddrbookid());
  
		this.isshare= other.getIsshare();  
		this.userName= other.getUserName();  
		this.sex= other.getSex();  
		this.belond= other.getBelond();  
		this.bodyType= other.getBodyType();  
		this.country= other.getCountry();  
		this.province= other.getProvince();  
		this.city= other.getCity();  
		this.unitName= other.getUnitName();  
		this.deptName= other.getDeptName();  
		this.rankName= other.getRankName();  
		this.profession= other.getProfession();  
		this.mobilephone= other.getMobilephone();  
		this.telphone= other.getTelphone();  
		this.email= other.getEmail();  
		this.otherphone= other.getOtherphone();  
		this.remark= other.getRemark();  
		this.creater= other.getCreater();  
		this.createdate= other.getCreatedate();
		this.shareNames= other.getShareNames();
		this.type= other.getType();
		this.unitcode= other.getUnitcode();
		
		this.addressBookRelections = other.getAddressBookRelections();
	}
	
	public void copyNotNullProperty(Addressbooks other){
  
	if( other.getAddrbookid() != null)
		this.setAddrbookid(other.getAddrbookid());
  
//		if( other.getIsshare() != null)
		this.isshare= other.getIsshare();  
		if( other.getUserName() != null)
			this.userName= other.getUserName();  
		if( other.getSex() != null)
			this.sex= other.getSex();  
		if( other.getBelond() != null)
			this.belond= other.getBelond();  
		if( other.getBodyType() != null)
			this.bodyType= other.getBodyType();  
		if( other.getCountry() != null)
			this.country= other.getCountry();  
		if( other.getProvince() != null)
			this.province= other.getProvince();  
		if( other.getCity() != null)
			this.city= other.getCity();  
		if( other.getUnitName() != null)
			this.unitName= other.getUnitName();  
		if( other.getDeptName() != null)
			this.deptName= other.getDeptName();  
		if( other.getRankName() != null)
			this.rankName= other.getRankName();  
		if( other.getProfession() != null)
			this.profession= other.getProfession();  
		if( other.getMobilephone() != null)
			this.mobilephone= other.getMobilephone();  
		if( other.getTelphone() != null)
			this.telphone= other.getTelphone();  
		if( other.getEmail() != null)
			this.email= other.getEmail();  
		if( other.getOtherphone() != null)
			this.otherphone= other.getOtherphone();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatedate() != null)
			this.createdate= other.getCreatedate();
		if( other.getShareNames() != null)
            this.shareNames= other.getShareNames();
		if( other.getShareUserCode() != null)
            this.shareUserCode= other.getShareUserCode();
		if( other.getType() != null)
            this.type= other.getType();  
		if( other.getUnitcode() != null)
		this.unitcode= other.getUnitcode();
		
		
		
//		this.addressBookRelections = other.getAddressBookRelections();
	}
	
	public void clearProperties(){
  
		this.isshare= null;  
		this.userName= null;  
		this.sex= null;  
		this.belond= null;  
		this.bodyType= null;  
		this.country= null;  
		this.province= null;  
		this.city= null;  
		this.unitName= null;  
		this.deptName= null;  
		this.rankName= null;  
		this.profession= null;  
		this.mobilephone= null;  
		this.telphone= null;  
		this.email= null;  
		this.otherphone= null;  
		this.remark= null;  
		this.creater= null;  
		this.createdate= null;
		//this.type= null;
		this.unitcode=null;
	
		this.addressBookRelections = new HashSet<AddressBookRelection>();
	}
    public String getShareUserCode() {
        return shareUserCode;
    }
    public void setShareUserCode(String shareUserCode) {
        this.shareUserCode = shareUserCode;
    }
   
    public String getShareNames() {
        return shareNames;
    }
    public void setShareNames(String shareNames) {
        this.shareNames = shareNames;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
//    public String getBodycode() {
//        return bodycode;
//    }
//    public void setBodycode(String bodycode) {
//        this.bodycode = bodycode;
//    }
    public String getUnitcode() {
        return unitcode;
    }
    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }
    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }
}
