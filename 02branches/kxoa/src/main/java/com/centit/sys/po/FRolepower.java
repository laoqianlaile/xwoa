package com.centit.sys.po;

/**
 * FRolepower entity.
 * 
 * @author MyEclipse Persistence Tools
 */

//角色操作权限表
public class FRolepower implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;

	private FRolepowerId id; //主键id

	// Constructors

	/** default constructor */
	public FRolepower() {
	}

	/** full constructor */
	public FRolepower(FRolepowerId id) {
		this.id = id;
	}

	// Property accessors
	public FRolepowerId getId() {
		return this.id;
	}

	public void setId(FRolepowerId id) {
		this.id = id;
	}
	
	public String getRolecode() {
		return this.id.getRolecode();
	}

	public void setRolecode(String rolecode) {
		this.id.setRolecode(rolecode);
	}

	public String getOptcode() {
		return this.id.getOptcode();
	}

	public void setOptcode(String optcode) {
		this.id.setOptcode(optcode);
	}

}