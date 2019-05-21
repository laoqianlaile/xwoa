package com.centit.dispatchdoc.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.dispatchdoc.po.DocSend;
import com.centit.dispatchdoc.dao.DocSendDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.dispatchdoc.service.DocSendManager;

public class DocSendManagerImpl extends BaseEntityManagerImpl<DocSend>
        implements DocSendManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(DocSendManager.class);

    private DocSendDao docSendDao;

    public void setDocSendDao(DocSendDao baseDao) {
        this.docSendDao = baseDao;
        setBaseDao(this.docSendDao);
    }

    
    public String getNextkey() {
        return docSendDao.getNextKeyBySequence("S_DOC_SEND", 8);
    }
}
