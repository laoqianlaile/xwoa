 package com.centit.powerbase.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerbase.po.Pcfreeumpiretype;
 
public interface PcfreeumpiretypeManager extends BaseEntityManager<Pcfreeumpiretype> 
{
 
    public List<Pcfreeumpiretype> listFreeumpiretype(Long degreeno);
    public List<Pcfreeumpiretype> getPCFreeUmpireTypeListByClassID(String itemId,Long version,String punishtypeid);
    
    public void  updatepcfreeumpiretype( long degreeno,String punishtypeid,long isinuse);
    public boolean ifHavePCFreeUmpireTypeRate(String punishtypeid, String degreeno);
}
