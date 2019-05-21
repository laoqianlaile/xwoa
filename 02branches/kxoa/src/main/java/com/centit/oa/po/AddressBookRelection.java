package com.centit.oa.po;


/**
 * create by scaffold
 */ 

public class AddressBookRelection implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	private AddressBookRelectionId cid;


    private Addressbooks addressbooks;

	// Constructors
	/** default constructor */
	public AddressBookRelection() {
	}
	/** minimal constructor */
	public AddressBookRelection(AddressBookRelectionId id 
				
		) {
		this.cid = id; 
	}
	public AddressBookRelectionId getCid() {
		return this.cid;
	}
	
	public void setCid(AddressBookRelectionId id) {
		this.cid = id;
	}
  
	public String getAddrbookid() {
		if(this.cid==null)
			this.cid = new AddressBookRelectionId();
		return this.cid.getAddrbookid();
	}
	
	public void setAddrbookid(String addrbookid) {
		if(this.cid==null)
			this.cid = new AddressBookRelectionId();
		this.cid.setAddrbookid(addrbookid);
	}
  
	public String getShareman() {
		if(this.cid==null)
			this.cid = new AddressBookRelectionId();
		return this.cid.getShareman();
	}
	
	public void setShareman(String shareman) {
		if(this.cid==null)
			this.cid = new AddressBookRelectionId();
		this.cid.setShareman(shareman);
	}
	
	public String getBizType() {
        if(this.cid==null)
            this.cid = new AddressBookRelectionId();
        return this.cid.getBizType();
    }
    
    public void setBizType(String bizType) {
        if(this.cid==null)
            this.cid = new AddressBookRelectionId();
        this.cid.setBizType(bizType);
    }

	// Property accessors
    public void copy(AddressBookRelection other){
  
		this.setAddrbookid(other.getAddrbookid());  
		this.setShareman(other.getShareman());
		this.setBizType(other.getBizType());

	}
	
	public void copyNotNullProperty(AddressBookRelection other){
  
	if( other.getAddrbookid() != null)
		this.setAddrbookid(other.getAddrbookid());  
	if( other.getShareman() != null)
		this.setShareman(other.getShareman());
	if( other.getBizType() != null)
        this.setBizType(other.getBizType());


	}
	
	public void clearProperties(){       

	}
    public Addressbooks getAddressbooks() {
        return addressbooks;
    }
    public void setAddressbooks(Addressbooks addressbooks) {
        this.addressbooks = addressbooks;
    }
}
