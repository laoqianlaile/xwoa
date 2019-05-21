package com.centit.sys.po;

import com.centit.support.utils.StringBaseOpt;


/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 
//通讯录
public class AddressBook implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private Long addrbookid;

	private String  bodytype;
	private String  bodycode;
	private String  representation;
	private String  unitname;
	private String  deptname;
	private String  rankname;
	private String  email;
	private String  email2;
	private String  email3;
	private String  homepage;
	private String  qq;
	private String  msn;
	private String  wangwang;
	private String  buzphone;
	private String  buzphone2;
	private String  buzfax;
	private String  assiphone;
	private String  callbacphone;
	private String  carphone;
	private String  unitphone;
	private String  homephone;
	private String  homephone2;
	private String  homephone3;
	private String  homefax;
	private String  mobilephone;
	private String  mobilephone2;
	private String  mobilephone3;
	private String  unitzip;
	private String  unitprovince;
	private String  unitcity;
	private String  unitdistrict;
	private String  unitstreet;
	private String  unitaddress;
	private String  homezip;
	private String  homeprovince;
	private String  homecity;
	private String  homedistrict;
	private String  homestreet;
	private String  homeaddress;
	private String  home2zip;
	private String  home2province;
	private String  home2city;
	private String  home2district;
	private String  home2street;
	private String  home2address;
	private String  inuseaddress;
	private String  searchstring;
	private String  memo;

	// Constructors
	/** default constructor */
	public AddressBook() {
	}
	/** minimal constructor */
	public AddressBook(
		Long addrbookid		
		,String  bodytype,String  bodycode) {
	
	
		this.addrbookid = addrbookid;		
	
		this.bodytype= bodytype; 
		this.bodycode= bodycode; 		
	}

/** full constructor */
	public AddressBook(
	 Long addrbookid		
	,String  bodytype,String  bodycode,String  representation,String  unitname,String  deptname,String  rankname,String  email,String  email2,String  email3,String  homepage,String  qq,String  msn,String  wangwang,String  buzphone,String  buzphone2,String  buzfax,String  assiphone,String  callbacphone,String  carphone,String  unitphone,String  homephone,String  homephone2,String  homephone3,String  homefax,String  mobilephone,String  mobilephone2,String  mobilephone3,String  unitzip,String  unitprovince,String  unitcity,String  unitdistrict,String  unitstreet,String  unitaddress,String  homezip,String  homeprovince,String  homecity,String  homedistrict,String  homestreet,String  homeaddress,String  home2zip,String  home2province,String  home2city,String  home2district,String  home2street,String  home2address,String  inuseaddress,String  memo) {
	
	
		this.addrbookid = addrbookid;		
	
		this.bodytype= bodytype;
		this.bodycode= bodycode;
		this.representation= representation;
		this.unitname= unitname;
		this.deptname= deptname;
		this.rankname= rankname;
		this.email= email;
		this.email2= email2;
		this.email3= email3;
		this.homepage= homepage;
		this.qq= qq;
		this.msn= msn;
		this.wangwang= wangwang;
		this.buzphone= buzphone;
		this.buzphone2= buzphone2;
		this.buzfax= buzfax;
		this.assiphone= assiphone;
		this.callbacphone= callbacphone;
		this.carphone= carphone;
		this.unitphone= unitphone;
		this.homephone= homephone;
		this.homephone2= homephone2;
		this.homephone3= homephone3;
		this.homefax= homefax;
		this.mobilephone= mobilephone;
		this.mobilephone2= mobilephone2;
		this.mobilephone3= mobilephone3;
		this.unitzip= unitzip;
		this.unitprovince= unitprovince;
		this.unitcity= unitcity;
		this.unitdistrict= unitdistrict;
		this.unitstreet= unitstreet;
		this.unitaddress= unitaddress;
		this.homezip= homezip;
		this.homeprovince= homeprovince;
		this.homecity= homecity;
		this.homedistrict= homedistrict;
		this.homestreet= homestreet;
		this.homeaddress= homeaddress;
		this.home2zip= home2zip;
		this.home2province= home2province;
		this.home2city= home2city;
		this.home2district= home2district;
		this.home2street= home2street;
		this.home2address= home2address;
		this.inuseaddress= inuseaddress;
		this.memo= memo;		
	}
	

  
	public Long getAddrbookid() {
		return this.addrbookid;
	}

	public void setAddrbookid(Long addrbookid) {
		this.addrbookid = addrbookid;
	}
	// Property accessors
  
	public String getBodytype() {
		return this.bodytype;
	}
	
	public void setBodytype(String bodytype) {
		this.bodytype = bodytype;
	}
  
	public String getBodycode() {
		return this.bodycode;
	}
	
	public void setBodycode(String bodycode) {
		this.bodycode = bodycode;
	}
  
	public String getRepresentation() {
		return this.representation;
	}
	
	public void setRepresentation(String representation) {
		this.representation = representation;
	}
  
	public String getUnitname() {
		return this.unitname;
	}
	
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
  
	public String getDeptname() {
		return this.deptname;
	}
	
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
  
	public String getRankname() {
		return this.rankname;
	}
	
	public void setRankname(String rankname) {
		this.rankname = rankname;
	}
  
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
  
	public String getEmail2() {
		return this.email2;
	}
	
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
  
	public String getEmail3() {
		return this.email3;
	}
	
	public void setEmail3(String email3) {
		this.email3 = email3;
	}
  
	public String getHomepage() {
		return this.homepage;
	}
	
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
  
	public String getQq() {
		return this.qq;
	}
	
	public void setQq(String qq) {
		this.qq = qq;
	}
  
	public String getMsn() {
		return this.msn;
	}
	
	public void setMsn(String msn) {
		this.msn = msn;
	}
  
	public String getWangwang() {
		return this.wangwang;
	}
	
	public void setWangwang(String wangwang) {
		this.wangwang = wangwang;
	}
  
	public String getBuzphone() {
		return this.buzphone;
	}
	
	public void setBuzphone(String buzphone) {
		this.buzphone = buzphone;
	}
  
	public String getBuzphone2() {
		return this.buzphone2;
	}
	
	public void setBuzphone2(String buzphone2) {
		this.buzphone2 = buzphone2;
	}
  
	public String getBuzfax() {
		return this.buzfax;
	}
	
	public void setBuzfax(String buzfax) {
		this.buzfax = buzfax;
	}
  
	public String getAssiphone() {
		return this.assiphone;
	}
	
	public void setAssiphone(String assiphone) {
		this.assiphone = assiphone;
	}
  
	public String getCallbacphone() {
		return this.callbacphone;
	}
	
	public void setCallbacphone(String callbacphone) {
		this.callbacphone = callbacphone;
	}
  
	public String getCarphone() {
		return this.carphone;
	}
	
	public void setCarphone(String carphone) {
		this.carphone = carphone;
	}
  
	public String getUnitphone() {
		return this.unitphone;
	}
	
	public void setUnitphone(String unitphone) {
		this.unitphone = unitphone;
	}
  
	public String getHomephone() {
		return this.homephone;
	}
	
	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}
  
	public String getHomephone2() {
		return this.homephone2;
	}
	
	public void setHomephone2(String homephone2) {
		this.homephone2 = homephone2;
	}
  
	public String getHomephone3() {
		return this.homephone3;
	}
	
	public void setHomephone3(String homephone3) {
		this.homephone3 = homephone3;
	}
  
	public String getHomefax() {
		return this.homefax;
	}
	
	public void setHomefax(String homefax) {
		this.homefax = homefax;
	}
  
	public String getMobilephone() {
		return this.mobilephone;
	}
	
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
  
	public String getMobilephone2() {
		return this.mobilephone2;
	}
	
	public void setMobilephone2(String mobilephone2) {
		this.mobilephone2 = mobilephone2;
	}
  
	public String getMobilephone3() {
		return this.mobilephone3;
	}
	
	public void setMobilephone3(String mobilephone3) {
		this.mobilephone3 = mobilephone3;
	}
  
	public String getUnitzip() {
		return this.unitzip;
	}
	
	public void setUnitzip(String unitzip) {
		this.unitzip = unitzip;
	}
  
	public String getUnitprovince() {
		return this.unitprovince;
	}
	
	public void setUnitprovince(String unitprovince) {
		this.unitprovince = unitprovince;
	}
  
	public String getUnitcity() {
		return this.unitcity;
	}
	
	public void setUnitcity(String unitcity) {
		this.unitcity = unitcity;
	}
  
	public String getUnitdistrict() {
		return this.unitdistrict;
	}
	
	public void setUnitdistrict(String unitdistrict) {
		this.unitdistrict = unitdistrict;
	}
  
	public String getUnitstreet() {
		return this.unitstreet;
	}
	
	public void setUnitstreet(String unitstreet) {
		this.unitstreet = unitstreet;
	}
  
	public String getUnitaddress() {
		return this.unitaddress;
	}
	
	public void setUnitaddress(String unitaddress) {
		this.unitaddress = unitaddress;
	}
  
	public String getHomezip() {
		return this.homezip;
	}
	
	public void setHomezip(String homezip) {
		this.homezip = homezip;
	}
  
	public String getHomeprovince() {
		return this.homeprovince;
	}
	
	public void setHomeprovince(String homeprovince) {
		this.homeprovince = homeprovince;
	}
  
	public String getHomecity() {
		return this.homecity;
	}
	
	public void setHomecity(String homecity) {
		this.homecity = homecity;
	}
  
	public String getHomedistrict() {
		return this.homedistrict;
	}
	
	public void setHomedistrict(String homedistrict) {
		this.homedistrict = homedistrict;
	}
  
	public String getHomestreet() {
		return this.homestreet;
	}
	
	public void setHomestreet(String homestreet) {
		this.homestreet = homestreet;
	}
  
	public String getHomeaddress() {
		return this.homeaddress;
	}
	
	public void setHomeaddress(String homeaddress) {
		this.homeaddress = homeaddress;
	}
  
	public String getHome2zip() {
		return this.home2zip;
	}
	
	public void setHome2zip(String home2zip) {
		this.home2zip = home2zip;
	}
  
	public String getHome2province() {
		return this.home2province;
	}
	
	public void setHome2province(String home2province) {
		this.home2province = home2province;
	}
  
	public String getHome2city() {
		return this.home2city;
	}
	
	public void setHome2city(String home2city) {
		this.home2city = home2city;
	}
  
	public String getHome2district() {
		return this.home2district;
	}
	
	public void setHome2district(String home2district) {
		this.home2district = home2district;
	}
  
	public String getHome2street() {
		return this.home2street;
	}
	
	public void setHome2street(String home2street) {
		this.home2street = home2street;
	}
  
	public String getHome2address() {
		return this.home2address;
	}
	
	public void setHome2address(String home2address) {
		this.home2address = home2address;
	}
  
	public String getInuseaddress() {
		return this.inuseaddress;
	}
	
	public void setInuseaddress(String inuseaddress) {
		this.inuseaddress = inuseaddress;
	}
  
	public String getMemo() {
		return this.memo;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getSearchstring() {
		return searchstring;
	}
	
	public void setSearchstring(String searchstring) {
		this.searchstring = searchstring;
	}
	
	public void makeSearchString()
	{
		StringBuilder ss1= new StringBuilder(),
						ss2=new StringBuilder();
		//StringBaseOpt.getFirstLetter(ss1);
		if(this.representation != null){
			ss1.append(this.representation);
			ss2.append(StringBaseOpt.getFirstLetter(this.representation));
		}
		if(this.unitname != null){
			ss1.append(' ').append(this.unitname);
			ss2.append(' ').append(StringBaseOpt.getFirstLetter(this.unitname));
		}
		if(this.deptname != null){
			ss1.append(' ').append(this.deptname); 
			ss2.append(' ').append(StringBaseOpt.getFirstLetter(this.deptname));
		}
		if(this.rankname != null){
			ss1.append(' ').append(this.rankname);
			ss2.append(' ').append(StringBaseOpt.getFirstLetter(this.rankname));
		}
		
		if(this.email != null)
			ss1.append(' ').append(this.email);
		if(this.email2 != null)
			ss1.append(' ').append(this.email2);
		if(this.email3 != null)
			ss1.append(' ').append(this.email3);
		if(this.homepage != null)
			ss1.append(' ').append(this.homepage);
		if(this.qq != null)
			ss1.append(' ').append(this.qq);
		if(this.msn != null)
			ss1.append(' ').append(this.msn);
		if(this.wangwang != null)
			ss1.append(' ').append(this.wangwang);
		if(this.buzphone != null)
			ss1.append(' ').append(this.buzphone);
		if(this.buzphone2 != null)
			ss1.append(' ').append(this.buzphone2);
		if(this.buzfax != null)
			ss1.append(' ').append(this.buzfax);
		if(this.assiphone != null)
			ss1.append(' ').append(this.assiphone);
		if(this.callbacphone != null)
			ss1.append(' ').append(this.callbacphone);
		if(this.carphone != null)
			ss1.append(' ').append(this.carphone);
		if(this.unitphone != null)
			ss1.append(' ').append(this.unitphone);
		if(this.homephone != null)
			ss1.append(' ').append(this.homephone);
		if(this.homephone2 != null)
			ss1.append(' ').append(this.homephone2);
		if(this.homephone3 != null)
			ss1.append(' ').append(this.homephone3);
		if(this.homefax != null)
			ss1.append(' ').append(this.homefax);
		if(this.mobilephone != null)
			ss1.append(' ').append(this.mobilephone);
		if(this.mobilephone2 != null)
			ss1.append(' ').append(this.mobilephone2);
		if(this.mobilephone3 != null)
			ss1.append(' ').append(this.mobilephone3);
		if(this.unitzip != null)
			ss1.append(' ').append(this.unitzip);
		if(this.unitprovince != null)
			ss1.append(' ').append(this.unitprovince);
		if(this.unitcity != null)
			ss1.append(this.unitcity); 
		if(this.unitdistrict != null)
			ss1.append(this.unitdistrict);
		if(this.unitstreet != null)
			ss1.append(this.unitstreet);
		if(this.unitaddress != null)
			ss1.append(this.unitaddress);
		if(this.unitprovince != null)
			ss2.append(' ').append(StringBaseOpt.getFirstLetter(this.unitprovince));
		if(this.unitcity != null)
			ss2.append(StringBaseOpt.getFirstLetter(this.unitcity));
		if(this.unitdistrict != null)
			ss2.append(StringBaseOpt.getFirstLetter(this.unitdistrict));
		if(this.unitstreet != null)
			ss2.append(StringBaseOpt.getFirstLetter(this.unitstreet));
		if(this.unitaddress != null)
			ss2.append(StringBaseOpt.getFirstLetter(this.unitaddress));

		if(this.homezip != null)
			ss1.append(' ').append(this.homezip);
		if(this.homeprovince != null)
			ss1.append(' ').append(this.homeprovince); 
		if(this.homecity != null)
			ss1.append(this.homecity);
		if(this.homedistrict != null)
			ss1.append(this.homedistrict);
		if(this.homestreet != null)
			ss1.append(this.homestreet);
		if(this.homeaddress != null)
			ss1.append(this.homeaddress);
		if(this.homeprovince != null)
			ss2.append(' ').append(StringBaseOpt.getFirstLetter(this.homeprovince));
		if(this.homecity != null)
			ss2.append(StringBaseOpt.getFirstLetter(this.homecity));
		if(this.homedistrict != null)
			ss2.append(StringBaseOpt.getFirstLetter(this.homedistrict));
		if(this.homestreet != null)
			ss2.append(StringBaseOpt.getFirstLetter(this.homestreet));
		if(this.homeaddress != null)
			ss2.append(StringBaseOpt.getFirstLetter(this.homeaddress));
		
		if(this.home2zip != null)
			ss1.append(' ').append(this.home2zip);
		if(this.home2province != null)
			ss1.append(' ').append(this.home2province);
		if(this.home2city != null)
			ss1.append(this.home2city);
		if(this.home2district != null)
			ss1.append(this.home2district);
		if(this.home2street != null)
			ss1.append(this.home2street);
		if(this.home2address != null)
			ss1.append(this.home2address);
		if(this.home2province != null)
			ss2.append(' ').append(StringBaseOpt.getFirstLetter(this.home2province));
		if(this.home2city != null)
			ss2.append(StringBaseOpt.getFirstLetter(this.home2city));
		if(this.home2district != null)
			ss2.append(StringBaseOpt.getFirstLetter(this.home2district));
		if(this.home2street != null)
			ss2.append(StringBaseOpt.getFirstLetter(this.home2street));
		if(this.home2address != null)
			ss2.append(StringBaseOpt.getFirstLetter(this.home2address));
		
		this.searchstring = ss1.toString() + ss2.toString();
	}
	
	public void copy(AddressBook other){
  
		this.bodytype= other.getBodytype();  
		this.bodycode= other.getBodycode();  
		this.representation= other.getRepresentation();  
		this.unitname= other.getUnitname();  
		this.deptname= other.getDeptname();  
		this.rankname= other.getRankname();  
		this.email= other.getEmail();  
		this.email2= other.getEmail2();  
		this.email3= other.getEmail3();  
		this.homepage= other.getHomepage();  
		this.qq= other.getQq();  
		this.msn= other.getMsn();  
		this.wangwang= other.getWangwang();  
		this.buzphone= other.getBuzphone();  
		this.buzphone2= other.getBuzphone2();  
		this.buzfax= other.getBuzfax();  
		this.assiphone= other.getAssiphone();  
		this.callbacphone= other.getCallbacphone();  
		this.carphone= other.getCarphone();  
		this.unitphone= other.getUnitphone();  
		this.homephone= other.getHomephone();  
		this.homephone2= other.getHomephone2();  
		this.homephone3= other.getHomephone3();  
		this.homefax= other.getHomefax();  
		this.mobilephone= other.getMobilephone();  
		this.mobilephone2= other.getMobilephone2();  
		this.mobilephone3= other.getMobilephone3();  
		this.unitzip= other.getUnitzip();  
		this.unitprovince= other.getUnitprovince();  
		this.unitcity= other.getUnitcity();  
		this.unitdistrict= other.getUnitdistrict();  
		this.unitstreet= other.getUnitstreet();  
		this.unitaddress= other.getUnitaddress();  
		this.homezip= other.getHomezip();  
		this.homeprovince= other.getHomeprovince();  
		this.homecity= other.getHomecity();  
		this.homedistrict= other.getHomedistrict();  
		this.homestreet= other.getHomestreet();  
		this.homeaddress= other.getHomeaddress();  
		this.home2zip= other.getHome2zip();  
		this.home2province= other.getHome2province();  
		this.home2city= other.getHome2city();  
		this.home2district= other.getHome2district();  
		this.home2street= other.getHome2street();  
		this.home2address= other.getHome2address();  
		this.inuseaddress= other.getInuseaddress();  
		this.memo= other.getMemo();
	}
	
	public void copyNotNullProperty(AddressBook other){
  
		if( other.getBodytype() != null)
			this.bodytype= other.getBodytype();  
		if( other.getBodycode() != null)
			this.bodycode= other.getBodycode();  
		if( other.getRepresentation() != null)
			this.representation= other.getRepresentation();  
		if( other.getUnitname() != null)
			this.unitname= other.getUnitname();  
		if( other.getDeptname() != null)
			this.deptname= other.getDeptname();  
		if( other.getRankname() != null)
			this.rankname= other.getRankname();  
		if( other.getEmail() != null)
			this.email= other.getEmail();  
		if( other.getEmail2() != null)
			this.email2= other.getEmail2();  
		if( other.getEmail3() != null)
			this.email3= other.getEmail3();  
		if( other.getHomepage() != null)
			this.homepage= other.getHomepage();  
		if( other.getQq() != null)
			this.qq= other.getQq();  
		if( other.getMsn() != null)
			this.msn= other.getMsn();  
		if( other.getWangwang() != null)
			this.wangwang= other.getWangwang();  
		if( other.getBuzphone() != null)
			this.buzphone= other.getBuzphone();  
		if( other.getBuzphone2() != null)
			this.buzphone2= other.getBuzphone2();  
		if( other.getBuzfax() != null)
			this.buzfax= other.getBuzfax();  
		if( other.getAssiphone() != null)
			this.assiphone= other.getAssiphone();  
		if( other.getCallbacphone() != null)
			this.callbacphone= other.getCallbacphone();  
		if( other.getCarphone() != null)
			this.carphone= other.getCarphone();  
		if( other.getUnitphone() != null)
			this.unitphone= other.getUnitphone();  
		if( other.getHomephone() != null)
			this.homephone= other.getHomephone();  
		if( other.getHomephone2() != null)
			this.homephone2= other.getHomephone2();  
		if( other.getHomephone3() != null)
			this.homephone3= other.getHomephone3();  
		if( other.getHomefax() != null)
			this.homefax= other.getHomefax();  
		if( other.getMobilephone() != null)
			this.mobilephone= other.getMobilephone();  
		if( other.getMobilephone2() != null)
			this.mobilephone2= other.getMobilephone2();  
		if( other.getMobilephone3() != null)
			this.mobilephone3= other.getMobilephone3();  
		if( other.getUnitzip() != null)
			this.unitzip= other.getUnitzip();  
		if( other.getUnitprovince() != null)
			this.unitprovince= other.getUnitprovince();  
		if( other.getUnitcity() != null)
			this.unitcity= other.getUnitcity();  
		if( other.getUnitdistrict() != null)
			this.unitdistrict= other.getUnitdistrict();  
		if( other.getUnitstreet() != null)
			this.unitstreet= other.getUnitstreet();  
		if( other.getUnitaddress() != null)
			this.unitaddress= other.getUnitaddress();  
		if( other.getHomezip() != null)
			this.homezip= other.getHomezip();  
		if( other.getHomeprovince() != null)
			this.homeprovince= other.getHomeprovince();  
		if( other.getHomecity() != null)
			this.homecity= other.getHomecity();  
		if( other.getHomedistrict() != null)
			this.homedistrict= other.getHomedistrict();  
		if( other.getHomestreet() != null)
			this.homestreet= other.getHomestreet();  
		if( other.getHomeaddress() != null)
			this.homeaddress= other.getHomeaddress();  
		if( other.getHome2zip() != null)
			this.home2zip= other.getHome2zip();  
		if( other.getHome2province() != null)
			this.home2province= other.getHome2province();  
		if( other.getHome2city() != null)
			this.home2city= other.getHome2city();  
		if( other.getHome2district() != null)
			this.home2district= other.getHome2district();  
		if( other.getHome2street() != null)
			this.home2street= other.getHome2street();  
		if( other.getHome2address() != null)
			this.home2address= other.getHome2address();  
		if( other.getInuseaddress() != null)
			this.inuseaddress= other.getInuseaddress();  
		if( other.getMemo() != null)
			this.memo= other.getMemo();
	}

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("AddressBook");
        sb.append("{addrbookid=").append(addrbookid);
        sb.append(", bodytype='").append(bodytype).append('\'');
        sb.append(", bodycode='").append(bodycode).append('\'');
        sb.append(", representation='").append(representation).append('\'');
        sb.append(", unitname='").append(unitname).append('\'');
        sb.append(", deptname='").append(deptname).append('\'');
        sb.append(", rankname='").append(rankname).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", email2='").append(email2).append('\'');
        sb.append(", email3='").append(email3).append('\'');
        sb.append(", homepage='").append(homepage).append('\'');
        sb.append(", qq='").append(qq).append('\'');
        sb.append(", msn='").append(msn).append('\'');
        sb.append(", wangwang='").append(wangwang).append('\'');
        sb.append(", buzphone='").append(buzphone).append('\'');
        sb.append(", buzphone2='").append(buzphone2).append('\'');
        sb.append(", buzfax='").append(buzfax).append('\'');
        sb.append(", assiphone='").append(assiphone).append('\'');
        sb.append(", callbacphone='").append(callbacphone).append('\'');
        sb.append(", carphone='").append(carphone).append('\'');
        sb.append(", unitphone='").append(unitphone).append('\'');
        sb.append(", homephone='").append(homephone).append('\'');
        sb.append(", homephone2='").append(homephone2).append('\'');
        sb.append(", homephone3='").append(homephone3).append('\'');
        sb.append(", homefax='").append(homefax).append('\'');
        sb.append(", mobilephone='").append(mobilephone).append('\'');
        sb.append(", mobilephone2='").append(mobilephone2).append('\'');
        sb.append(", mobilephone3='").append(mobilephone3).append('\'');
        sb.append(", unitzip='").append(unitzip).append('\'');
        sb.append(", unitprovince='").append(unitprovince).append('\'');
        sb.append(", unitcity='").append(unitcity).append('\'');
        sb.append(", unitdistrict='").append(unitdistrict).append('\'');
        sb.append(", unitstreet='").append(unitstreet).append('\'');
        sb.append(", unitaddress='").append(unitaddress).append('\'');
        sb.append(", homezip='").append(homezip).append('\'');
        sb.append(", homeprovince='").append(homeprovince).append('\'');
        sb.append(", homecity='").append(homecity).append('\'');
        sb.append(", homedistrict='").append(homedistrict).append('\'');
        sb.append(", homestreet='").append(homestreet).append('\'');
        sb.append(", homeaddress='").append(homeaddress).append('\'');
        sb.append(", home2zip='").append(home2zip).append('\'');
        sb.append(", home2province='").append(home2province).append('\'');
        sb.append(", home2city='").append(home2city).append('\'');
        sb.append(", home2district='").append(home2district).append('\'');
        sb.append(", home2street='").append(home2street).append('\'');
        sb.append(", home2address='").append(home2address).append('\'');
        sb.append(", inuseaddress='").append(inuseaddress).append('\'');
        sb.append(", searchstring='").append(searchstring).append('\'');
        sb.append(", memo='").append(memo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
