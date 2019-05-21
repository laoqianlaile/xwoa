package com.centit.oa.po;


/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class AddressBookRelectionId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private String addrbookid;

	private String shareman;
	
	private String bizType;

	// Constructors
	/** default constructor */
	public AddressBookRelectionId() {
	}
	/** full constructor */
	
	public AddressBookRelectionId(String addrbookid, String shareman) {

		this.addrbookid = addrbookid;
		this.shareman = shareman;	
	}

  
	public AddressBookRelectionId(String addrbookid, String shareman,
            String bizType) {
        super();
        this.addrbookid = addrbookid;
        this.shareman = shareman;
        this.bizType = bizType;
    }
    public String getBizType() {
        return bizType;
    }
    public void setBizType(String bizType) {
        this.bizType = bizType;
    }
    public String getAddrbookid() {
		return this.addrbookid;
	}

	public void setAddrbookid(String addrbookid) {
		this.addrbookid = addrbookid;
	}
  
	public String getShareman() {
		return this.shareman;
	}

	public void setShareman(String shareman) {
		this.shareman = shareman;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AddressBookRelectionId))
			return false;
		
		AddressBookRelectionId castOther = (AddressBookRelectionId) other;
		boolean ret = true;
  
		ret = ret && ( this.getAddrbookid() == castOther.getAddrbookid() ||
					   (this.getAddrbookid() != null && castOther.getAddrbookid() != null
							   && this.getAddrbookid().equals(castOther.getAddrbookid())));
  
		ret = ret && ( this.getShareman() == castOther.getShareman() ||
					   (this.getShareman() != null && castOther.getShareman() != null
							   && this.getShareman().equals(castOther.getShareman())));

		ret = ret && ( this.getBizType() == castOther.getBizType() ||
                (this.getBizType() != null && castOther.getBizType() != null
                        && this.getBizType().equals(castOther.getBizType())));
		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getAddrbookid() == null ? 0 :this.getAddrbookid().hashCode());
  
		result = 37 * result +
		 	(this.getShareman() == null ? 0 :this.getShareman().hashCode());
	
		result = 37 * result +
	            (this.getBizType() == null ? 0 :this.getBizType().hashCode());
		return result;
	}
}
