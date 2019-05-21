package com.centit.app.service.impl;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.centit.app.dao.FileInfoDao;
import com.centit.app.po.FileInfo;
import com.centit.app.service.UploadService;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.support.searcher.Indexer;
import com.centit.support.utils.DatetimeOpt;

public  class UploadServiceImpl extends BaseEntityManagerImpl<FileInfo> implements UploadService {

	private FileInfo fileInfo;
	private FileInfoDao fileInfoDao;
	private static final long serialVersionUID = 1L;


	public void setPFileinfoDao(FileInfoDao baseDao)
	{
		this.fileInfoDao = baseDao;
		setBaseDao(this.fileInfoDao);
	}
	
	public String getNextKey() {
		String sKey = "00000000000"+
			fileInfoDao.getNextValueOfSequence("S_FILENO");
		return DatetimeOpt.convertDateToString(DatetimeOpt.currentUtilDate(),"yyMMdd")+ sKey.substring(sKey.length()-10);
	}


    public FileInfo getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

    //获取后缀名
	public  String  getfiletype(String filename)
	{
		
		int   index   =   filename.lastIndexOf( "."); 
		if(index   <0   ||   index==filename.length()-1) 
		    return   " "; 
		return   filename.substring(index+1);
	}

	//上传到本地
	public void upload(File file,String ContentType,String FileName,
			           String savePath,FileInfo fileInfo)throws Exception  {
		
		//上传信息到数据库中
		fileInfo.setFileCode(getNextKey());  //获取编号
		fileInfo.setRecordDate(DatetimeOpt.currentSqlDate());	//上传时间
		//fileInfo.setFileName(FileName);      //文件名
		//fileInfo.setFileType(ContentType);		//文件类型
		fileInfo.setFileExtName(getfiletype(FileName));	//文件后缀名
															//pFileinfo其他信息可以必须从页面填写传进去
		//FUserinfo ui = getLoginUser();	//获取用户信息
		//if(ui != null)
			//fileInfo.setRecorder( ui.getUsercode());
		
		
		//上传动作
		FileOutputStream fos = new FileOutputStream(savePath
			+ "\\" +fileInfo.getFileCode()+'.' +FileName);
		FileInputStream fis;
	
		fis = new FileInputStream(file);
		
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) > 0)
		{
			fos.write(buffer , 0 , len);
		}
		fis.close();
		fos.close();
		fileInfoDao.saveObject(fileInfo);
		
		
		if(fileInfo.getNeedIndex()==1)
		{
			String sFilePath = ServletActionContext.getServletContext().getRealPath("/")+savePath;
			Indexer.indexFile( sFilePath+"/"+fileInfo.getFileCode()+FileName ,
					//这个 url 可以通过 finfo.getFileType() 和 finfo.getOptCode() 来生成
					sFilePath, //TODO url
					fileInfo.getFileExtName(),fileInfo.getFileType(),fileInfo.getRecorder());
		}
		
	}
	
	//上传到数据库中
	public void uploaddb(File file,String ContentType,String FileName,String savePath,FileInfo fileInfo)throws Exception  {
		
		//上传信息到数据库中
		fileInfo.setFileCode(getNextKey());  //获取编号
		fileInfo.setRecordDate( DatetimeOpt.currentSqlDate());	//上传时间
		fileInfo.setFileName(FileName);      //文件名
		fileInfo.setFileType(ContentType);		//文件类型
		fileInfo.setFileExtName(getfiletype(FileName));	//文件后缀名
															//pFileinfo其他信息可以必须从页面填写传进去
		//FUserinfo ui = SystemUtil.getLoginUser();	//获取用户信息
		
		//if(ui != null)
		//	fileInfo.setRecorder( ui.getUsercode());
		
		//上传动作
		byte[] dadoc =FileUtils.readFileToByteArray(file);
		//BASE64Encoder encoder = new BASE64Encoder();
	//	fileInfo.setFileContent(Base64.encodeBase64(dadoc).toString());
		
		fileInfoDao.saveObject(fileInfo);
		
		//建立索引
		if(fileInfo.getNeedIndex()==1)
		{
			String sFilePath = ServletActionContext.getServletContext().getRealPath("/")+savePath;
			Indexer.indexByte( dadoc ,
					//这个 url 可以通过 finfo.getFileType() 和 finfo.getOptCode() 来生成
					sFilePath ,//TODO url
					fileInfo.getFileExtName(),fileInfo.getFileType(),fileInfo.getRecorder());
		}
		
	}
	
	
}