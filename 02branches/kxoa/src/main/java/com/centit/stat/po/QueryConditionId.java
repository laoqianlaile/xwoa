package com.centit.stat.po;


/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class QueryConditionId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private String modelName;

	private String condName;

	// Constructors
	/** default constructor */
	public QueryConditionId() {
	}
	/** full constructor */
	public QueryConditionId(String modelName, String condName) {

		this.modelName = modelName;
		this.condName = condName;	
	}

  
	public String getModelName() {
		return this.modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
  
	public String getCondName() {
		return this.condName;
	}

	public void setCondName(String condName) {
		this.condName = condName;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof QueryConditionId))
			return false;
		
		QueryConditionId castOther = (QueryConditionId) other;
		boolean ret = true;
  
		ret = ret && ( this.getModelName() == castOther.getModelName() ||
					   (this.getModelName() != null && castOther.getModelName() != null
							   && this.getModelName().equals(castOther.getModelName())));
  
		ret = ret && ( this.getCondName() == castOther.getCondName() ||
					   (this.getCondName() != null && castOther.getCondName() != null
							   && this.getCondName().equals(castOther.getCondName())));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getModelName() == null ? 0 :this.getModelName().hashCode());
  
		result = 37 * result +
		 	(this.getCondName() == null ? 0 :this.getCondName().hashCode());
	
		return result;
	}
}
