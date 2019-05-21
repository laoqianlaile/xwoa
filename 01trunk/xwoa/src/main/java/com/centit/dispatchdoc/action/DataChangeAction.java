package com.centit.dispatchdoc.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.centit.app.po.FileInfo;
import com.centit.core.action.BaseAction;
import com.centit.dispatchdoc.service.DataChangeManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.opensymphony.xwork2.Action;

public class DataChangeAction extends BaseAction implements
		ServletRequestAware, Action {
	private static final long serialVersionUID = 1L;

	private DataChangeManager dataChgManager;
	private List<FileInfo> expfiles;
	private List<FileInfo> impfiles;
	private String filename;
	private String fileCode;
	private long needUploadUsers;
	private long needUploadArchives;

	public long getNeedUploadUsers() {
		return needUploadUsers;
	}

	public void setNeedUploadUsers(long needUploadUsers) {
		this.needUploadUsers = needUploadUsers;
	}

	public long getNeedUploadArchives() {
		return needUploadArchives;
	}

	public void setNeedUploadArchives(long needUploadArchives) {
		this.needUploadArchives = needUploadArchives;
	}

	private File importFile;

	public File getImportFile() {
		return importFile;
	}

	public void setImportFile(File importFile) {
		this.importFile = importFile;
	}
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
	public List<FileInfo> getExpfiles() {
		return expfiles;
	}

	public void setExpfiles(List<FileInfo> expfiles) {
		this.expfiles = expfiles;
	}

	public List<FileInfo> getImpfiles() {
		return impfiles;
	}

	public void setImpfiles(List<FileInfo> impfiles) {
		this.impfiles = impfiles;
	}
	
	public void setDataChgManager(DataChangeManager fileinfoManager) {
		this.dataChgManager = fileinfoManager;
	}

	public String view() {
		
		//select count(1) from Archives where ISUPLOAD = '0';
		//select count(1)  from FUserinfo where ISUPLOAD = '0';
		needUploadUsers = dataChgManager.getNeedUploadUsers();
		needUploadArchives = dataChgManager.getNeedUploadArchives();		 
		expfiles = dataChgManager.listLastFileByType("exp",7);
		impfiles = dataChgManager.listLastFileByType("imp",7);
		return "view";            
	}

	public String view1() {

        // select count(1) from Archives where ISUPLOAD = '0';
        // select count(1) from FUserinfo where ISUPLOAD = '0';
        needUploadUsers = dataChgManager.getNeedUploadUsers();
        needUploadArchives = dataChgManager.getNeedUploadArchives();

        return "view1";
    }

    public String view2() {

        // select count(1) from Archives where ISUPLOAD = '0';
        // select count(1) from FUserinfo where ISUPLOAD = '0';

        expfiles = dataChgManager.listLastFileByType("exp", 7);

        return "view2";
    }

    public String view3() {

        // select count(1) from Archives where ISUPLOAD = '0';
        // select count(1) from FUserinfo where ISUPLOAD = '0';

        impfiles = dataChgManager.listLastFileByType("imp", 7);
        return "view3";
    }
	
	public String downloadexp() throws IOException {

		FileInfo dbobject = dataChgManager.getObjectById(fileCode);
		Date expDate = dbobject.getRecordDate();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
		filename ="fgwsb"+df.format(expDate)+ ".dmp";
		String path = CodeRepositoryUtil.getValue("SYSPARAM", "uploadfile");
		String realFileName = ("exp".equals(dbobject.getFileType()) ? "expdata/":"impdata/") + filename;
		try {
			this.setInputStream(new FileInputStream(path + realFileName));
		} catch (FileNotFoundException e) {
		    log.error(e.getMessage(), e);
            saveError(e.getMessage());
		}
		return "download";
	}
	
	public String downloadexplog() throws IOException {

		FileInfo dbobject = dataChgManager.getObjectById(fileCode);
		Date expDate = dbobject.getRecordDate();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
		filename ="fgwsb"+df.format(expDate)+ ".log";
		String path = CodeRepositoryUtil.getValue("SYSPARAM", "uploadfile");
		String realFileName = ("exp".equals(dbobject.getFileType()) ? "expdata/":"impdata/") + filename;
		try {
			this.setInputStream(new FileInputStream(path + realFileName));
		} catch (FileNotFoundException e) {
		    log.error(e.getMessage(), e);
            saveError(e.getMessage());
		}
		return "downloadlog";
	}	
	
	public String doexport() {
		
		needUploadUsers = dataChgManager.getNeedUploadUsers();
		needUploadArchives = dataChgManager.getNeedUploadArchives();	
		if(needUploadUsers==0 && needUploadArchives==0)
			return view();
		
		Date expDate = DatetimeOpt.currentUtilDate();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
		
		String scriptpath = CodeRepositoryUtil.getValue("SYSPARAM", "scriptpath");
		String path = CodeRepositoryUtil.getValue("SYSPARAM", "uploadfile");
		
		String	realFileName = "expdata/fgwsb" + df.format(expDate)+ ".dmp";
		String logfile = path+ "expdata/fgwsb" + df.format(expDate)+ ".log";
		 
	    try { 
	    	Runtime rt = Runtime.getRuntime();
	    	String file = scriptpath + /*"D:/centit/j2ee/fgwapply/src/*/ "sqlExport/exportApplyData.bat"; 
	    	//"cmd.exe /c start " + 
	    	Process expProcess = rt.exec("cmd.exe /c start " + file + " "+ path+realFileName+" "+ logfile); 
	    	expProcess.waitFor();
	    	
	    	FileInfo expFile = new FileInfo();
	    	expFile.setFileCode(dataChgManager.getNextKey());
	    	expFile.setFileName(realFileName);
	    	expFile.setFileType("exp");
	    	expFile.setRecordDate(expDate); // 设置上传日期
	    	expFile.setRecorder(((FUserDetail)getLoginUser()).getLoginname()); // 获得上传人的名称
	    	expFile.setFileExtName("dmp");
	    	expFile.setInDb("0");
	    	dataChgManager.saveObject(expFile);
			
	    }catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) { 
	      e.printStackTrace(); 
	    } 
		saveMessage("已将本次备份数据保存至服务器,文件名:" + realFileName);
		
		return view();
	}

	@SuppressWarnings("resource")
    public String doimport() {
		String path = CodeRepositoryUtil.getValue("SYSPARAM", "uploadfile");
		String scriptpath = CodeRepositoryUtil.getValue("SYSPARAM", "scriptpath");
		
        //ServletActionContext.getRequest().getRealPath("/upload");
		Date impDate = DatetimeOpt.currentUtilDate();

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
		String realFileName = "impdata/fgwsb" + df.format(impDate)+ ".dmp";
		String logfile = path+ "impdata/fgwsb" + df.format(impDate)+ ".log";
		try{
	        File f = this.getImportFile();
	        
	        if(null!=f){
	            FileInputStream inputStream = new FileInputStream(f);
	            FileOutputStream outputStream = new FileOutputStream(path + realFileName);
	            byte[] buf = new byte[8192];
	            int length = 0;
	            while ((length = inputStream.read(buf)) != -1) {
	                outputStream.write(buf, 0, length);
	            }
	            inputStream.close();
	            outputStream.flush();
	            
	            Runtime rt = Runtime.getRuntime();
	            String file = scriptpath + /*"D:/centit/j2ee/fgwapply/src/*/ "sqlImport/importApplyData.bat"; 
	            //"cmd.exe /c start " + 
	            Process expProcess = rt.exec("cmd.exe /c start " + file + " "+ path+realFileName+" "+ logfile); 
	            expProcess.waitFor();

	            FileInfo impFile = new FileInfo();
	            impFile.setFileCode(dataChgManager.getNextKey());
	            impFile.setFileName(realFileName);
	            impFile.setFileType("imp");
	            impFile.setRecordDate(impDate); // 设置上传日期
	            impFile.setRecorder(((FUserDetail)getLoginUser()).getLoginname()); // 获得上传人的名称
	            impFile.setFileExtName("dmp");
	            impFile.setInDb("0");
	            dataChgManager.saveObject(impFile);
	        }	        
	    	
		}catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) { 
	      e.printStackTrace(); 
	    } 
		return view();
	}

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}




}
