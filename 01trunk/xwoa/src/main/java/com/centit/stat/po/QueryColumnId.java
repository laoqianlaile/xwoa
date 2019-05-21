package com.centit.stat.po;


/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class QueryColumnId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private String modelName;

	private String colName;

	// Constructors
	/** default constructor */
	public QueryColumnId() {
	}
	/** full constructor */
	public QueryColumnId(String modelName, String colName) {

		this.modelName = modelName;
		this.colName = colName;	
	}

  
	public String getModelName() {
		return this.modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
  
	public String getColName() {
		return this.colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof QueryColumnId))
			return false;
		
		QueryColumnId castOther = (QueryColumnId) other;
		boolean ret = true;
  
		ret = ret && ( this.getModelName() == castOther.getModelName() ||
					   (this.getModelName() != null && castOther.getModelName() != null
							   && this.getModelName().equals(castOther.getModelName())));
  
		ret = ret && ( this.getColName() == castOther.getColName() ||
					   (this.getColName() != null && castOther.getColName() != null
							   && this.getColName().equals(castOther.getColName())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getModelName() == null ? 0 :this.getModelName().hashCode());
  
		result = 37 * result +
		 	(this.getColName() == null ? 0 :this.getColName().hashCode());
	
		return result;
	}
}
