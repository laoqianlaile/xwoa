package com.centit.dispatchdoc.po;

/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */

public class DocRelativeId implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String incomeNo;

    private String dispatchNo;

    // Constructors
    /** default constructor */
    public DocRelativeId() {
    }

    /** full constructor */
    public DocRelativeId(String incomeNo, String dispatchNo) {

        this.incomeNo = incomeNo;
        this.dispatchNo = dispatchNo;
    }

    public String getIncomeNo() {
        return this.incomeNo;
    }

    public void setIncomeNo(String incomeNo) {
        this.incomeNo = incomeNo;
    }

    public String getDispatchNo() {
        return this.dispatchNo;
    }

    public void setDispatchNo(String dispatchNo) {
        this.dispatchNo = dispatchNo;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof DocRelativeId))
            return false;

        DocRelativeId castOther = (DocRelativeId) other;
        boolean ret = true;

        ret = ret
                && (this.getIncomeNo() == castOther.getIncomeNo() || (this
                        .getIncomeNo() != null
                        && castOther.getIncomeNo() != null && this
                        .getIncomeNo().equals(castOther.getIncomeNo())));

        ret = ret
                && (this.getDispatchNo() == castOther.getDispatchNo() || (this
                        .getDispatchNo() != null
                        && castOther.getDispatchNo() != null && this
                        .getDispatchNo().equals(castOther.getDispatchNo())));

        return ret;
    }

    public int hashCode() {
        int result = 17;

        result = 37
                * result
                + (this.getIncomeNo() == null ? 0 : this.getIncomeNo()
                        .hashCode());

        result = 37
                * result
                + (this.getDispatchNo() == null ? 0 : this.getDispatchNo()
                        .hashCode());

        return result;
    }
}
