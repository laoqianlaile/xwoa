package com.centit.app.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class Innermsg implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Long msgcode;

    private String sender;
    private Date senddate;
    private Long replymsgcode;
    private String msgtitle;
    private String receivetype;
    private String receive;
    private String msgstate;
    private String msgcontent;
    private String isReveive;
    private String[] fileCodes;
    private Set<Msgannex> msgannexs = null;// new ArrayList<Msgannex>();

    // Constructors
    /** default constructor */
    public Innermsg() {
    }

    /** minimal constructor */
    public Innermsg(Long msgcode) {

        this.msgcode = msgcode;

    }

    /** full constructor */
    public Innermsg(Long msgcode, String sender, Date senddate,
            Long replymsgcode, String msgtitle, String receivetype,
            String receive, String msgstate, String msgcontent) {

        this.msgcode = msgcode;

        this.sender = sender;
        this.senddate = senddate;
        this.replymsgcode = replymsgcode;
        this.msgtitle = msgtitle;
        this.receivetype = receivetype;
        this.receive = receive;
        this.msgstate = msgstate;
        this.msgcontent = msgcontent;
    }

    public Long getMsgcode() {
        return this.msgcode;
    }

    public void setMsgcode(Long msgcode) {
        this.msgcode = msgcode;
    }

    // Property accessors

    public String getSender() {
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getSenddate() {
        return this.senddate;
    }

    public void setSenddate(Date senddate) {
        this.senddate = senddate;
    }

    public Long getReplymsgcode() {
        return this.replymsgcode;
    }

    public void setReplymsgcode(Long replymsgcode) {
        this.replymsgcode = replymsgcode;
    }

    public String getMsgtitle() {
        return this.msgtitle;
    }

    public void setMsgtitle(String msgtitle) {
        this.msgtitle = msgtitle;
    }

    /**
     * 
     * @return 个人为消息，机构为公告
     */
    public String getReceivetype() {
        return this.receivetype;
    }

    /**
     * 
     * @param receivetype
     *            个人为消息，机构为公告
     */
    public void setReceivetype(String receivetype) {
        this.receivetype = receivetype;
    }

    public String getReceive() {
        return this.receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    /**
     * 
     * @return U 未读/R 已读/D 删除
     */
    public String getMsgstate() {
        return this.msgstate;
    }

    /**
     * 
     * @param msgstate
     *            未读/已读/删除
     */
    public void setMsgstate(String msgstate) {
        this.msgstate = msgstate;
    }

    public String getMsgcontent() {
        return this.msgcontent;
    }

    public void setMsgcontent(String msgcontent) {
        this.msgcontent = msgcontent;
    }

    public Set<Msgannex> getMsgannexs() {
        if (this.msgannexs == null)
            this.msgannexs = new HashSet<Msgannex>();
        return this.msgannexs;
    }

    public void setMsgannexs(Set<Msgannex> msgannexs) {
        this.msgannexs = msgannexs;
    }

    public void addMsgannex(Msgannex msgannex) {
        if (this.msgannexs == null)
            this.msgannexs = new HashSet<Msgannex>();
        this.msgannexs.add(msgannex);
    }

    public void removeMsgannex(Msgannex msgannex) {
        if (this.msgannexs == null)
            return;
        this.msgannexs.remove(msgannex);
    }

    public Msgannex newMsgannex() {
        Msgannex res = new Msgannex();

        res.setMsgcode(this.getMsgcode());

        return res;
    }

    public void replaceMsgannexs(List<Msgannex> msgannexs) {
        List<Msgannex> newObjs = new ArrayList<Msgannex>();
        for (Msgannex p : msgannexs) {
            if (p == null)
                continue;
            Msgannex newdt = newMsgannex();
            newdt.copyNotNullProperty(p);
            newObjs.add(newdt);
        }
        // delete
        boolean found = false;
        Set<Msgannex> oldObjs = new HashSet<Msgannex>();
        oldObjs.addAll(getMsgannexs());

        for (Iterator<Msgannex> it = oldObjs.iterator(); it.hasNext();) {
            Msgannex odt = it.next();
            found = false;
            for (Msgannex newdt : newObjs) {
                if (odt.getCid().equals(newdt.getCid())) {
                    found = true;
                    break;
                }
            }
            if (!found)
                removeMsgannex(odt);
        }
        oldObjs.clear();
        // insert
        for (Msgannex newdt : newObjs) {
            found = false;
            for (Iterator<Msgannex> it = getMsgannexs().iterator(); it
                    .hasNext();) {
                Msgannex odt = it.next();
                if (odt.getCid().equals(newdt.getCid())) {
                    odt.copy(newdt);
                    found = true;
                    break;
                }
            }
            if (!found)
                addMsgannex(newdt);
        }
    }

    public void copy(Innermsg other) {

        this.setMsgcode(other.getMsgcode());

        this.sender = other.getSender();
        this.senddate = other.getSenddate();
        this.replymsgcode = other.getReplymsgcode();
        this.msgtitle = other.getMsgtitle();
        this.receivetype = other.getReceivetype();
        this.receive = other.getReceive();
        this.msgstate = other.getMsgstate();
        this.msgcontent = other.getMsgcontent();

        this.msgannexs = other.getMsgannexs();
    }

    public void copyNotNullProperty(Innermsg other) {

        if (other.getMsgcode() != null)
            this.setMsgcode(other.getMsgcode());

        if (other.getSender() != null)
            this.sender = other.getSender();
        if (other.getSenddate() != null)
            this.senddate = other.getSenddate();
        if (other.getReplymsgcode() != null)
            this.replymsgcode = other.getReplymsgcode();
        if (other.getMsgtitle() != null)
            this.msgtitle = other.getMsgtitle();
        if (other.getReceivetype() != null)
            this.receivetype = other.getReceivetype();
        if (other.getReceive() != null)
            this.receive = other.getReceive();
        if (other.getMsgstate() != null)
            this.msgstate = other.getMsgstate();
        if (other.getMsgcontent() != null)
            this.msgcontent = other.getMsgcontent();

        this.msgannexs = other.getMsgannexs();
    }

    public String getIsReveive() {
        return isReveive;
    }

    public void setIsReveive(String isReveive) {
        this.isReveive = isReveive;
    }

    public String[] getFileCodes() {
        return fileCodes;
    }

    public void setFileCodes(String[] fileCodes) {
        this.fileCodes = fileCodes;
    }

    public void clearProperties() {

        this.msgcode = null;
        this.sender = null;
        this.senddate = null;
        this.replymsgcode = null;
        this.msgtitle = null;
        this.receivetype = null;
        this.receive = null;
        this.msgstate = null;
        this.msgcontent = null;
        this.msgannexs = null;
    }
}
