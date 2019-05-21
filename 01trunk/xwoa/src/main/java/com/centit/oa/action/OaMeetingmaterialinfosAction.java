package com.centit.oa.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.oa.po.OaMeetingmaterialinfos;
import com.centit.oa.service.OaMeetingmaterialinfosManager;
import com.centit.powerruntime.po.OptStuffInfo;
import com.centit.powerruntime.service.OptStuffInfoManager;
import com.centit.sys.util.SysParametersUtils;
	

public class OaMeetingmaterialinfosAction  extends BaseEntityDwzAction<OaMeetingmaterialinfos>  {
	private static final Log log = LogFactory.getLog(OaMeetingmaterialinfosAction.class);
	private static final long serialVersionUID = 1L;
	private OaMeetingmaterialinfosManager oaMeetingmaterialinfosMag;
    private OptStuffInfoManager optStuffInfoManager;
    private InputStream stuffStream;//供下载使用
    private String filename;
	public OptStuffInfoManager getOptStuffInfoManager() {
        return optStuffInfoManager;
    }
    public void setOptStuffInfoManager(OptStuffInfoManager optStuffInfoManager) {
        this.optStuffInfoManager = optStuffInfoManager;
    }
    public InputStream getStuffStream() {
        return stuffStream;
    }
    public void setStuffStream(InputStream stuffStream) {
        this.stuffStream = stuffStream;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public void setOaMeetingmaterialinfosManager(OaMeetingmaterialinfosManager basemgr)
	{
		oaMeetingmaterialinfosMag = basemgr;
		this.setBaseEntityManager(oaMeetingmaterialinfosMag);
	}
	public String view(){
	    super.view();
	    return VIEW;
	}
	
    //跳转页面至下载页
    public String meetingDownFile(){
        
        List<OptStuffInfo> docAttachments =  new ArrayList<OptStuffInfo>();
        String attendCode = request.getParameter("meetingAttendee");
        String djId = request.getParameter("djId");
        
        List<OaMeetingmaterialinfos> stuffIdList = oaMeetingmaterialinfosMag.findStuffIdByCode(attendCode, djId);
        List<OaMeetingmaterialinfos> meetingInfo = new ArrayList<OaMeetingmaterialinfos>();
        if(stuffIdList.size()>0){
                for(int i=0;i<stuffIdList.size();i++){
                    OptStuffInfo meetingStuffAttach = optStuffInfoManager.getObjectById(stuffIdList.get(i).getCid().getStuffId());
                    docAttachments.add(meetingStuffAttach);
                }             
                meetingInfo.add(stuffIdList.get(0));
                if(stuffIdList.get(0).getIsback().equals("F")){
                    meetingInfo.get(0).setIsback("否");
                }else{
                    meetingInfo.get(0).setIsback("是");
                }
        }
        
        request.setAttribute("docAttachments", docAttachments);
        request.setAttribute("meetingInfo", meetingInfo);
        return "meetingDown";
    }

    //附件下载--服务器端下载
    public String downLocalStuffInfo() throws IOException {
        
        OptStuffInfo attachment = optStuffInfoManager.getObjectById(request.getParameter("no"));
        if (null == attachment) {
            return "download";
        }
        String absolutePath = SysParametersUtils.getInfosHome() + attachment.getStuffpath();
        File file = new File(absolutePath);
        
        
        InputStream stuffStream = FileUtils.openInputStream(file);
       
        try {
            setStuffStream(stuffStream);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
        }
        this.setFilename(new String(attachment.getStuffname().getBytes("GBK"), "ISO8859-1"));
        
        return "download";
    }
	
		
}
