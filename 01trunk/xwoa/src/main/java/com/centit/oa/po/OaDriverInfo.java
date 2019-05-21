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

public class OaDriverInfo implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

/**
 * 流水号
 */
	private String no;

	private String  codenum;//编号
	private String  name;//姓名
	private String  drLicenseNo;//驾驶证号
	private Date  releaseDate;//发证日期
	private Date  validDate;//证件有效期
	private String  publicType;//准驾车型
	private Long  beenDriving;//驾龄
	private String  telephone;//联系电话
	private Date  birthDate;//出生日期
	private Long  age;//年龄
	private String  sex;//性别
	private String  mobile;//手机
	private String  address;//家庭住址
	private String  depno;//单位
	private String  workExperience;//工作经验
	private byte[]  personalPhoto;//个人照片
	private String  photoname;//照片名称
	private String  remark;//备注
	private String  creater;//创建人
	private Date  creatertime;//创建日期
	private String  state;//启用状态
	private Set<OaCarinfo> oaCarinfos = null;// new ArrayList<OaCarinfo>();
	
	

	// Constructors
	/** default constructor */
	public OaDriverInfo() {
	}
	/** minimal constructor */
	public OaDriverInfo(
		String no		
		) {
	
	
		this.no = no;		
			
	}

/** full constructor */
	public OaDriverInfo(
	 String no		
	,String  codenum,String  name,String  drLicenseNo,Date  releaseDate,Date  validDate,String  publicType,Long  beenDriving,String  telephone,Date  birthDate,Long  age,String  sex,String  mobile,String  address,String  depno,String  workExperience,byte[]  personalPhoto,String  photoname,String  remark,String  creater,Date  creatertime,String  state) {
	
	
		this.no = no;		
	
		this.codenum= codenum;
		this.name= name;
		this.drLicenseNo= drLicenseNo;
		this.releaseDate= releaseDate;
		this.validDate= validDate;
		this.publicType= publicType;
		this.beenDriving= beenDriving;
		this.telephone= telephone;
		this.birthDate= birthDate;
		this.age= age;
		this.sex= sex;
		this.mobile= mobile;
		this.address= address;
		this.depno= depno;
		this.workExperience= workExperience;
		this.personalPhoto= personalPhoto;
		this.photoname= photoname;
		this.remark= remark;
		this.creater= creater;
		this.creatertime= creatertime;
		this.state= state;		
	}
	

  
	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	// Property accessors
  
	public String getCodenum() {
		return this.codenum;
	}
	
	public void setCodenum(String codenum) {
		this.codenum = codenum;
	}
  
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
  
	public String getDrLicenseNo() {
		return this.drLicenseNo;
	}
	
	public void setDrLicenseNo(String drLicenseNo) {
		this.drLicenseNo = drLicenseNo;
	}
  
	public Date getReleaseDate() {
		return this.releaseDate;
	}
	
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
  
	public Date getValidDate() {
		return this.validDate;
	}
	
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
  
	public String getPublicType() {
		return this.publicType;
	}
	
	public void setPublicType(String publicType) {
		this.publicType = publicType;
	}
  
	public Long getBeenDriving() {
		return this.beenDriving;
	}
	
	public void setBeenDriving(Long beenDriving) {
		this.beenDriving = beenDriving;
	}
  
	public String getTelephone() {
		return this.telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
  
	public Date getBirthDate() {
		return this.birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
  
	public Long getAge() {
		return this.age;
	}
	
	public void setAge(Long age) {
		this.age = age;
	}
  
	public String getSex() {
		return this.sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
  
	public String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
  
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
  
	public String getDepno() {
		return this.depno;
	}
	
	public void setDepno(String depno) {
		this.depno = depno;
	}
  
	public String getWorkExperience() {
		return this.workExperience;
	}
	
	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}
  
	public byte[] getPersonalPhoto() {
		return this.personalPhoto;
	}
	
	public void setPersonalPhoto(byte[] personalPhoto) {
		this.personalPhoto = personalPhoto;
	}
  
	public String getPhotoname() {
		return this.photoname;
	}
	
	public void setPhotoname(String photoname) {
		this.photoname = photoname;
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
  
	public Date getCreatertime() {
		return this.creatertime;
	}
	
	public void setCreatertime(Date creatertime) {
		this.creatertime = creatertime;
	}
  
	public String getState() {
		return this.state;
	}
	
	public void setState(String state) {
		this.state = state;
	}


	public Set<OaCarinfo> getOaCarinfos(){
		if(this.oaCarinfos==null)
			this.oaCarinfos = new HashSet<OaCarinfo>();
		return this.oaCarinfos;
	}

	public void setOaCarinfos(Set<OaCarinfo> oaCarinfos) {
		this.oaCarinfos = oaCarinfos;
	}	

	public void addOaCarinfo(OaCarinfo oaCarinfo ){
		if (this.oaCarinfos==null)
			this.oaCarinfos = new HashSet<OaCarinfo>();
		this.oaCarinfos.add(oaCarinfo);
	}
	
	public void removeOaCarinfo(OaCarinfo oaCarinfo ){
		if (this.oaCarinfos==null)
			return;
		this.oaCarinfos.remove(oaCarinfo);
	}
	
	public OaCarinfo newOaCarinfo(){
		OaCarinfo res = new OaCarinfo();
  
		res.setDriver(this.getNo());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不�?��的问�?
	 * 
	 */
	public void replaceOaCarinfos(List<OaCarinfo> oaCarinfos) {
		List<OaCarinfo> newObjs = new ArrayList<OaCarinfo>();
		for(OaCarinfo p :oaCarinfos){
			if(p==null)
				continue;
			OaCarinfo newdt = newOaCarinfo();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<OaCarinfo> oldObjs = new HashSet<OaCarinfo>();
		oldObjs.addAll(getOaCarinfos());
		
		for(Iterator<OaCarinfo> it=oldObjs.iterator(); it.hasNext();){
			OaCarinfo odt = it.next();
			found = false;
			for(OaCarinfo newdt :newObjs){
				if(odt.getDjid().equals( newdt.getDjid())){
					found = true;
					break;
				}
			}
			if(! found)
				removeOaCarinfo(odt);
		}
		oldObjs.clear();
		//insert or update
		for(OaCarinfo newdt :newObjs){
			found = false;
			for(Iterator<OaCarinfo> it=getOaCarinfos().iterator();
			 it.hasNext();){
				OaCarinfo odt = it.next();
				if(odt.getDjid().equals( newdt.getDjid())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addOaCarinfo(newdt);
		} 	
	}	


	public void copy(OaDriverInfo other){
  
		this.setNo(other.getNo());
  
		this.codenum= other.getCodenum();  
		this.name= other.getName();  
		this.drLicenseNo= other.getDrLicenseNo();  
		this.releaseDate= other.getReleaseDate();  
		this.validDate= other.getValidDate();  
		this.publicType= other.getPublicType();  
		this.beenDriving= other.getBeenDriving();  
		this.telephone= other.getTelephone();  
		this.birthDate= other.getBirthDate();  
		this.age= other.getAge();  
		this.sex= other.getSex();  
		this.mobile= other.getMobile();  
		this.address= other.getAddress();  
		this.depno= other.getDepno();  
		this.workExperience= other.getWorkExperience();  
		this.personalPhoto= other.getPersonalPhoto();  
		this.photoname= other.getPhotoname();  
		this.remark= other.getRemark();  
		this.creater= other.getCreater();  
		this.creatertime= other.getCreatertime();  
		this.state= other.getState();
	
		this.oaCarinfos = other.getOaCarinfos();
	}
	
	public void copyNotNullProperty(OaDriverInfo other){
  
	if( other.getNo() != null)
		this.setNo(other.getNo());
  
		if( other.getCodenum() != null)
			this.codenum= other.getCodenum();  
		if( other.getName() != null)
			this.name= other.getName();  
		if( other.getDrLicenseNo() != null)
			this.drLicenseNo= other.getDrLicenseNo();  
		if( other.getReleaseDate() != null)
			this.releaseDate= other.getReleaseDate();  
		if( other.getValidDate() != null)
			this.validDate= other.getValidDate();  
		if( other.getPublicType() != null)
			this.publicType= other.getPublicType();  
		if( other.getBeenDriving() != null)
			this.beenDriving= other.getBeenDriving();  
		if( other.getTelephone() != null)
			this.telephone= other.getTelephone();  
		if( other.getBirthDate() != null)
			this.birthDate= other.getBirthDate();  
		if( other.getAge() != null)
			this.age= other.getAge();  
		if( other.getSex() != null)
			this.sex= other.getSex();  
		if( other.getMobile() != null)
			this.mobile= other.getMobile();  
		if( other.getAddress() != null)
			this.address= other.getAddress();  
		if( other.getDepno() != null)
			this.depno= other.getDepno();  
		if( other.getWorkExperience() != null)
			this.workExperience= other.getWorkExperience();  
		if( other.getPersonalPhoto() != null)
			this.personalPhoto= other.getPersonalPhoto();  
		if( other.getPhotoname() != null)
			this.photoname= other.getPhotoname();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatertime() != null)
			this.creatertime= other.getCreatertime();  
		if( other.getState() != null)
			this.state= other.getState();
	
		this.oaCarinfos = other.getOaCarinfos();
	}
	
	public void clearProperties(){
  
		this.codenum= null;  
		this.name= null;  
		this.drLicenseNo= null;  
		this.releaseDate= null;  
		this.validDate= null;  
		this.publicType= null;  
		this.beenDriving= null;  
		this.telephone= null;  
		this.birthDate= null;  
		this.age= null;  
		this.sex= null;  
		this.mobile= null;  
		this.address= null;  
		this.depno= null;  
		this.workExperience= null;  
		this.personalPhoto= null;  
		this.photoname= null;  
		this.remark= null;  
		this.creater= null;  
		this.creatertime= null;  
		this.state= null;
	
		this.oaCarinfos = new HashSet<OaCarinfo>();
	}
   
}
