package com.centit.app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.app.po.FileInfo;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;

public class FileInfoDao extends BaseDaoImpl<FileInfo>
	{
    private static final long serialVersionUID = 1L;
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("filecode" , CodeBook.EQUAL_HQL_ID);
			filterField.put("optcode" , CodeBook.EQUAL_HQL_ID);
			filterField.put("recorder" , CodeBook.LIKE_HQL_ID);
			filterField.put("fileextname" , CodeBook.LIKE_HQL_ID);
			filterField.put("filedesc" , CodeBook.LIKE_HQL_ID);
			filterField.put("filetype" , CodeBook.LIKE_HQL_ID);
			filterField.put("filename" , CodeBook.LIKE_HQL_ID);
			filterField.put(CodeBook.ORDER_BY_HQL_ID,"filecode desc");
		}
		return filterField;
	} 
	
    /**
     * 根据消息编号获取文件信息
     * @param msgcode
     * @return
     */
    public List<FileInfo> listFilesByMsg(Long msgcode){
        String baseHQL = "from FileInfo f where f.fileCode in (select cid.filecode from Msgannex where cid.msgcode = ?)";
        return this.listObjects(baseHQL,msgcode);
    }
}
