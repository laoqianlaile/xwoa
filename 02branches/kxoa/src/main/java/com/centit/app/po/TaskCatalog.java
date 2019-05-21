package com.centit.app.po;

public class TaskCatalog {
	
	private String catalogCode;
	private String catalogName;
	
	private String formUrl;
	private String viewUrl;
	
	/**
	 * 可选值，默认readonly
	 * readonly，只读
	 * change，可修改时间段
	 */
	private String optStyle = "readonly";

	public TaskCatalog(String catalogCode, String catalogName, String optStyle) {
		super();
		this.catalogCode = catalogCode;
		this.catalogName = catalogName;
		this.optStyle = optStyle;
	}

	public TaskCatalog(String catalogCode, String catalogName) {
		super();
		this.catalogCode = catalogCode;
		this.catalogName = catalogName;
	}
	
	

	public String getViewUrl() {
		return viewUrl;
	}

	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
	}

	public TaskCatalog(String catalogCode, String catalogName, String formUrl, String optStyle) {
		super();
		this.catalogCode = catalogCode;
		this.catalogName = catalogName;
		this.formUrl = formUrl;
		this.optStyle = optStyle;
	}

	public String getFormUrl() {
		return formUrl;
	}

	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}

	public String getCatalogCode() {
		return catalogCode;
	}

	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getOptStyle() {
		return optStyle;
	}

	public void setOptStyle(String optStyle) {
		this.optStyle = optStyle;
	}
	
	
	
}
