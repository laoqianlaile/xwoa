package com.centit.oa.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaPowergroupDetail;
import com.centit.oa.po.OaPowerrolergroup;

public interface OaPowergroupDetailManager extends BaseEntityManager<OaPowergroupDetail> 
{
    public void saveDetails(OaPowerrolergroup rolergroup,String usercodes);
    /**
     * 批量删除某个分组详情
     * 
     * @param rolergroup
     */
    public void deleteDetails(OaPowerrolergroup rolergroup);
    /**
     * 根据权限分组No获取分组详细
     * @param no
     * @return
     */
    public List<String> listUsercodesByNo(String no) ;

    /**
     * *根据权限分组No获取分组详细的usercode
     * @param rolergroup
     * @return
     */
    public List<OaPowergroupDetail> getUserlist(OaPowerrolergroup rolergroup);
    /**
     * 根據當前登錄人員获取个人及分组所有的详情
     * @param usercode
     * @return
     */
    public List<OaPowergroupDetail> getDetailsByUsercode(String usercode);
}
