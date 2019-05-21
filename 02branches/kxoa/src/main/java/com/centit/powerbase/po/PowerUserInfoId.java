package com.centit.powerbase.po;


/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */

public class PowerUserInfoId implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String usercode;

    private String itemId;

    // Constructors
    /** default constructor */
    public PowerUserInfoId() {
    }

    /** full constructor */
    public PowerUserInfoId(String usercode, String itemId) {

        this.usercode = usercode;
        this.itemId = itemId;
    }

    public String getUsercode() {
        return this.usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof PowerUserInfoId))
            return false;

        PowerUserInfoId castOther = (PowerUserInfoId) other;
        boolean ret = true;

        ret = ret
                && (this.getUsercode() == castOther.getUsercode() || (this
                        .getUsercode() != null
                        && castOther.getUsercode() != null && this
                        .getUsercode().equals(castOther.getUsercode())));

        ret = ret
                && (this.getItemId() == castOther.getItemId() || (this
                        .getItemId() != null && castOther.getItemId() != null && this
                        .getItemId().equals(castOther.getItemId())));

        return ret;
    }

    public int hashCode() {
        int result = 17;

        result = 37
                * result
                + (this.getUsercode() == null ? 0 : this.getUsercode()
                        .hashCode());

        result = 37 * result
                + (this.getItemId() == null ? 0 : this.getItemId().hashCode());

        return result;
    }
}
