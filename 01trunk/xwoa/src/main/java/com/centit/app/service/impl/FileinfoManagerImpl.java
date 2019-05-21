package com.centit.app.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.app.dao.FileInfoDao;
import com.centit.app.po.FileInfo;
import com.centit.app.service.FileinfoManager;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.support.utils.DatetimeOpt;

public class FileinfoManagerImpl extends BaseEntityManagerImpl<FileInfo>
	implements FileinfoManager{
    private static final long serialVersionUID = 1L;	
    protected FileInfoDao fileInfoDao;
	
	public void setFileinfoDao(FileInfoDao baseDao)
	{
		this.fileInfoDao = baseDao;
		setBaseDao(this.fileInfoDao);
	}
	
	public String getNextKey() {
		String sKey = "00000000000"+
			fileInfoDao.getNextValueOfSequence("S_FILENO");
		return DatetimeOpt.convertDateToString(DatetimeOpt.currentUtilDate(),"yyMMdd") + sKey.substring(sKey.length()-10);
	}
	
	public List<FileInfo> listLastFileByType(String fileType,int listSum){
        Map<String, Object> filterMap = new HashMap<String, Object>();
        PageDesc pageDesc=new PageDesc();
        filterMap.put("filetype", fileType);
        pageDesc.setPageNo(1);
        pageDesc.setPageSize(listSum);      
        return fileInfoDao.listObjects(filterMap, pageDesc);
    }
	
}

