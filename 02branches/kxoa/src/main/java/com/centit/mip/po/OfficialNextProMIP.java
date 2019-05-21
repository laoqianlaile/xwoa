package com.centit.mip.po;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

/**
 * create by scaffold
 * 
 * {stepid ,..userlist[{userrolr,..userinfo[{userid,..}..]}...]}
 * 
 * @author codefan@hotmail.com
 */

public class OfficialNextProMIP {

    private static final long serialVersionUID = 1L;

    
    @Expose
    @Since(1.0)
    private String stepid;// 邮件id
    @Expose
    @Since(1.0)
    private String stepname;// 邮件标题

    @Expose
    @Since(1.0)
    public List<UserListMIP> userlist = new ArrayList<UserListMIP>();
    @Expose
    @Since(1.0)
    public List<DeptListMIP> deptlist = new ArrayList<DeptListMIP>();

    @Expose(serialize = false)
    private String userid;// 人员id
    @Expose(serialize = false)
    private String messageitemguid;// djid
    @Expose(serialize = false)
    private String type;// 公文类型
    @Expose(serialize = false)
    private String nodeinstid;// 节点实例id

    
    private String canSendmessg;
    
    
    public static class UserListMIP {
        
        @Expose
        @Since(1.0)
        private String userLable;//移动端显示名称
        @Expose
        @Since(1.0)
        private String userRoleType;
        @Expose
        @Since(1.0)
        private String limit;// （可选个数限制 。“”为不限制）

        @Expose
        @Since(1.0)
        public List<UserInfoMIP> userinfo;

        public UserListMIP() {
        }

        public UserListMIP(String userLable,String userRoleType, String limit,
                List<UserInfoMIP> userinfo) {
            this.userLable=userLable;
            this.userRoleType = userRoleType;
            this.limit = limit;
            this.userinfo = userinfo;
        }

        public String getUserRoleType() {
            return userRoleType;
        }

        public void setUserRoleType(String userRoleType) {
            this.userRoleType = userRoleType;
        }

        public String getLimit() {
            return limit;
        }

        public void setLimit(String limit) {
            this.limit = limit;
        }

        public List<UserInfoMIP> getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(List<UserInfoMIP> userinfo) {
            this.userinfo = userinfo;
        }

        public String getUserLable() {
            return userLable;
        }

        public void setUserLable(String userLable) {
            this.userLable = userLable;
        }
        

    }

    public static class DeptListMIP {
        
        @Expose
        @Since(1.0)
        private String deptLable;//移动端显示名称
        @Expose
        @Since(1.0)
        private String deptRoleType;
        @Expose
        @Since(1.0)
        private String limit;

        @Expose
        @Since(1.0)
        public List<DeptInfoMIP> deptinfo;

        public DeptListMIP() {
        }

        public DeptListMIP(String deptLable,String deptRoleType, String limit,
                List<DeptInfoMIP> deptinfo) {
            this.deptLable=deptLable;
            this.deptRoleType = deptRoleType;
            this.limit = limit;
            this.deptinfo = deptinfo;

        }

        public String getDeptRoleType() {
            return deptRoleType;
        }

        public void setDeptRoleType(String deptRoleType) {
            this.deptRoleType = deptRoleType;
        }

        public String getLimit() {
            return limit;
        }

        public void setLimit(String limit) {
            this.limit = limit;
        }

        public List<DeptInfoMIP> getDeptinfo() {
            return deptinfo;
        }

        public void setDeptinfo(List<DeptInfoMIP> deptinfo) {
            this.deptinfo = deptinfo;
        }

        public String getDeptLable() {
            return deptLable;
        }

        public void setDeptLable(String deptLable) {
            this.deptLable = deptLable;
        }

    }

    public static class UserInfoMIP {
        @Expose
        @Since(1.0)
        private String userid;
        @Expose
        @Since(1.0)
        private String username;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

    }

    public static class DeptInfoMIP {
        @Expose
        @Since(1.0)
        private String deptid;
        @Expose
        @Since(1.0)
        private String deptname;

        public String getDeptid() {
            return deptid;
        }

        public void setDeptid(String deptid) {
            this.deptid = deptid;
        }

        public String getDeptname() {
            return deptname;
        }

        public void setDeptname(String deptname) {
            this.deptname = deptname;
        }

    }

    public String getStepid() {
        return stepid;
    }

    public void setStepid(String stepid) {
        this.stepid = stepid;
    }

    public String getStepname() {
        return stepname;
    }

    public void setStepname(String stepname) {
        this.stepname = stepname;
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

    public List<UserListMIP> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<UserListMIP> userlist) {
        this.userlist = userlist;
    }

    public List<DeptListMIP> getDeptlist() {
        return deptlist;
    }

    public void setDeptlist(List<DeptListMIP> deptlist) {
        this.deptlist = deptlist;
    }

    public String getCanSendmessg() {
        return canSendmessg;
    }

    public void setCanSendmessg(String canSendmessg) {
        this.canSendmessg = canSendmessg;
    }

}
