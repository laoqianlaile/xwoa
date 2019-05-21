package com.centit.powerbase.po;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class PcpunishStandard implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private PcpunishStandardId cid;
    private Long isinuse;
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
    private Set<Pcfreeumpiretype> pcfreeumpiretypes = null;// new
                                                           // ArrayList<Pcfreeumpiretype>();
    private String israte;
    // Constructors
    /** default constructor */
    public PcpunishStandard() {
    }

    /** minimal constructor */
    public PcpunishStandard(PcpunishStandardId id

    , Long isinuse) {
        this.cid = id;

        this.isinuse = isinuse;
    }

    

    public String getItemId() {
        if (this.cid == null)
            this.cid = new PcpunishStandardId();
        return this.cid.getItemId();
    }

    public void setItemId(String itemId) {
        if (this.cid == null)
            this.cid = new PcpunishStandardId();
        this.cid.setItemId(itemId);
    }

    public String getVersion() {
        if (this.cid == null)
            this.cid = new PcpunishStandardId();
        return this.cid.getVersion();
    }

    public void setVersion(String version) {
        if (this.cid == null)
            this.cid = new PcpunishStandardId();
        this.cid.setVersion(version);
    }

    /** full constructor */
    public PcpunishStandard(PcpunishStandardId id

    , Long isinuse, Long punishtype,
            String toplimit, String toplimitUnit, String lowlimit,
            String lowlimitUnit, String baseName, String baseToplimit,
            String baseLowlimit, String baseUnit, String remark) {
        this.cid = id;

      
        this.isinuse = isinuse;
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

    public PcpunishStandardId getCid() {
        return this.cid;
    }

    public void setCid(PcpunishStandardId id) {
        this.cid = id;
    }

 
    public String getPunishtypeid() {
        if (this.cid == null)
            this.cid = new PcpunishStandardId();
        return this.cid.getPunishtypeid();
    }

    public void setPunishtypeid(String punishtypeid) {
        if (this.cid == null)
            this.cid = new PcpunishStandardId();
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

    public Set<Pcfreeumpiretype> getPcfreeumpiretypes() {
        if (this.pcfreeumpiretypes == null)
            this.pcfreeumpiretypes = new HashSet<Pcfreeumpiretype>();
        return this.pcfreeumpiretypes;
    }

    public void setPcfreeumpiretypes(Set<Pcfreeumpiretype> pcfreeumpiretypes) {
        this.pcfreeumpiretypes = pcfreeumpiretypes;
    }

    public void addPcfreeumpiretype(Pcfreeumpiretype pcfreeumpiretype) {
        if (this.pcfreeumpiretypes == null)
            this.pcfreeumpiretypes = new HashSet<Pcfreeumpiretype>();
        this.pcfreeumpiretypes.add(pcfreeumpiretype);
    }

    public void removePcfreeumpiretype(Pcfreeumpiretype pcfreeumpiretype) {
        if (this.pcfreeumpiretypes == null)
            return;
        this.pcfreeumpiretypes.remove(pcfreeumpiretype);
    }

    public Pcfreeumpiretype newPcfreeumpiretype() {
        Pcfreeumpiretype res = new Pcfreeumpiretype();

       

        res.setPunishtypeid(this.getPunishtypeid());

        return res;
    }

    /**
     * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
     * 
     */
    public void replacePcfreeumpiretypes(
            List<Pcfreeumpiretype> pcfreeumpiretypes) {
        List<Pcfreeumpiretype> newObjs = new ArrayList<Pcfreeumpiretype>();
        for (Pcfreeumpiretype p : pcfreeumpiretypes) {
            if (p == null)
                continue;
            Pcfreeumpiretype newdt = newPcfreeumpiretype();
            newdt.copyNotNullProperty(p);
            newObjs.add(newdt);
        }
        // delete
        boolean found = false;
        Set<Pcfreeumpiretype> oldObjs = new HashSet<Pcfreeumpiretype>();
        oldObjs.addAll(getPcfreeumpiretypes());

        for (Iterator<Pcfreeumpiretype> it = oldObjs.iterator(); it.hasNext();) {
            Pcfreeumpiretype odt = it.next();
            found = false;
            for (Pcfreeumpiretype newdt : newObjs) {
                if (odt.getCid().equals(newdt.getCid())) {
                    found = true;
                    break;
                }
            }
            if (!found)
                removePcfreeumpiretype(odt);
        }
        oldObjs.clear();
        // insert or update
        for (Pcfreeumpiretype newdt : newObjs) {
            found = false;
            for (Iterator<Pcfreeumpiretype> it = getPcfreeumpiretypes()
                    .iterator(); it.hasNext();) {
                Pcfreeumpiretype odt = it.next();
                if (odt.getCid().equals(newdt.getCid())) {
                    odt.copy(newdt);
                    found = true;
                    break;
                }
            }
            if (!found)
                addPcfreeumpiretype(newdt);
        }
    }

    public void copy(PcpunishStandard other) {

        
        this.setPunishtypeid(other.getPunishtypeid());
        this.setItemId(other.getItemId());
        this.setVersion(other.getVersion());
 
        this.isinuse = other.getIsinuse();
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

        this.pcfreeumpiretypes = other.getPcfreeumpiretypes();
    }

    public void copyNotNullProperty(PcpunishStandard other) {

      
        if (other.getPunishtypeid() != null)
            this.setPunishtypeid(other.getPunishtypeid());

        if (other.getItemId() != null)
            this.setItemId(other.getItemId());
        if (other.getVersion() != null)
            this.setVersion(other.getVersion());
        if (other.getIsinuse() != null)
            this.isinuse = other.getIsinuse();
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

        this.pcfreeumpiretypes = other.getPcfreeumpiretypes();
    }

    public void clearProperties() {

 
        this.isinuse = null;
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

        this.pcfreeumpiretypes = new HashSet<Pcfreeumpiretype>();
    }


    public String getIsrate() {
        return israte;
    }

    public void setIsrate(String israte) {
        this.israte = israte;
    }

    
}
