package com.centit.sys.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.sys.po.MyHome;
import com.centit.sys.po.Usersetting;


public interface UserSettingManager extends BaseEntityManager<Usersetting>
{
    /**
     * 根据用户code获取我的首页展示内容
     * 
     * @param code
     * @return
     */
    public List<MyHome> queryMyHomeByUsercode(String code);

}

