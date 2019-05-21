package com.centit.powerruntime.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerruntime.dao.TemplateFileDao;
import com.centit.powerruntime.po.TemplateFile;
import com.centit.powerruntime.service.TemplateFileManager;

public class TemplateFileManagerImpl extends BaseEntityManagerImpl<TemplateFile>
	implements TemplateFileManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(TemplateFileManager.class);

	private TemplateFileDao templateFileDao ;
	public void setTemplateFileDao(TemplateFileDao baseDao)
	{
		this.templateFileDao = baseDao;
		setBaseDao(this.templateFileDao);
	}
	
	public List<TemplateFile> listTemplateByType(String tempType){
	    return templateFileDao.listTemplateByType(tempType);
	}
	
	public TemplateFile getTempByRecord(String recordId){
	    
	    return templateFileDao.getTemplateByRecordId(recordId);
	}

    @Override
    public List<TemplateFile> listTemplateByTypeNoWith(String tempType,String squerySource) {
        if("list".equals(squerySource)){
        return templateFileDao.listTemplateByType(tempType); 
        }else{
        return templateFileDao.listTemplateByTypeNoWith(tempType);
        }
    }
	
}

