package com.centit.app.service;

import java.util.List;

import com.centit.app.po.FileInfo;
import com.centit.core.service.BaseEntityManager;

public interface FileinfoManager extends BaseEntityManager<FileInfo>
{
	public String getNextKey();
	public List<FileInfo> listLastFileByType(String fileType,int listSum);
}
