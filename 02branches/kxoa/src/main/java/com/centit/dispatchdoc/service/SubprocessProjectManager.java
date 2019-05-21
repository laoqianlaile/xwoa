package com.centit.dispatchdoc.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.dispatchdoc.po.SubprocessProject;

public interface SubprocessProjectManager extends BaseEntityManager<SubprocessProject> 
{
    public String generateNextDjId();
    public List<String> getOptBaseNotOverList(String djId);
}
