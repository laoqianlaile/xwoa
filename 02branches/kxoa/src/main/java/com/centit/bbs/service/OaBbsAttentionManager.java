package com.centit.bbs.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.bbs.po.OaBbsAttention;

public interface OaBbsAttentionManager extends BaseEntityManager<OaBbsAttention> 
{
    /**
     * 按laytype对bbsattention进行分类统计
     * @param oaBbsAttention
     * @return
     */
 public List<OaBbsAttention>  getLaytypeNum(OaBbsAttention oaBbsAttention);
}
