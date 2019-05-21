package com.centit.dispatchdoc.service.impl;

import com.centit.app.service.impl.FileinfoManagerImpl;
import com.centit.dispatchdoc.service.DataChangeManager;

public class DataChangeManagerImpl extends FileinfoManagerImpl implements DataChangeManager {

	private static final long serialVersionUID = 1L;
	public long getNeedUploadArchives() {
		return super.fileInfoDao.getSingleIntByHql("select count(*) as needuploads from OptBaseInfo where isupload = '0'");
	}

	public long getNeedUploadUsers(){
		return super.fileInfoDao.getSingleIntByHql("select count(*) as needuploads from FUserinfo where isupload = '0'");
	}
	
}