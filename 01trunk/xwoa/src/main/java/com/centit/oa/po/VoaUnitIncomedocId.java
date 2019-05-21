package com.centit.oa.po;

public class VoaUnitIncomedocId implements java.io.Serializable{
    private static final long serialVersionUID =  1L;
    private String no;
    private String usercode;
    
    public VoaUnitIncomedocId() {
    }

    public VoaUnitIncomedocId(String no, String usercode) {
        this.no = no;
        this.usercode = usercode;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((no == null) ? 0 : no.hashCode());
        result = prime * result
                + ((usercode == null) ? 0 : usercode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VoaUnitIncomedocId other = (VoaUnitIncomedocId) obj;
        if (no == null) {
            if (other.no != null)
                return false;
        } else if (!no.equals(other.no))
            return false;
        if (usercode == null) {
            if (other.usercode != null)
                return false;
        } else if (!usercode.equals(other.usercode))
            return false;
        return true;
    }
    
}
