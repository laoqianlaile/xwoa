package com.centit.sys.security;

import java.io.Serializable;

import com.centit.core.structs2.Struts2UrlParser;

public class OptDesc implements Serializable {
  private static final long serialVersionUID = 1L; 
	private String actionUrl;
	private String method;
	public OptDesc(){
		
	}
	
	public OptDesc(String au,String md){
		this.actionUrl = au;
		this.method = md;
	}
	
	public String getActionUrl() {
		return actionUrl;
	}
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getOptUrl(){
		return actionUrl+"!"+method+Struts2UrlParser.ACTION_POSTFIX;
	}
	
	
}
