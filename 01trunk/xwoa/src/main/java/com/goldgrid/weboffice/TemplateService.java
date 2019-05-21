/*
 * @(#) DocumentService.java
 * Created Date: 2012-7-3
 *				
 * Copyright (c)  Centit Co., Ltd
 *
 * This software is the confidential and proprietary information of
 * Centit Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Centit Co., Ltd.
 */
package com.goldgrid.weboffice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * TODO Class description should be added
 * 
 * @author ljy
 * @version $Rev$ <br>
 *          $Id$
 */
public class TemplateService {
    
    /**
     * 根据属性名列出模版
     * @param ObjType 
     * @param FileType
     * @return
     */
    public static String getTemplate(String objId, String templateType,String fileType) {
        iDBManager2000 DbaObj=new iDBManager2000();
        String mTemplateList, mstr = "";
        mTemplateList = "<select id=" + objId + " name=" + objId + " onChange='openTemplate(this.value);'>";
        mTemplateList = mTemplateList
                + "<option value=''>----请选择----</option>";
        String Sql = "SELECT RECORDID,DESCRIPT FROM TEMPLATE_FILE WHERE ISUSED='1' and FILETYPE='"
                + fileType + "'" + " and TEMPTYPE ='"+templateType+"'"+" ORDER BY ORDERBY"; // 打开数据库
        try {
            if (DbaObj.OpenConnection()) {
                try {
                    ResultSet result = DbaObj.ExecuteQuery(Sql);
                    // mstr="selected";
                    while (result.next()) {
                        mTemplateList = mTemplateList + "<option value='"
                                + result.getString("RecordID") + "'" + mstr
                                + ">" + result.getString("Descript")
                                + "</option>";
                    }
                    result.close();
                } catch (SQLException sqlex) {
                    System.out.println(sqlex.toString());
                }
            } else {
                System.out.println("GetTemplateList: OpenDatabase Error");
            }
        } finally {
            DbaObj.CloseConnection();
        }
        mTemplateList = mTemplateList + "</select>";
        return (mTemplateList);
    }
    /**
     * 功能或作用：格式化日期时间
     * 
     * @param DateValue
     *            输入日期或时间
     * @param DateType
     *            格式化 EEEE是星期, yyyy是年, MM是月, dd是日, HH是小时, mm是分钟, ss是秒
     * @return 输出字符串
     */
    public static String formatDate(String DateValue, String DateType) {
        String Result;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat(DateType);
            Date mDateTime = formatter.parse(DateValue);
            Result = formatter.format(mDateTime);
        } catch (Exception ex) {
            Result = ex.getMessage();
        }
        if (Result.equalsIgnoreCase("1900-01-01")) {
            Result = "";
        }
        return Result;
    }

    public static void saveDocument(String mRecordID, String mTemplate,
            String mSubject, String mAuthor, String mFileDate,
            String mFileType, String mHTMLPath) {

        if (mRecordID == null) {
            mRecordID = "";
        }
        // String mStatus = "READ";

        int mDocumentId = 0;
        iDBManager2000 dbObj = new iDBManager2000();
        if (dbObj.OpenConnection()) {
            String mysql = "SELECT DocumentID,RecordID from  Document Where RecordID='"
                    + mRecordID + "'";
            // String mFileDate=dbObj.GetDateTime() ;
            try {
                ResultSet result = dbObj.ExecuteQuery(mysql);
                if (result.next()) {
                    mysql = "update Document set DocumentID=?,RecordID=?,Template=?,Subject=?,Author=?,FileDate=?,FileType=?,HtmlPath=?,Status=? where RecordID='"
                            + mRecordID + "'";
                    mDocumentId = result.getInt("DocumentID");
                } else {
                    mysql = "insert into Document (DocumentID,RecordID,Template,Subject,Author,FileDate,FileType,HtmlPath,Status) values (?,?,?,?,?,?,?,?,?)";
                    mDocumentId = dbObj.GetMaxID("Document", "DocumentID");
                }
                result.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            java.sql.PreparedStatement prestmt = null;
            try {
                prestmt = dbObj.Conn.prepareStatement(mysql);
                prestmt.setInt(1, mDocumentId);
                prestmt.setString(2, mRecordID);
                prestmt.setString(3, mTemplate);
                prestmt.setString(4, mSubject);
                prestmt.setString(5, mAuthor);
                prestmt.setDate(6, dbObj.GetDate());
                prestmt.setString(7, mFileType);
                prestmt.setString(8, mHTMLPath);
                prestmt.setString(9, "READ");

                dbObj.Conn.setAutoCommit(true);
                prestmt.executeUpdate();
                dbObj.Conn.commit();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    prestmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            dbObj.CloseConnection();
        }
    }
}
