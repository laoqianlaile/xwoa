package com.centit.mip.po;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaUserinfoMIP implements java.io.Serializable {
	 private static final long serialVersionUID =  1L;
	 @Expose  
	 @Since(1.0)
	 private String userid; //"用户唯一ID",
	 @Expose 
	 @Since(1.0)
     private String deptid; //"部门id",
	 @Expose  
	 @Since(1.0)
     private String username; //"姓名",
	 @Expose  
	 @Since(1.0)
     private String ways; //
	 @Expose  
     private String id; //"联系方式id",
	 @Expose  
	 @Since(1.0)
     private String type; //"联系方式类型",
	 @Expose 
	 @Since(1.0)
     private String value; //"联系方式"
	 @Expose  
	 @Since(1.0)
     private String deptname; //"部门名称"
	 
	 @Expose(serialize = false)   
     private String personid; //"人员id"
	 @Expose
	 @Since(1.0)  
     private String sex; //"性别",
	 @Expose  
	 @Since(1.0)
     private String age; //"年龄",
	 @Expose  
	 @Since(1.0)
     private String position; //"岗位",
	 
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getDeptid() {
        return deptid;
    }
    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getWays() {
        return ways;
    }
    public void setWays(String ways) {
        this.ways = ways;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getDeptname() {
        return deptname;
    }
    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }
    public String getPersonid() {
        return personid;
    }
    public void setPersonid(String personid) {
        this.personid = personid;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }


}
