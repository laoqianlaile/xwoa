package com.centit.powerbase.po;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.centit.powerbase.po.Pcfreeumpiretype;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class Pcfreeumpiredegree implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private PcfreeumpiredegreeId cid;

    private String itemId;
    private Long version;
    private String thisPart;
    
    public String getThisPart() {
        return thisPart;
    }

    public void setThisPart(String thisPart) {
        this.thisPart = thisPart;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    private Long isinuse;
    private String punishfactgrade;
    private String punishbasis;
    private String standardNo;
   private Set<Pcfreeumpiretype> pcfreeumpiretypes = null;// new
                                                           // ArrayList<Pcfreeumpiretype>();

    // Constructors
    /** default constructor */
    public Pcfreeumpiredegree() {
    }

    /** minimal constructor */
    public Pcfreeumpiredegree(PcfreeumpiredegreeId id

    , Long isinuse) {
        this.cid = id;

        this.isinuse = isinuse;
    }

    /** full constructor */
    public Pcfreeumpiredegree(PcfreeumpiredegreeId id, String itemId, Long version

    , Long isinuse, String punishfactgrade, String punishbasis,
            String standardNo) {
        this.cid = id;

        this.itemId = itemId;
        this.version = version;
        this.isinuse = isinuse;
        this.punishfactgrade = punishfactgrade;
        this.punishbasis = punishbasis;
        this.standardNo = standardNo;
    }

    public PcfreeumpiredegreeId getCid() {
        return this.cid;
    }

    public void setCid(PcfreeumpiredegreeId id) {
        this.cid = id;
    }


    public Long getDegreeno() {
        if (this.cid == null)
            this.cid = new PcfreeumpiredegreeId();
        return this.cid.getDegreeno();
    }

    public void setDegreeno(Long degreeno) {
        if (this.cid == null)
            this.cid = new PcfreeumpiredegreeId();
        this.cid.setDegreeno(degreeno);
    }

    // Property accessors

    public Long getIsinuse() {
        return this.isinuse;
    }

    public void setIsinuse(Long isinuse) {
        this.isinuse = isinuse;
    }

    public String getPunishfactgrade() {
        return this.punishfactgrade;
    }

    public void setPunishfactgrade(String punishfactgrade) {
        this.punishfactgrade = punishfactgrade;
    }

    public String getPunishbasis() {
        return this.punishbasis;
    }

    public void setPunishbasis(String punishbasis) {
        this.punishbasis = punishbasis;
    }

    public String getStandardNo() {
        return this.standardNo;
    }

    public void setStandardNo(String standardNo) {
        this.standardNo = standardNo;
    }

    public Set<Pcfreeumpiretype> getPcfreeumpiretypes(){
        if(this.pcfreeumpiretypes==null)
            this.pcfreeumpiretypes = new HashSet<Pcfreeumpiretype>();
        return this.pcfreeumpiretypes;
    }

    public void setPcfreeumpiretypes(Set<Pcfreeumpiretype> pcfreeumpiretypes) {
        this.pcfreeumpiretypes = pcfreeumpiretypes;
    }   

    public void addPcfreeumpiretype(Pcfreeumpiretype pcfreeumpiretype ){
        if (this.pcfreeumpiretypes==null)
            this.pcfreeumpiretypes = new HashSet<Pcfreeumpiretype>();
        this.pcfreeumpiretypes.add(pcfreeumpiretype);
    }
    
    public void removePcfreeumpiretype(Pcfreeumpiretype pcfreeumpiretype ){
        if (this.pcfreeumpiretypes==null)
            return;
        this.pcfreeumpiretypes.remove(pcfreeumpiretype);
    }

    public Pcfreeumpiretype newPcfreeumpiretype() {
        Pcfreeumpiretype res = new Pcfreeumpiretype();


        res.setDegreeno(this.getDegreeno());

        return res;
    }

    /**
     * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
     * 
     */
    public void replacePcfreeumpiretypes(List<Pcfreeumpiretype> pcfreeumpiretypes) {
        List<Pcfreeumpiretype> newObjs = new ArrayList<Pcfreeumpiretype>();
        for(Pcfreeumpiretype p :pcfreeumpiretypes){
            if(p==null)
                continue;
            Pcfreeumpiretype newdt = newPcfreeumpiretype();
            newdt.copyNotNullProperty(p);
            newObjs.add(newdt);
        }
        //delete
        boolean found = false;
        Set<Pcfreeumpiretype> oldObjs = new HashSet<Pcfreeumpiretype>();
        oldObjs.addAll(getPcfreeumpiretypes());
        
        for(Iterator<Pcfreeumpiretype> it=oldObjs.iterator(); it.hasNext();){
            Pcfreeumpiretype odt = it.next();
            found = false;
            for(Pcfreeumpiretype newdt :newObjs){
                if(odt.getCid().equals( newdt.getCid())){
                    found = true;
                    break;
                }
            }
            if(! found)
                removePcfreeumpiretype(odt);
        }
        oldObjs.clear();
        //insert or update
        for(Pcfreeumpiretype newdt :newObjs){
            found = false;
            for(Iterator<Pcfreeumpiretype> it=getPcfreeumpiretypes().iterator();
             it.hasNext();){
                Pcfreeumpiretype odt = it.next();
                if(odt.getCid().equals( newdt.getCid())){
                    odt.copy(newdt);
                    found = true;
                    break;
                }
            }
            if(! found)
                addPcfreeumpiretype(newdt);
        }   
    }   


    public void copy(Pcfreeumpiredegree other) {

        this.setDegreeno(other.getDegreeno());
        
        this.itemId = other.getItemId();
        this.version = other.getVersion();
        this.isinuse = other.getIsinuse();
        this.punishfactgrade = other.getPunishfactgrade();
        this.punishbasis = other.getPunishbasis();
        this.standardNo = other.getStandardNo();
        this.pcfreeumpiretypes = other.getPcfreeumpiretypes();
       
    }

    public void copyNotNullProperty(Pcfreeumpiredegree other) {

       
        if (other.getDegreeno() != null)
            this.setDegreeno(other.getDegreeno());

        if (other.getItemId() != null)
            this.itemId = other.getItemId();
        if (other.getVersion() != null)
            this.version = other.getVersion();
        if (other.getIsinuse() != null)
            this.isinuse = other.getIsinuse();
        if (other.getPunishfactgrade() != null)
            this.punishfactgrade = other.getPunishfactgrade();
        if (other.getPunishbasis() != null)
            this.punishbasis = other.getPunishbasis();
        if (other.getStandardNo() != null)
            this.standardNo = other.getStandardNo();
        this.pcfreeumpiretypes = other.getPcfreeumpiretypes();
       
    }

    public void clearProperties() {

        this.itemId= null;  
        this.version= null;  
        this.isinuse = null;
        this.punishfactgrade = null;
        this.punishbasis = null;
        this.standardNo = null;

        this.pcfreeumpiretypes = new HashSet<Pcfreeumpiretype>();
    }
}
