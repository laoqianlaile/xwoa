package com.centit.bbs.service;

import java.util.List;
import java.util.Map;

import com.centit.bbs.po.VBbsThemeReplay;
import com.centit.bbs.po.VBbsThemeUser;
import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;

public interface VBbsThemeUserManager extends BaseEntityManager<VBbsThemeUser> 
{
public List<VBbsThemeReplay>   getThemeReplyList(Map<String ,Object> filterMap, PageDesc pageDesc);
}