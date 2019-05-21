package com.centit.oa.action;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.oa.po.OptFilingCabinets;
import com.centit.oa.service.OptFilingCabinetsManager;
import com.centit.sys.util.SysParametersUtils;

public class OptFilingCabinetsAction extends BaseEntityExtremeAction<OptFilingCabinets> {
   
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
   
    private InputStream inputStream;
    
    private String filename;
    
    private OptFilingCabinetsManager optFilingCabinetsMgr;
    
    
    public OptFilingCabinetsManager getOptFilingCabinetsMgr() {
        return optFilingCabinetsMgr;
    }


    public void setOptFilingCabinetsMgr(
            OptFilingCabinetsManager optFilingCabinetsMgr) {
        this.optFilingCabinetsMgr = optFilingCabinetsMgr;
        super.setBaseEntityManager(optFilingCabinetsMgr);
    }
    
    public InputStream getInputStream() {
        return inputStream;
    }
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }


    public String getFilename() {
        return filename;
    }


    public void setFilename(String filename) {
        this.filename = filename;
    }


    public String downloadAffix(){
        String attachmentId = request.getParameter("id");
        OptFilingCabinets optFilingCabinets = optFilingCabinetsMgr.getObjectById(Long.valueOf(attachmentId));
        try{
            this.setFilename(new String(optFilingCabinets.getFileName().getBytes("GBK"), "ISO8859-1"));
            File file = new File(SysParametersUtils.getFilingCabinetsHome() + optFilingCabinets.getFilePath());
            
            
            InputStream stuffStream = FileUtils.openInputStream(file);
            this.setInputStream(stuffStream);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
        }
        return "downloadfile";
    }

}
