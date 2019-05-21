package com.centit.webservice.util;
/**
 * 约定常量
 * TODO Class description should be added
 * 
 * @author lq
 * @create 2017年2月6日
 * @version
 */
public interface CommonOptCodeUtil {
    
    //短信运营商
    public static final String SMSCONFIG_OPERATORSOURCE_YD = "YD";
    
    public static final String SMSCONFIG_OPERATORSOURCE_DX = "DX";
    
    
    //顶级机构
    public static final String TOP_UNITCODE = "881";//因太极同步过来顶级机构编号为881
    
    
  /*  //顶级机构
    public static final String TOP_UNITCODE = "1";*/
    
    //机关名义
    /**
     * 公文性质-顶级
     */
    public static final String GW_NATURE_SUP = "01";
    
    /**
     * 公文性质-部门
     */
    public static final String GW_NATURE_DEP = "02";
    
    /**
     * 公文性质-顶级-(指定部门可以查询顶级机构公文)
     */
    public static final String GW_NATURE_SUP_UNITCODE = "882";//因太极同步过来党政办公室编号为882


  /*  *//**
     * 公文性质-顶级-(指定部门可以查询顶级机构公文)
     *//*
    public static final String GW_NATURE_SUP_UNITCODE = "001661";*/
    
    
    //流程对应关系
    /**
     * 会议申请流程
     */
    public static final String FLOWCODE_MEET_APPLY = "000857";
    
    /**
     * 车辆申请流程
     */
    public static final String FLOWCODE_CAR_APPLY = "000858";
    
    /**
     * 请假流程
     */
    public static final String FLOWCODE_LEAVE_APPLY = "001272";
    
    /**
     * 自助餐流程
     */
    public static final String FLOWCODE_BUFFET_APPLY = "001273";
    
    /**
     * 物品申领流程
     */
    public static final String FLOWCODE_OFFICESUPPLIES = "001273";
    
    /**
     * 公示公告入网流程
     */
    public static final String FLOWCODE_NETAPPLICATION = "001273";
    
    /**
     * 外出请假报备流程
     */
    public static final String FLOWCODE_LEAVEREPORTED = "001350";
    
   
    
    //对应权利编号
    
  
    
    /**
     * 请假流程
     */
    public static final String ITEMID_LEAVE_APPLY = "QJ-SQ-002";
    
    /**
     * 自助餐流程
     */
    public static final String ITEMID_BUFFET_APPLY = "SQ-SQ-001";
    
    /**
     * 物品申领流程
     */
    public static final String ITEMID_OFFICESUPPLIES = "WPSL-SQ-001";
    
    /**
     * 公示公告入网流程
     */
    public static final String ITEMID_NETAPPLICATION = "RWSQ-SQ-001";
    
    /**
     * 外出请假报备流程
     */
    public static final String ITEMID_LEAVEREPORTED = "WCQJ-SQ-001";
    
    
    
    //对应流程APPLY_ITEM_TYPE
    /**
     * 请假流程
     */
    public static final String APPLY_ITEM_TYPE_QJ = "oaLeaveapply";
    /**
     * 自助餐流程
     */
    public static final String APPLY_ITEM_TYPE_ZZC = "oaBuffetapply";
    /**
     * 物品申领流程
     */
    public static final String APPLY_ITEM_TYPE_WPSL = "oaOfficesupplies";
    /**
     * 公示公告入网流程
     */
    public static final String APPLY_ITEM_TYPE_RW = "oaNetapplication";
    /**
     * 外出请假报备流程
     */
    public static final String APPLY_ITEM_TYPE_WCQJ = "oaLeavereported";
    
    
    
    //论坛版块对应关系
    
    /**
     * 生活服务版块
     */
    public static final String BBS_DASHBOARD_SHFW = "PM000000000083";
    
    
    /**
     * 出行
     */
    public static final String BBS_DISCUSSION_CX = "SM000000000065";
    
    
    /**
     * 住房
     */
    public static final String BBS_DISCUSSION_ZF = "SM000000000066";
    
    /**
     * 商品
     */
    public static final String BBS_DISCUSSION_SP = "SM000000000067";
    
    /**
     * 娱乐
     */
    public static final String BBS_DISCUSSION_YL = "SM000000000068";
    
    /**
     * 休闲
     */
    public static final String BBS_DISCUSSION_XX = "SM000000000069";
    
    /**
     * 其他
     */
    public static final String BBS_DISCUSSION_OT = "SM000000000081";
  
}
