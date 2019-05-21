package com.centit.dispatchdoc.service;

import com.centit.app.service.FileinfoManager;

public interface DataChangeManager extends FileinfoManager {
	public long getNeedUploadUsers() ;
	public long getNeedUploadArchives();
    
}
