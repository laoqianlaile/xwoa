package com.centit.app.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.centit.app.po.FileInfo;
import com.centit.app.service.FileinfoManager;
import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.support.utils.DatetimeOpt;
import com.centit.support.utils.FileSystemOpt;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;

public class FileinfoAction extends BaseEntityExtremeAction<FileInfo> {

    
	private static final long serialVersionUID = 1L;
	private int nFileInDBorDir = 1;// 1 存储在数据库中 0存储在/upload目录下文件中
	
	private FileinfoManager fileinfoManager;
	private static String dfNY = "yyyyMM";


	public void setFileinfoManager(FileinfoManager fileinfoManager) {
		this.fileinfoManager = fileinfoManager;
		this.setBaseEntityManager(fileinfoManager);
	}

	public void setFileInDB() {
		nFileInDBorDir = 1;
	}

	public void setFileInDir() {
		nFileInDBorDir = 0;
	}

	public String uploadindialog() {

		try {
			FileInfo dbInfo = getEntityClass().newInstance();
			dbInfo.setFileCode(fileinfoManager.getNextKey());
			fileinfoManager.copyObject(object, dbInfo);

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "uploadindialog";

	}

	public String built() {
		try {
			FileInfo dbInfo = getEntityClass().newInstance();
			dbInfo.setFileCode(fileinfoManager.getNextKey());
			fileinfoManager.copyObject(object, dbInfo);
			return EDIT;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Override
	public String edit() {

		try {

			FileInfo dbObject = fileinfoManager.getObjectById(object
					.getFileCode());
			if (dbObject != null)
				// 将对象o copy给object，object自己的属性会保留
				fileinfoManager.copyObjectNotNullProperty(object, dbObject);
			return EDIT;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	private String getUploadFilePath(Date recDate) {
		String sFilePath = ServletActionContext.getServletContext()
				.getRealPath("/") + "upload/";
		sFilePath = sFilePath + DatetimeOpt.convertDateToString(recDate,dfNY);
		if (!FileSystemOpt.existFile(sFilePath))
			FileSystemOpt.createDirect(sFilePath);
		return sFilePath;
	}

	private File upload;
	private String uploadFileName;
	private String uploadContentType;

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public static String getfiletype(String filename) {

		int index = filename.lastIndexOf(".");
		if (index < 0 || index == filename.length() - 1)
			return " ";
		return filename.substring(index + 1);
	}

	private void savefile() throws Exception {
	    //TODO 这个要求用数据字典中设置的路径! 请夏成确认一下
	        @SuppressWarnings("deprecation")
            String path = ServletActionContext.getRequest().getRealPath("/upload");
       
            File f = this.getUpload();
                  
            FileInputStream inputStream = new FileInputStream(f);
            FileOutputStream outputStream = new FileOutputStream(path + "/"+ this.getUploadFileName());
            byte[] buf = new byte[8192];
            int length = 0;
            while ((length = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, length);
            }
            inputStream.close();
            outputStream.flush();
            object.setFileCode(fileinfoManager.getNextKey());
            object.setRecordDate(DatetimeOpt.currentSqlDate()); // 设置上传日期
            object.setRecorder(((FUserDetail)this.getLoginUser()).getLoginname()); // 获得上传人的名称
            object.setFileExtName(getfiletype(uploadFileName));
            object.setInDb("0");
         
            fileinfoManager.saveObject(object);
	}

	private void savefiledb() {

		object.setRecordDate(DatetimeOpt.currentSqlDate()); // 设置上传日期
		object.setRecorder(((FUserDetail)this.getLoginUser()).getLoginname()); // 获得上传人的名称
	//	object.setFileType(getUploadContentType());
		object.setFileExtName(getfiletype(uploadFileName));
		object.setInDb("1"); // 1存储到数据库中，0是存储到/upload目录下文件中
		byte[] bbuf = null;	
		try {

			FileInputStream fis = new FileInputStream(upload);
			if (fis != null) {
				int len = fis.available();
				bbuf = new byte[len];
				fis.read(bbuf);
			}
			object.setFileContent(bbuf);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//object.setFileContent(dadoc);
		fileinfoManager.saveObject(object);

	}

	public String upload() {
	    
	    try{
		if (nFileInDBorDir == 1)
			savefiledb();
		else
			savefile();
	    }
	    catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
	    return SUCCESS;
	}

	private String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String downloadfile() throws IOException {

		FileInfo dbobject = fileinfoManager.getObjectById(object.getFileCode());
		String realFilename = dbobject.getFileCode() + '.'
				+ dbobject.getFileName();
		String fn = dbobject.getFileName() + '.' + dbobject.getFileExtName();
          this.setFilename(new String(fn.getBytes("GBK"), "ISO8859-1"));
      
		try {
			this.setInputStream(new FileInputStream(getUploadFilePath(dbobject
					.getRecordDate()) + "/" + realFilename));
		} catch (FileNotFoundException e) {
		    log.error(e.getMessage(), e);
            saveError(e.getMessage());
		}
		return "downloadfile";
	}

	public String downloaddb() throws IOException {
		FileInfo dbobject = fileinfoManager.getObjectById(object.getFileCode());
		String fn = dbobject.getFileName() + '.' + dbobject.getFileExtName();
		try {			
		inputStream=new ByteArrayInputStream(dbobject.getFileContent());
		} catch (Exception e) {
		    log.error(e.getMessage(), e);
            saveError(e.getMessage());
		}
		this.setFilename(new String(fn.getBytes("GBK"), "ISO8859-1"));
		
		return "downloadfile";
	}

	public String download() throws IOException {
		FileInfo dbobject = fileinfoManager.getObjectById(object.getFileCode());
		if (("1").equals(dbobject.getInDb()))
			return downloaddb(); 
		else
			return downloadfile();

	}
    
    public String uploadfile(){
        String path=CodeRepositoryUtil.getDataPiece("SYSPARAM", "uploadfile").getDatavalue();
       // String path = ServletActionContext.getRequest().getRealPath("/upload");
        try {
            File f = this.getUpload();
            if(this.getUploadFileName().endsWith(".exe")){
                message="对不起,你上传的文件格式不允许!!!";
                return ERROR;
            }   
            object.setFileCode(fileinfoManager.getNextKey());
            object.setRecordDate(DatetimeOpt.currentSqlDate()); // 设置上传日期
            object.setRecorder(((FUserDetail)this.getLoginUser()).getLoginname()); // 获得上传人的名称
            //object.setFileType(getUploadContentType());
            object.setFileExtName(getfiletype(uploadFileName));
            object.setFileName(this.getUploadFileName());
            object.setInDb("0");     
            FileInputStream inputStream = new FileInputStream(f);
            FileOutputStream outputStream = new FileOutputStream(path + "/"+ this.object.getFileCode()+"."+object.getFileExtName());
            byte[] buf = new byte[8192];
            int length = 0;
            while ((length = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, length);
            }
            inputStream.close();
            outputStream.flush();
           
            
            message="上传成功";
            fileinfoManager.saveObject(object);
        }catch (Exception e) {
            message = "对不起,文件上传失败了!!!!";
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
        }
      
        Map<String, String> map= new HashMap<String, String>();
        map.put("name", this.getUploadFileName());
        map.put("message",this.getMessage());
        map.put("id", object.getFileCode());
        result=JSONObject.fromObject(map);
        return "newupload";
    }
    
    private JSONObject result;
	
	private String  message;
	
	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

  


}