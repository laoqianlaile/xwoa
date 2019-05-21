package com.centit.app.po;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class Msgannex implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private MsgannexId cid;

    // Constructors
    /** default constructor */
    public Msgannex() {
    }

    /** minimal constructor */
    public Msgannex(MsgannexId id

    ) {
        this.cid = id;

    }

    public MsgannexId getCid() {
        return this.cid;
    }

    public void setCid(MsgannexId id) {
        this.cid = id;
    }

    public Long getMsgcode() {
        if (this.cid == null)
            this.cid = new MsgannexId();
        return this.cid.getMsgcode();
    }

    public void setMsgcode(Long msgcode) {
        if (this.cid == null)
            this.cid = new MsgannexId();
        this.cid.setMsgcode(msgcode);
    }

    public String getFilecode() {
        if (this.cid == null)
            this.cid = new MsgannexId();
        return this.cid.getFilecode();
    }

    public void setFilecode(String filecode) {
        if (this.cid == null)
            this.cid = new MsgannexId();
        this.cid.setFilecode(filecode);
    }

    // Property accessors

    public void copy(Msgannex other) {

        this.setMsgcode(other.getMsgcode());
        this.setFilecode(other.getFilecode());

    }

    public void copyNotNullProperty(Msgannex other) {

        if (other.getMsgcode() != null)
            this.setMsgcode(other.getMsgcode());
        if (other.getFilecode() != null)
            this.setFilecode(other.getFilecode());

    }
}
