package com.centit.sys.po;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class VUserUnits implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private VUserUnitsId cid;

    private String unitName;
    private String userName;
    private Long unitOrder;
    private Long userOrder;

    // Constructors
    /** default constructor */
    public VUserUnits() {
    }

    /** minimal constructor */
    public VUserUnits(VUserUnitsId id

    ) {
        this.cid = id;

    }

    /** full constructor */
    public VUserUnits(VUserUnitsId id

    , String unitName, String userName, Long unitOrder, Long userOrder) {
        this.cid = id;

        this.unitName = unitName;
        this.userName = userName;
        this.unitOrder = unitOrder;
        this.userOrder = userOrder;
    }

    public VUserUnitsId getCid() {
        return this.cid;
    }

    public void setCid(VUserUnitsId id) {
        this.cid = id;
    }

    public String getUnitCode() {
        if (this.cid == null)
            this.cid = new VUserUnitsId();
        return this.cid.getUnitCode();
    }

    public void setUnitCode(String unitCode) {
        if (this.cid == null)
            this.cid = new VUserUnitsId();
        this.cid.setUnitCode(unitCode);
    }

    public String getUserCode() {
        if (this.cid == null)
            this.cid = new VUserUnitsId();
        return this.cid.getUserCode();
    }

    public void setUserCode(String userCode) {
        if (this.cid == null)
            this.cid = new VUserUnitsId();
        this.cid.setUserCode(userCode);
    }

    // Property accessors

    public String getUnitName() {
        return this.unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUnitOrder() {
        return this.unitOrder;
    }

    public void setUnitOrder(Long unitOrder) {
        this.unitOrder = unitOrder;
    }

    public Long getUserOrder() {
        return this.userOrder;
    }

    public void setUserOrder(Long userOrder) {
        this.userOrder = userOrder;
    }

    public void copy(VUserUnits other) {

        this.setUnitCode(other.getUnitCode());
        this.setUserCode(other.getUserCode());

        this.unitName = other.getUnitName();
        this.userName = other.getUserName();
        this.unitOrder = other.getUnitOrder();
        this.userOrder = other.getUserOrder();

    }

    public void copyNotNullProperty(VUserUnits other) {

        if (other.getUnitCode() != null)
            this.setUnitCode(other.getUnitCode());
        if (other.getUserCode() != null)
            this.setUserCode(other.getUserCode());

        if (other.getUnitName() != null)
            this.unitName = other.getUnitName();
        if (other.getUserName() != null)
            this.userName = other.getUserName();
        if (other.getUnitOrder() != null)
            this.unitOrder = other.getUnitOrder();
        if (other.getUserOrder() != null)
            this.userOrder = other.getUserOrder();

    }

    public void clearProperties() {

        this.unitName = null;
        this.userName = null;
        this.unitOrder = null;
        this.userOrder = null;

    }
}
