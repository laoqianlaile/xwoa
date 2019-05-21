package com.centit.app.service;

import java.io.File;

import com.centit.app.po.FileInfo;
import com.centit.core.service.BaseEntityManager;


public interface UploadService  extends BaseEntityManager<FileInfo>  {
	
	void upload(File file,String ContentType,String FileName,String savePath,FileInfo fileInfo)throws Exception;
	void uploaddb(File file,String ContentType,String FileName,String savePath,FileInfo fileInfo)throws Exception;
	
	String getNextKey();
	String getfiletype(String filename);
	

}
