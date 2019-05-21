package com.centit.dispatchdoc.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.dispatchdoc.po.DocSend;

public interface DocSendManager extends BaseEntityManager<DocSend> {
    public String getNextkey();
}
