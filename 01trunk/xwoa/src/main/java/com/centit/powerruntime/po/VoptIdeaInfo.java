package com.centit.powerruntime.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class VoptIdeaInfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Long procid;

    private Long nodeInstId;
    private String djId;
    private String transaffairname;
    private String usercode;
    private String username;
    private Date transdate;
    private String nodename;
    private String itemTypeName;//办件类型
    
    

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    // Constructors
    /** default constructor */
    public VoptIdeaInfo() {
    }

    /** minimal constructor */
    public VoptIdeaInfo(Long procid, String djId) {

        this.procid = procid;

        this.djId = djId;
    }



    public VoptIdeaInfo(Long procid, Long nodeInstId, String djId,
            String transaffairname, String usercode, String username,
            Date transdate, String nodename) {
        super();
        this.procid = procid;
        this.nodeInstId = nodeInstId;
        this.djId = djId;
        this.transaffairname = transaffairname;
        this.usercode = usercode;
        this.username = username;
        this.transdate = transdate;
        this.nodename = nodename;
    }

    public Long getProcid() {
        return procid;
    }

    public void setProcid(Long procid) {
        this.procid = procid;
    }

    public Long getNodeInstId() {
        return nodeInstId;
    }

    public void setNodeInstId(Long nodeInstId) {
        this.nodeInstId = nodeInstId;
    }

    public String getDjId() {
        return djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

    public String getTransaffairname() {
        return transaffairname;
    }

    public void setTransaffairname(String transaffairname) {
        this.transaffairname = transaffairname;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTransdate() {
        return transdate;
    }

    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public void copy(VoptIdeaInfo other) {

        this.setProcid(other.getProcid());

        this.nodeInstId = other.getNodeInstId();
        this.djId = other.getDjId();
        this.usercode = other.getUsercode();
        this.username = other.getUsername();
        this.transdate = other.getTransdate();
        this.nodename = other.getNodename();
        this.transaffairname=other.getTransaffairname();
    }

    public void copyNotNullProperty(VoptIdeaInfo other) {

        if (other.getProcid() != null)
            this.setProcid(other.getProcid());

        if (other.getNodeInstId() != null)
            this.nodeInstId = other.getNodeInstId();
        if (other.getDjId() != null)
            this.djId = other.getDjId();
        if (other.getUsercode() != null)
            this.usercode = other.getUsercode();
        if (other.getUsername() != null)
            this.username = other.getUsername();
        if (other.getTransdate() != null)
            this.transdate = other.getTransdate();
        if (other.getNodename() != null)
            this.nodename = other.getNodename();
        if (other.getTransaffairname() != null)
            this.transaffairname = other.getTransaffairname();
    }

    public void clearProperties() {

        this.nodeInstId = null;
        this.djId = null;
        this.usercode = null;
        this.username = null;
        this.transdate = null;
        this.nodename = null;
        this.transaffairname = null;
    }


}
