package com.centit.powerruntime.po;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OptStuffInfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String stuffid;
    private String djId; // 流程id
    private String stuffname;
    private byte[] stuffcontent;
    private String stuffpath;
    private String iszhi;
    private String filename;
    private Long nodeInstId; // 节点id
    private Date uploadtime;
    private Date  begTime;
    private Date  endTime;
    private String uploadusercode;
    private String nodename;
    private String attachsize;//附件大小
    /**
     * 文件类别：<br>
     * ************************************************<br>
     * 1：格式文书<br>
     * 2：申请附件<br>
     * 3：办理附件 <br>
     * 4：证据（处罚平台用） <br>
     * ************************************************ <br>
     */
    private String filetype;

    /**
     * 格式文书类别archiveType：<br>
     * ************************************************<br>
     * 登记—— 01: 申请表<br>
     * 受理—— 02: 受理通知书、 03: 补正通知书、04:不受理通知书(保留)<br>
     * 审核（第二步）—— 05: 审核意见书、 06: 审核意见书附表 <br>
     * 审查（上报后的第一步）—— 07: 审查意见书<br>
     * 发证—— 08: 许可决定书<br>
     * ************************************************ <br>
     */
    private String archivetype;
    private String groupid;
    private String sortId;
    private String isuse;

    /**
     ************************************************ <br>
     * recordid:模板编号 wsno:文书编号 <br>
     ************************************************* <br>
     */
    private String recordid;
    private String wsno;
    private String isUpload;
    
    private String tempDirPath;
    
    public String getIsuse() {
        return isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse;
    }

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(String isUpload) {
        this.isUpload = isUpload;
    }

    public Date getBegTime() {
        return begTime;
    }

    public void setBegTime(Date begTime) {
        this.begTime = begTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    // Constructors
    /** default constructor */
    public OptStuffInfo() {
    }

    /** minimal constructor */
    public OptStuffInfo(String stuffid, String djId) {

        this.stuffid = stuffid;

        this.djId = djId;
    }

    /** full constructor */
    public OptStuffInfo(String stuffid, String djId, String stuffname,
            byte[] stuffcontent, String isuse, String iszhi, String filename,
            Long nodeInstId, Date uploadtime, Date begTime, Date endTime, String uploadusercode,
            String nodename, String filetype, String sortId,
            String archivetype, String groupid,String attachsize) {

        this.stuffid = stuffid;

        this.djId = djId;
        this.stuffname = stuffname;
        this.stuffcontent = stuffcontent;
        this.iszhi = iszhi;
        this.filename = filename;
        this.nodeInstId = nodeInstId;
        this.uploadtime = uploadtime;
        this.uploadusercode = uploadusercode;
        this.nodename = nodename;
        this.filetype = filetype;
        this.archivetype = archivetype;
        this.groupid = groupid;
        this.sortId = sortId;
        this.isuse = isuse;
        this.begTime = begTime;
        this.endTime = endTime;
        this.attachsize = attachsize;
    }

    public OptStuffInfo(String stuffid, String djId, String stuffname,
            String iszhi, String filename, Long nodeInstId, Date uploadtime,Date begTime, Date endTime,
            String uploadusercode, String nodename, String filetype,
            String archivetype, String groupid, String sortId, String isuse,
            String recordid, String wsno, String isUpload,String attachsize) {
        super();
        this.stuffid = stuffid;
        this.djId = djId;
        this.stuffname = stuffname;
        this.iszhi = iszhi;
        this.filename = filename;
        this.nodeInstId = nodeInstId;
        this.uploadtime = uploadtime;
        this.begTime = begTime;
        this.endTime = endTime;
        this.uploadusercode = uploadusercode;
        this.nodename = nodename;
        this.filetype = filetype;
        this.archivetype = archivetype;
        this.groupid = groupid;
        this.sortId = sortId;
        this.isuse = isuse;
        this.recordid = recordid;
        this.wsno = wsno;
        this.isUpload = isUpload;
        this.attachsize = attachsize;
    }

    public String getStuffid() {
        return this.stuffid;
    }

    public void setStuffid(String stuffid) {
        this.stuffid = stuffid;
    }

    // Property accessors

    public String getDjId() {
        return this.djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

    public String getStuffname() {
        return this.stuffname;
    }

    public void setStuffname(String stuffname) {
        this.stuffname = stuffname;
    }

    public byte[] getStuffcontent() {
        return this.stuffcontent;
    }

    public void setStuffcontent(byte[] stuffcontent) {
        this.stuffcontent = stuffcontent;
    }

    public String getIszhi() {
        return this.iszhi;
    }

    public void setIszhi(String iszhi) {
        this.iszhi = iszhi;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getNodeInstId() {
        return this.nodeInstId;
    }

    public void setNodeInstId(Long nodeInstId) {
        this.nodeInstId = nodeInstId;
    }

    public Date getUploadtime() {
        return this.uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getUploadusercode() {
        return this.uploadusercode;
    }

    public void setUploadusercode(String uploadusercode) {
        this.uploadusercode = uploadusercode;
    }

    public String getNodename() {
        return this.nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public String getFiletype() {
        if (StringUtils.isBlank(filetype)) {
            this.filetype = "0";
        }
        return this.filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getArchivetype() {
        return this.archivetype;
    }

    public void setArchivetype(String archivetype) {
        this.archivetype = archivetype;
    }

    
    public String getTempDirPath() {
        return tempDirPath;
    }

    public void setTempDirPath(String tempDirPath) {
        this.tempDirPath = tempDirPath;
    }

    public void copy(OptStuffInfo other) {

        this.setStuffid(other.getStuffid());

        this.djId = other.getDjId();
        this.stuffname = other.getStuffname();
        this.stuffcontent = other.getStuffcontent();
        this.iszhi = other.getIszhi();
        this.filename = other.getFilename();
        this.nodeInstId = other.getNodeInstId();
        this.uploadtime = other.getUploadtime();
        this.uploadusercode = other.getUploadusercode();
        this.nodename = other.getNodename();
        this.filetype = other.getFiletype();
        this.archivetype = other.getArchivetype();
        this.groupid = other.groupid;
        this.sortId = other.sortId;
        this.isuse = other.isuse;
        this.recordid = other.getRecordid();
        this.wsno = other.getWsno();
        this.isUpload = other.getIsUpload();
        this.stuffpath = other.getStuffpath();
        this.begTime =other.getBegTime();
        this.endTime =other.getEndTime();
        this.attachsize =other.getAttachsize();
    }

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public String getWsno() {
        return wsno;
    }

    public void setWsno(String wsno) {
        this.wsno = wsno;
    }

    public void copyNotNullProperty(OptStuffInfo other) {

        if (other.getStuffid() != null)
            this.setStuffid(other.getStuffid());

        if (other.getDjId() != null)
            this.djId = other.getDjId();
        if (other.getStuffname() != null)
            this.stuffname = other.getStuffname();
        if (other.getStuffcontent() != null)
            this.stuffcontent = other.getStuffcontent();
        if (other.getIszhi() != null)
            this.iszhi = other.getIszhi();
        if (other.getFilename() != null)
            this.filename = other.getFilename();
        if (other.getNodeInstId() != null)
            this.nodeInstId = other.getNodeInstId();
        if (other.getUploadtime() != null)
            this.uploadtime = other.getUploadtime();
        if (other.getBegTime() != null)
            this.begTime = other.getBegTime();
        if (other.getEndTime() != null)
            this.endTime = other.getEndTime();
        if (other.getUploadusercode() != null)
            this.uploadusercode = other.getUploadusercode();
        if (other.getNodename() != null)
            this.nodename = other.getNodename();
        if (other.getFiletype() != null)
            this.filetype = other.getFiletype();
        if (other.getArchivetype() != null)
            this.archivetype = other.getArchivetype();
        if (other.getGroupid()!= null)
            this.groupid = other.getGroupid();
        if (other.getSortId()!= null)
            this.sortId = other.getSortId();
        if (other.getIsuse() != null)
            this.isuse = other.getIsuse();
        
        if (other.getRecordid()!= null)
            this.recordid = other.getRecordid();
        if (other.getWsno()!= null)
            this.wsno = other.getWsno();
        if (other.isUpload != null)
            this.isUpload = other.getIsUpload();
        if (other.stuffpath != null)
            this.stuffpath = other.getStuffpath();
        if (other.attachsize !=null)
            this.attachsize = other.getAttachsize();
    }

    public void clearProperties() {

        this.djId = null;
        this.stuffname = null;
        this.stuffcontent = null;
        this.stuffpath = null;
        this.iszhi = null;
        this.filename = null;
        this.nodeInstId = null;
        this.uploadtime = null;
        this.begTime = null;
        this.endTime = null;
        this.uploadusercode = null;
        this.nodename = null;
        this.filetype = null;
        this.archivetype = null;
        this.groupid = null;
        this.sortId = null;
        this.isuse = null;
        this.recordid=null;
        this.wsno=null;
        this.isUpload = null;
        this.attachsize = null;
    }

    public String getStuffpath() {
        return stuffpath;
    }

    public void setStuffpath(String stuffpath) {
        this.stuffpath = stuffpath;
    }

    public String getAttachsize() {
        return attachsize;
    }

    public void setAttachsize(String attachsize) {
        this.attachsize = attachsize;
    }


    
}
