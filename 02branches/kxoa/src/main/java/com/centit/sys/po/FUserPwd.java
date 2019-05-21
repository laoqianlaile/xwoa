package com.centit.sys.po;

public class FUserPwd {
	private String loginname;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	private String captchaKey;
	private String captchaVal;

	public String getCaptchaKey() {
		return captchaKey;
	}
	public void setCaptchaKey(String captchaKey) {
		this.captchaKey = captchaKey;
	}
	public String getCaptchaVal() {
		return captchaVal;
	}
	public void setCaptchaVal(String captchaVal) {
		this.captchaVal = captchaVal;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public void copy(FUserPwd other){
		this.loginname= other.getLoginname();
		this.oldPassword= other.getOldPassword();
		this.newPassword= other.getNewPassword();
		this.confirmPassword= other.getConfirmPassword();
		this.captchaKey= other.getCaptchaKey();
		this.captchaVal= other.getCaptchaVal();
	}
	
	public void copyNotNullProperty(FUserPwd other){
  
		if( other.getLoginname() != null)
			this.loginname= other.getLoginname();
		if( other.getOldPassword() != null)
			this.oldPassword= other.getOldPassword();
		if( other.getNewPassword() != null)
			this.newPassword= other.getNewPassword();
		if( other.getConfirmPassword() != null)
			this.confirmPassword= other.getConfirmPassword();
		if( other.getCaptchaKey() != null)
			this.captchaKey= other.getCaptchaKey();
		if( other.getCaptchaVal() != null)
			this.captchaVal= other.getCaptchaVal();
	}
}