package com.centit.oa.po;

import java.util.Date;

/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class OaMeetingmaterialinfosId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private String djId;

	private String meetingAttendee;
	
	private String stuffId;

	// Constructors
	/** default constructor */
	public OaMeetingmaterialinfosId() {
	}
	
	public OaMeetingmaterialinfosId(String djId, String meetingAttendee,
            String stuffId) {
        super();
        this.djId = djId;
        this.meetingAttendee = meetingAttendee;
        this.stuffId = stuffId;
    }

    /** full constructor */
	public OaMeetingmaterialinfosId(String djId, String meetingAttendee) {

		this.djId = djId;
		this.meetingAttendee = meetingAttendee;	
	}

  
	public String getDjId() {
		return this.djId;
	}

	public String getStuffId() {
        return stuffId;
    }
    public void setStuffId(String stuffId) {
        this.stuffId = stuffId;
    }
    public void setDjId(String djId) {
		this.djId = djId;
	}
  
	public String getMeetingAttendee() {
		return this.meetingAttendee;
	}

	public void setMeetingAttendee(String meetingAttendee) {
		this.meetingAttendee = meetingAttendee;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OaMeetingmaterialinfosId))
			return false;
		
		OaMeetingmaterialinfosId castOther = (OaMeetingmaterialinfosId) other;
		boolean ret = true;
  
		ret = ret && ( this.getDjId() == castOther.getDjId() ||
					   (this.getDjId() != null && castOther.getDjId() != null
							   && this.getDjId().equals(castOther.getDjId())));
  
		ret = ret && ( this.getMeetingAttendee() == castOther.getMeetingAttendee() ||
					   (this.getMeetingAttendee() != null && castOther.getMeetingAttendee() != null
							   && this.getMeetingAttendee().equals(castOther.getMeetingAttendee())));
		ret = ret && ( this.getStuffId() == castOther.getStuffId() ||
                (this.getStuffId() != null && castOther.getStuffId() != null
                        && this.getStuffId().equals(castOther.getStuffId())));
		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getDjId() == null ? 0 :this.getDjId().hashCode());
  
		result = 37 * result +
		 	(this.getMeetingAttendee() == null ? 0 :this.getMeetingAttendee().hashCode());
	
		result = 37 * result +
	            (this.getStuffId() == null ? 0 :this.getStuffId().hashCode());
		
		return result;
	}
}
