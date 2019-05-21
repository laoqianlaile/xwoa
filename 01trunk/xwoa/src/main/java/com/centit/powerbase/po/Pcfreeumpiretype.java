package com.centit.powerbase.po;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class Pcfreeumpiretype implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private PcfreeumpiretypeId cid;

    private String punishfactgrade;// 这个字段的作用是结合left join 取出另一张表的内容

    private Long isinuse;
    /*
     * private String punishmax; private String punishmin; private Long israte;
     * private String radixname; private String radixunit; private String
     * punishcontent;
     */
    private String standardNo;
    private Long punishtype;
    private String toplimit;
    private String toplimitUnit;
    private String lowlimit;
    private String lowlimitUnit;
    private String baseName;
    private String baseToplimit;
    private String baseLowlimit;
    private String baseUnit;
    private String remark;
    private String supPart;

    public String getSupPart() {
        return supPart;
    }

    public void setSupPart(String supPart) {
        this.supPart = supPart;
    }

    // Constructors
    /** default constructor */
    public Pcfreeumpiretype() {
    }

    /** minimal constructor */
    public Pcfreeumpiretype(PcfreeumpiretypeId id

    , Long isinuse) {
        this.cid = id;

        this.isinuse = isinuse;
    }

    /** full constructor */
    public Pcfreeumpiretype(PcfreeumpiretypeId id

    , Long isinuse, String standardNo, Long punishtype, String toplimit,
            String toplimitUnit, String lowlimit, String lowlimitUnit,
            String baseName, String baseToplimit, String baseLowlimit,
            String baseUnit, String remark) {
        this.cid = id;

        this.isinuse = isinuse;
        this.standardNo = standardNo;
        this.punishtype = punishtype;
        this.toplimit = toplimit;
        this.toplimitUnit = toplimitUnit;
        this.lowlimit = lowlimit;
        this.lowlimitUnit = lowlimitUnit;
        this.baseName = baseName;
        this.baseToplimit = baseToplimit;
        this.baseLowlimit = baseLowlimit;
        this.baseUnit = baseUnit;
        this.remark = remark;
    }

    public PcfreeumpiretypeId getCid() {
        return this.cid;
    }

    public void setCid(PcfreeumpiretypeId id) {
        this.cid = id;
    }

  
    public Long getDegreeno() {
        if (this.cid == null)
            this.cid = new PcfreeumpiretypeId();
        return this.cid.getDegreeno();
    }

    public void setDegreeno(Long degreeno) {
        if (this.cid == null)
            this.cid = new PcfreeumpiretypeId();
        this.cid.setDegreeno(degreeno);
    }

    public String getPunishtypeid() {
        if (this.cid == null)
            this.cid = new PcfreeumpiretypeId();
        return this.cid.getPunishtypeid();
    }

    public void setPunishtypeid(String punishtypeid) {
        if (this.cid == null)
            this.cid = new PcfreeumpiretypeId();
        this.cid.setPunishtypeid(punishtypeid);
    }

    // Property accessors

    public Long getIsinuse() {
        return this.isinuse;
    }

    public void setIsinuse(Long isinuse) {
        this.isinuse = isinuse;
    }

    public Long getPunishtype() {
        return this.punishtype;
    }

    public void setPunishtype(Long punishtype) {
        this.punishtype = punishtype;
    }

    public String getToplimit() {
        return this.toplimit;
    }

    public void setToplimit(String toplimit) {
        this.toplimit = toplimit;
    }

    public String getToplimitUnit() {
        return this.toplimitUnit;
    }

    public void setToplimitUnit(String toplimitUnit) {
        this.toplimitUnit = toplimitUnit;
    }

    public String getLowlimit() {
        return this.lowlimit;
    }

    public void setLowlimit(String lowlimit) {
        this.lowlimit = lowlimit;
    }

    public String getLowlimitUnit() {
        return this.lowlimitUnit;
    }

    public void setLowlimitUnit(String lowlimitUnit) {
        this.lowlimitUnit = lowlimitUnit;
    }

    public String getBaseName() {
        return this.baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getBaseToplimit() {
        return this.baseToplimit;
    }

    public void setBaseToplimit(String baseToplimit) {
        this.baseToplimit = baseToplimit;
    }

    public String getBaseLowlimit() {
        return this.baseLowlimit;
    }

    public void setBaseLowlimit(String baseLowlimit) {
        this.baseLowlimit = baseLowlimit;
    }

    public String getBaseUnit() {
        return this.baseUnit;
    }

    public void setBaseUnit(String baseUnit) {
        this.baseUnit = baseUnit;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStandardNo() {
        return this.standardNo;
    }

    public void setStandardNo(String standardNo) {
        this.standardNo = standardNo;
    }

    public void copy(Pcfreeumpiretype other) {

     
        this.setDegreeno(other.getDegreeno());
        this.setPunishtypeid(other.getPunishtypeid());

        this.isinuse = other.getIsinuse();
        this.standardNo = other.getStandardNo();
        this.punishtype = other.getPunishtype();
        this.toplimit = other.getToplimit();
        this.toplimitUnit = other.getToplimitUnit();
        this.lowlimit = other.getLowlimit();
        this.lowlimitUnit = other.getLowlimitUnit();
        this.baseName = other.getBaseName();
        this.baseToplimit = other.getBaseToplimit();
        this.baseLowlimit = other.getBaseLowlimit();
        this.baseUnit = other.getBaseUnit();
        this.remark = other.getRemark();

    }

    public void copyNotNullProperty(Pcfreeumpiretype other) {

        
        if (other.getDegreeno() != null)
            this.setDegreeno(other.getDegreeno());
        if (other.getPunishtypeid() != null)
            this.setPunishtypeid(other.getPunishtypeid());

        if (other.getIsinuse() != null)
            this.isinuse = other.getIsinuse();
        if (other.getStandardNo() != null)
            this.standardNo = other.getStandardNo();
        if (other.getPunishtype() != null)
            this.punishtype = other.getPunishtype();
        if (other.getToplimit() != null)
            this.toplimit = other.getToplimit();
        if (other.getToplimitUnit() != null)
            this.toplimitUnit = other.getToplimitUnit();
        if (other.getLowlimit() != null)
            this.lowlimit = other.getLowlimit();
        if (other.getLowlimitUnit() != null)
            this.lowlimitUnit = other.getLowlimitUnit();
        if (other.getBaseName() != null)
            this.baseName = other.getBaseName();
        if (other.getBaseToplimit() != null)
            this.baseToplimit = other.getBaseToplimit();
        if (other.getBaseLowlimit() != null)
            this.baseLowlimit = other.getBaseLowlimit();
        if (other.getBaseUnit() != null)
            this.baseUnit = other.getBaseUnit();
        if (other.getRemark() != null)
            this.remark = other.getRemark();

    }

    public void clearProperties() {

        this.isinuse = null;
        this.standardNo = null;
        this.punishtype = null;
        this.toplimit = null;
        this.toplimitUnit = null;
        this.lowlimit = null;
        this.lowlimitUnit = null;
        this.baseName = null;
        this.baseToplimit = null;
        this.baseLowlimit = null;
        this.baseUnit = null;
        this.remark = null;
       

    }

    public String getPunishfactgrade() {
        return punishfactgrade;
    }

    public void setPunishfactgrade(String punishfactgrade) {
        this.punishfactgrade = punishfactgrade;
    }
}
