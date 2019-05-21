package com.centit.powerruntime.po;


/**
 * create by scaffold
 * 
 * @author hx
 */

public class OptNewlyIdea implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private OptNewlyIdeaId cid;

    private Long nodeinstid;
    private String nodename;
    private Long isdisplay;
    private Long orderno;
    private String url;

    // Constructors
    /** default constructor */
    public OptNewlyIdea() {
    }

    /** minimal constructor */
    public OptNewlyIdea(OptNewlyIdeaId id

    ) {
        this.cid = id;

    }

    /** full constructor */
    public OptNewlyIdea(OptNewlyIdeaId id

    , Long nodeinstid, String nodename, Long isdisplay, Long orderno, String url) {
        this.cid = id;

        this.nodeinstid = nodeinstid;
        this.nodename = nodename;
        this.isdisplay = isdisplay;
        this.orderno = orderno;
        this.url = url;
    }

    public OptNewlyIdea(String djId, Long nodeId) {
        // TODO Auto-generated constructor stub
        if(this.cid==null){
            this.cid=new OptNewlyIdeaId();  
        }
        this.cid.setDjId(djId);
        this.cid.setNodeid(nodeId);
    }

    public OptNewlyIdeaId getCid() {
        return this.cid;
    }

    public void setCid(OptNewlyIdeaId id) {
        this.cid = id;
    }

    public String getDjId() {
        if (this.cid == null)
            this.cid = new OptNewlyIdeaId();
        return this.cid.getDjId();
    }

    public void setDjId(String djId) {
        if (this.cid == null)
            this.cid = new OptNewlyIdeaId();
        this.cid.setDjId(djId);
    }

    public Long getNodeid() {
        if (this.cid == null)
            this.cid = new OptNewlyIdeaId();
        return this.cid.getNodeid();
    }

    public void setNodeid(Long nodeid) {
        if (this.cid == null)
            this.cid = new OptNewlyIdeaId();
        this.cid.setNodeid(nodeid);
    }

    // Property accessors

    public Long getNodeinstid() {
        return this.nodeinstid;
    }

    public void setNodeinstid(Long nodeinstid) {
        this.nodeinstid = nodeinstid;
    }

    public String getNodename() {
        return this.nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public Long getIsdisplay() {
        return this.isdisplay;
    }

    public void setIsdisplay(Long isdisplay) {
        this.isdisplay = isdisplay;
    }

    public Long getOrderno() {
        return this.orderno;
    }

    public void setOrderno(Long orderno) {
        this.orderno = orderno;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void copy(OptNewlyIdea other) {

        this.setDjId(other.getDjId());
        this.setNodeid(other.getNodeid());

        this.nodeinstid = other.getNodeinstid();
        this.nodename = other.getNodename();
        this.isdisplay = other.getIsdisplay();
        this.orderno = other.getOrderno();
        this.url = other.getUrl();

    }

    public void copyNotNullProperty(OptNewlyIdea other) {

        if (other.getDjId() != null)
            this.setDjId(other.getDjId());
        if (other.getNodeid() != null)
            this.setNodeid(other.getNodeid());

        if (other.getNodeinstid() != null)
            this.nodeinstid = other.getNodeinstid();
        if (other.getNodename() != null)
            this.nodename = other.getNodename();
        if (other.getIsdisplay() != null)
            this.isdisplay = other.getIsdisplay();
        if (other.getOrderno() != null)
            this.orderno = other.getOrderno();
        if (other.getUrl() != null)
            this.url = other.getUrl();

    }

    public void clearProperties() {

        this.nodeinstid = null;
        this.nodename = null;
        this.isdisplay = null;
        this.orderno = null;
        this.url = null;

    }
}
