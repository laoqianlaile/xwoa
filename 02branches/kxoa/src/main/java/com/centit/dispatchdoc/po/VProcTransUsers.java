package com.centit.dispatchdoc.po;

import java.util.Date;
/**
 * 
 * TODO Class description should be added
 * 
 * @author cjw
 * @create 2013-12-13
 * @version
 */
public class VProcTransUsers  implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    private String id;
    private String djId;
    private String usercode;
    private String nodeCode;
    private Date transdate;
    private String username;
    private String transcontent;
    private String unitcode;
    private String unitname;
    
    private String ideacode;
    private String transidea;
    
    public VProcTransUsers(String id, String djId, String usercode,
            String nodeCode, Date transdate, String username,
            String transcontent, String unitcode, String unitname,
            String ideacode, String transidea) {
        super();
        this.id = id;
        this.djId = djId;
        this.usercode = usercode;
        this.nodeCode = nodeCode;
        this.transdate = transdate;
        this.username = username;
        this.transcontent = transcontent;
        this.unitcode = unitcode;
        this.unitname = unitname;
        this.ideacode = ideacode;
        this.transidea = transidea;
    }
    public String getIdeacode() {
        return ideacode;
    }
    public void setIdeacode(String ideacode) {
        this.ideacode = ideacode;
    }
    public String getTransidea() {
        return transidea;
    }
    public void setTransidea(String transidea) {
        this.transidea = transidea;
    }
    public String getUnitcode() {
        return unitcode;
    }
    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }
    public String getUnitname() {
        return unitname;
    }
    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }
    public String getTranscontent() {
        return transcontent;
    }
    public void setTranscontent(String transcontent) {
        this.transcontent = transcontent;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public VProcTransUsers() {
        super();
        // TODO Auto-generated constructor stub
    }
    public VProcTransUsers(String djId, String usercode, String nodeCode,
            Date transdate, String username) {
        super();
        this.djId = djId;
        this.usercode = usercode;
        this.nodeCode = nodeCode;
        this.transdate = transdate;
        this.username = username;
    }
    public String getDjId() {
        return djId;
    }
    public void setDjId(String djId) {
        this.djId = djId;
    }
    public String getUsercode() {
        return usercode;
    }
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
    public String getNodeCode() {
        return nodeCode;
    }
    public void setNodeCode(String nodeCode) {
        if (null != nodeCode && (0 == nodeCode.indexOf("P_") || 0 == nodeCode.indexOf("N_"))) {
            nodeCode = nodeCode.substring(2);
        }
        this.nodeCode = nodeCode;
    }
    public Date getTransdate() {
        return transdate;
    }
    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void copy(VProcTransUsers other) {

        this.djId = other.getDjId(); 
        this.usercode = other.getUsercode();
        this.username = other.getUsername();
        this.transdate = other.getTransdate();
        this.nodeCode = other.getNodeCode();
        this.ideacode = other.getIdeacode();
        this.transidea = other.getTransidea();
    }
    public void copyNotNullProperty(VProcTransUsers other) {

       
        if (other.getDjId() != null)
            this.djId = other.getDjId();
     
        if (other.getUsercode() != null)
            this.usercode = other.getUsercode();
        if (other.getUsername() != null)
            this.username = other.getUsername();
        if (other.getTransdate() != null)
            this.transdate = other.getTransdate();
       
        if(other.getNodeCode() != null)
            this.nodeCode = other.getNodeCode();
        if(other.getIdeacode() != null)
            this.ideacode = other.getIdeacode();
        if(other.getTransidea() != null)
            this.transidea = other.getTransidea();
    }
    public void clearProperties() {

 
        this.djId = null;

        this.usercode = null;
        this.username = null;
        this.transdate = null;
       
        this.nodeCode = null;
        
        this.ideacode = null;
        this.transidea = null;

    }

}
