package com.centit.dispatchdoc.po;

import java.util.Date;

import com.centit.powerruntime.po.OptBaseInfo;

/**
 * create by scaffold
 * 
 * @author gy
 */

public class Oaleadersort implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String lid;//主键
    private String usercode;//用户编码
    private String stagecode;//环节编码
    private String isvalid;//状态位
    private String leaderorder;//排序号
    private String teamrolecode;//办件角色
    private Long nodeInstId;//节点实例ID
   

    // Constructors
    /** default constructor */
    public Oaleadersort() {
    }

   
    /** full constructor 
     * @param stagecode 
     * @param usercode 
     * @param isvalid 
     * @param leaderorder 
     * @param teamrolecode */

    public Oaleadersort(String lid, String stagecode, String usercode, String isvalid, String leaderorder, String teamrolecode,
            Long nodeInstId) {

        this.lid = lid;
        this.usercode = usercode;
        this.stagecode = stagecode;
        this.isvalid = isvalid;
        this.leaderorder = leaderorder;
        this.teamrolecode = teamrolecode;
        this.nodeInstId = nodeInstId;
       
    }

    public Oaleadersort(String usercode, String stagecode, String isvalid, String leaderorder, String teamrolecode,Long nodeInstId) {
        super();
        this.lid = lid;
        this.usercode = usercode;
        this.stagecode = stagecode;
        this.isvalid = isvalid;
        this.leaderorder = leaderorder;
        this.teamrolecode = teamrolecode;
        this.nodeInstId = nodeInstId;
    }



    public String getLid() {
        return lid;
    }


    public void setLid(String lid) {
        this.lid = lid;
    }


    public String getUsercode() {
        return usercode;
    }


    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }


    public String getStagecode() {
        return stagecode;
    }


    public void setStagecode(String stagecode) {
        this.stagecode = stagecode;
    }


    public String getIsvalid() {
        return isvalid;
    }


    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }


    public String getLeaderorder() {
        return leaderorder;
    }


    public void setLeaderorder(String leaderorder) {
        this.leaderorder = leaderorder;
    }


    public String getTeamrolecode() {
        return teamrolecode;
    }


    public void setTeamrolecode(String teamrolecode) {
        this.teamrolecode = teamrolecode;
    }

    public Long getNodeInstId() {
        return nodeInstId;
    }


    public void setNodeInstId(Long nodeInstId) {
        this.nodeInstId = nodeInstId;
    }


    public void copy(Oaleadersort other) {

        this.setLid(other.getLid());
        this.setIsvalid(other.getIsvalid());
        this.setLeaderorder(other.getLeaderorder());
        this.setStagecode(other.getStagecode());
        this.setTeamrolecode(other.getTeamrolecode());
        this.setUsercode(other.getUsercode());
        this.setNodeInstId(other.getNodeInstId());
    }

    public void copyNotNullProperty(Oaleadersort other) {

        if (other.getLid() != null)
            this.setLid(other.getLid());

       
    }

  

    public void clearProperties() {

        this.usercode = null;
       
    }

 
    
}
