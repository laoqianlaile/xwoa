package com.centit.oa.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaRemindInformation;

public interface OaRemindInformationManager extends
        BaseEntityManager<OaRemindInformation> {
    public String getNextkey();

    /**
     * 获取已读，未读提醒：type 0：未读1：已读 2：删除
     * 
     * @param filterMap
     * @param pageDesc
     * @param usercode
     * @param type
     * @return
     */
    List<OaRemindInformation> recipientList(Map<String, Object> filterMap,
            PageDesc pageDesc, String usercode);

    /**
     * 通用发送提醒接口
     * 
     * @param djId
     *            业务流水号
     * @param sender
     *            发起者
     * @param reser
     *            接收者
     * @param title
     *            标题
     * @param remark
     *            内容
     * @return
     */
    public boolean sendOaRemindInformation(String djId, String sender,
            String reser, String title, String remark);

    /**
     * 通用发送提醒接口
     * 
     * @param djId
     *            业务流水号
     * @param sender
     *            发起者
     * @param reser
     *            接收者
     * @param title
     *            标题
     * @param remark
     *            内容
     * @param endtime
     *            结束时间
     * @return
     */
    public boolean sendOaRemindInformation(String djId, String sender,
            String reser, String title, String remark, Date endtime);
}
