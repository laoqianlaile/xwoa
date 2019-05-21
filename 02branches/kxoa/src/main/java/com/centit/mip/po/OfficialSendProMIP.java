package com.centit.mip.po;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

/**
 * 发送公文处理结果
 * {
    "paras":{"userid":"用户唯一ID","messageitemguid":"工作项的唯一消息id", "nodeinstid":"节点实例id",
        "nextsteplist":[{
                "nextstepids":"步骤ID:值（T,F,B 办理意见）",
                "userlist":[{
                        "userrole":"人员类型","nextpersonid":"usercode1，usercode2，。。。。）"
                    } ],
                "deptlist":[{
                        "deptrole":"部门类型","nextunitinfo":"unitcode1，unitcode2，。。。）"
                    }]
            }]
    }.....
}
 */

public class OfficialSendProMIP {

    private static final long serialVersionUID = 1L;

   
   
    @Expose
    public List<NextStepListMIP> nextsteplist = new ArrayList<NextStepListMIP>();

    
    @Expose(serialize = false)
    private String userid;// 人员id
    @Expose(serialize = false)
    private String messageitemguid;// djid
    @Expose(serialize = false)
    private String nodeinstid;// 节点实例id
    @Expose(serialize = false)
    private String opinion;// 处理意见
    @Expose(serialize = false)
    private String type;// 公文类型
    @Expose(serialize = false)
    private String docfileurl;// pdf下载地址
    @Expose(serialize = false)
    private String docfileid;// 原加密文件id
    
    @Expose(serialize = false)
    private String sendmessg;//是否发送短信
    public static class NextStepListMIP {
        
        @Expose
        private String nextstepids;// 步骤ID:值（T,F,B 办理意见）
        @Expose
        private String nextstepnames;// 步骤名称:值（提交部门领导）
        
        @Expose
        public List<NextUserListMIP> userlist = new ArrayList<NextUserListMIP>();
        @Expose
        public List<NextDeptListMIP> deptlist = new ArrayList<NextDeptListMIP>();
        public String getNextstepids() {
            return nextstepids;
        }
        public void setNextstepids(String nextstepids) {
            this.nextstepids = nextstepids;
        }
        public List<NextUserListMIP> getUserlist() {
            return userlist;
        }
        public void setUserlist(List<NextUserListMIP> userlist) {
            this.userlist = userlist;
        }
        public List<NextDeptListMIP> getDeptlist() {
            return deptlist;
        }
        public void setDeptlist(List<NextDeptListMIP> deptlist) {
            this.deptlist = deptlist;
        }
        public String getNextstepnames() {
            return nextstepnames;
        }
        public void setNextstepnames(String nextstepnames) {
            this.nextstepnames = nextstepnames;
        }
        
        
        

    }

    public static class NextUserListMIP {
        @Expose
        @Since(1.0)
        private String userRole;

        @Expose
        @Since(1.0)
        public String nextpersonid;//"usercode1，usercode2，。。。。

        public NextUserListMIP() {
        }

        public NextUserListMIP(String userRole, String nextpersonid  ) {
            this.userRole = userRole;
            this.nextpersonid = nextpersonid;
        }

        public String getUserRole() {
            return userRole;
        }

        public void setUserRole(String userRole) {
            this.userRole = userRole;
        }

        public String getNextpersonid() {
            return nextpersonid;
        }

        public void setNextpersonid(String nextpersonid) {
            this.nextpersonid = nextpersonid;
        }

       

       
    }

    public static class NextDeptListMIP {
        @Expose
        @Since(1.0)
        private String deptRole;
        

        @Expose
        @Since(1.0)
        public String nextunitinfo;

        public NextDeptListMIP() {
        }

        public NextDeptListMIP(String deptRole, String nextunitinfo) {
            this.deptRole = deptRole;
            this.nextunitinfo = nextunitinfo;
        }

        public String getDeptRole() {
            return deptRole;
        }

        public void setDeptRole(String deptRole) {
            this.deptRole = deptRole;
        }

        public String getNextunitinfo() {
            return nextunitinfo;
        }

        public void setNextunitinfo(String nextunitinfo) {
            this.nextunitinfo = nextunitinfo;
        }
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMessageitemguid() {
        return messageitemguid;
    }

    public void setMessageitemguid(String messageitemguid) {
        this.messageitemguid = messageitemguid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNodeinstid() {
        return nodeinstid;
    }

    public void setNodeinstid(String nodeinstid) {
        this.nodeinstid = nodeinstid;
    }

    public List<NextStepListMIP> getNextsteplist() {
        return nextsteplist;
    }

    public void setNextsteplist(List<NextStepListMIP> nextsteplist) {
        this.nextsteplist = nextsteplist;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getDocfileurl() {
        return docfileurl;
    }

    public void setDocfileurl(String docfileurl) {
        this.docfileurl = docfileurl;
    }

    public String getDocfileid() {
        return docfileid;
    }

    public void setDocfileid(String docfileid) {
        this.docfileid = docfileid;
    }
    public String getSendmessg() {
        return sendmessg;
    }

    public void setSendmessg(String sendmessg) {
        this.sendmessg = sendmessg;
    }
   

   
}
