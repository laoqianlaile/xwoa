package com.centit.powerbase.util;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;

public class MonitorOptLogger implements IMonitorOptLog, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -1213704398618624206L;

    public static final Log log = LogFactoryImpl.getLog(MonitorOptLogger.class);

    private String optId;

    public MonitorOptLogger(String optId) {
        this.optId = optId;
    }




    @Override
    public void log(String usercode, String tagId, String optContent, String oldValue, String bjType) {
        this.log(usercode, this.optId, tagId, Thread.currentThread()
                .getStackTrace()[2].getMethodName(), optContent, oldValue,
                bjType);

    }

    @Override
    public void log(String usercode, String tagId, String optContent, String bjType) {
        this.log(usercode, this.optId, tagId, Thread.currentThread()
                .getStackTrace()[2].getMethodName(), optContent, null,bjType);

    }




    @Override
    public void log(String usercode, String optId, String tagId,
            String optMethod, String optContent, String oldValue, String bjType) {
        // TODO Auto-generated method stub
        
    }


}
