package com.centit.sys.po;

/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */

public class VUserUnitsId implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String unitCode;

    private String userCode;

    // Constructors
    /** default constructor */
    public VUserUnitsId() {
    }

    /** full constructor */
    public VUserUnitsId(String unitCode, String userCode) {

        this.unitCode = unitCode;
        this.userCode = userCode;
    }

    public String getUnitCode() {
        return this.unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUserCode() {
        return this.userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof VUserUnitsId))
            return false;

        VUserUnitsId castOther = (VUserUnitsId) other;
        boolean ret = true;

        ret = ret
                && (this.getUnitCode() == castOther.getUnitCode() || (this
                        .getUnitCode() != null
                        && castOther.getUnitCode() != null && this
                        .getUnitCode().equals(castOther.getUnitCode())));

        ret = ret
                && (this.getUserCode() == castOther.getUserCode() || (this
                        .getUserCode() != null
                        && castOther.getUserCode() != null && this
                        .getUserCode().equals(castOther.getUserCode())));

        return ret;
    }

    public int hashCode() {
        int result = 17;

        result = 37
                * result
                + (this.getUnitCode() == null ? 0 : this.getUnitCode()
                        .hashCode());

        result = 37
                * result
                + (this.getUserCode() == null ? 0 : this.getUserCode()
                        .hashCode());

        return result;
    }
}
