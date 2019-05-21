package com.centit.dispatchdoc.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.dispatchdoc.po.DocRelative;

public interface DocRelativeManager extends BaseEntityManager<DocRelative> {
    public List<DocRelative> getObjectsByDispatchNo(String dispatchNo);

    public List<DocRelative> getObjectsByIncomeNo(String incomeNo);

}
