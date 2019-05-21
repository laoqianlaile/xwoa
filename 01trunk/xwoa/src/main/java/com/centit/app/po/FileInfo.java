package com.centit.app.po;

import java.util.Date;

/**
 * FileInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class FileInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

    private String fileCode;       //编号
    private String recorder;		//上传人
    private String fileExtName;		//后缀名
    private String optCode;        //业务编号
    private byte []  fileContent;		//文件
    private String fileDesc;		//描述
	private Date recordDate;		//上传时间
	private String fileType;		//上传文件类型
	private String fileName;		//上传文件名字
	private int needIndex;		//是否索引
	private String inDb;		//文件是否存在数据库中 // 1 存储在数据库中 0存储在/upload目录下文件中
    private Long instId;        //由业务系统自己解释  供反向链接

	// Constructors

	/** default constructor */
	public FileInfo() {
	}

	/** minimal constructor */
	public FileInfo(String fileCode, String fileExtName) {
		this.fileCode = fileCode;
		this.fileExtName = fileExtName;
		this.inDb="1";
	}

	/** full constructor */
	public FileInfo(String fileCode, String recorder, String fileExtName,
                    String optCode, byte []  fileContent, String fileDesc,
                    Date recordDate, String fileType, String fileName, String inDb,Long instId) {
		this.fileCode = fileCode;
		this.recorder = recorder;
		this.fileExtName = fileExtName;
		this.optCode = optCode;
		this.fileContent = fileContent;
		this.fileDesc = fileDesc;
		this.recordDate = recordDate;
		this.fileType = fileType;
		this.fileName = fileName;
		this.inDb=inDb;
		this.instId = instId;
	}

	// Property accessors

	public String getFileCode() {
		return this.fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	public String getRecorder() {
		return this.recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public String getFileExtName() {
		return this.fileExtName;
	}

	public void setFileExtName(String fileExtName) {
		this.fileExtName = fileExtName;
	}

	public String getOptCode() {
		return this.optCode;
	}

	public void setOptCode(String optCode) {
		this.optCode = optCode;
	}

	public byte []  getFileContent() {
		return this.fileContent;
	}

	public void setFileContent(byte []  fileContent) {
		this.fileContent = fileContent;
	}

	public String getFileDesc() {
		return this.fileDesc;
	}

	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	public Date getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setNeedIndex(int needIndex) {
		this.needIndex = needIndex;
	}

	public int getNeedIndex() {
		return needIndex;
	}

	/**
	 * // 1 存储在数据库中 0存储在/upload目录下文件中
	 * @return
	 */
	public String getInDb() {
		return inDb;
	}
	/**
	 * // 1 存储在数据库中 0存储在/upload目录下文件中
	 * @param inDb
	 */
	public void setInDb(String inDb) {
		this.inDb = inDb;
	}
	
    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }
	public void copy(FileInfo other){
		
		this.fileCode=other.fileCode;
		this.fileContent=other.fileContent;
		this.fileDesc=other.fileDesc;
		this.fileExtName=other.fileExtName;
		this.fileName=other.fileName;
		this.fileType=other.fileType;
		this.inDb=other.inDb;
		this.optCode=other.optCode;
		this.instId=other.instId;
	}
	public void copyNotNullProperty(FileInfo other){
		if( other.fileCode != null)
			this.fileCode=other.fileCode;  
		if( other.fileContent != null)
			this.fileContent=other.fileContent;
		if( other.fileDesc != null)
			this.fileDesc=other.fileDesc;
		if( other.fileExtName != null)
			this.fileExtName=other.fileExtName;
		if( other.fileName != null)
			this.fileName=other.fileName;
		if( other.fileType != null)
			this.fileType=other.fileType;
		if( other.inDb != null)
			this.inDb=other.inDb;
		if( other.optCode != null)
			this.optCode=other.optCode;
        if( other.instId != null)
            this.instId=other.instId;
	}
	
    public void clearProperties(){
        
        this.fileCode=null;
        this.fileContent=null;
        this.fileDesc=null;
        this.fileExtName=null;
        this.fileName=null;
        this.fileType=null;
        this.inDb="0";
        this.optCode=null;
        this.instId=null;
    }

}