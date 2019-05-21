package com.centit.app.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

import rtx.RTXSvrApi;

import com.centit.app.dao.RtxInfoDao;
import com.centit.app.po.RtxInfo;
import com.centit.app.service.RtxInfoManager;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.dispatchdoc.dao.VuserTaskListOADao;
import com.centit.sys.action.UnitAction;
import com.centit.sys.action.UserDefAction;
import com.centit.sys.job.ClearFileJob;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.MessageSender;

public class RtxInfoManagerImpl extends BaseEntityManagerImpl<RtxInfo>
implements RtxInfoManager{
    private Logger log = Logger.getLogger(ClearFileJob.class);
    private RtxInfoDao rtxInfoDao ;
    private Map<String,MessageSender> msgSenders;
    
    private VuserTaskListOADao vuserTaskListOADao ;
    
    public Map<String, MessageSender> getMsgSenders() {
        return msgSenders;
    }

    public void setMsgSenders(Map<String, MessageSender> msgSenders) {
        this.msgSenders = msgSenders;
    }

    public void setRtxInfoDao(RtxInfoDao rtxInfoDao) {
        this.rtxInfoDao = rtxInfoDao;
        setBaseDao(this.rtxInfoDao);
    }

    @Override
    public String getNextkey() {
        // TODO Auto-generated method stub
        return rtxInfoDao.getNextKeyBySequence("S_RtxInfo",14);
    }

    @Override
    public boolean sendRtx(String isSend) {
        Map filterMap = new HashMap();
        filterMap.put("isSend", isSend);
        List<RtxInfo> list = rtxInfoDao.listObjects(filterMap);
        String expireNoticeType = CodeRepositoryUtil.getValue("WFNotice", "type");
        MessageSender sender = msgSenders.get(expireNoticeType);
          if(sender==null){
              //log.error("找不到消息发送器，请检查Spring中的配置和数据字典 WFNotice中的配置是否一致。");            
              return false;
          }
          if(list!=null && list.size()>0){
          for(RtxInfo info : list){
              //判断消息对于待办是否有效
              boolean flag=false;
            if(info.getNodeId()==null || vuserTaskListOADao.checkUserTask(info.getNodeId(),info.getReceiveUserCode())){
             try{
                RTXSvrApi  RtxsvrapiObj = new RTXSvrApi();              
              if(RtxsvrapiObj.Init()){
              int iState =RtxsvrapiObj.QueryUserState(info.getReceiveUserName());//获取接收人登录状态
              if(1==iState||2==iState){//在线或者离开时发送消息
                  sender.sendMessage(info.getCreateUserCode()/*系统管理员*/,
                          info.getReceiveUserName(), "您有一个新待办：", 
                          info.getInfoContent());
                  flag=true;
                }
              }
              RtxsvrapiObj.UnInit();
             }catch (Exception e) {
                 log.info("=============RTX 相关服务异常===========");
            }
            }
            
            if(flag){
              info.setIsSend("1");
              info.setSendDate(new Date(System.currentTimeMillis()));           
              rtxInfoDao.save(info);     
            }
            
          }
          }
        return false;
    }

    public VuserTaskListOADao getVuserTaskListOADao() {
        return vuserTaskListOADao;
    }

    public void setVuserTaskListOADao(VuserTaskListOADao vuserTaskListOADao) {
        this.vuserTaskListOADao = vuserTaskListOADao;
    }

    /**
     * 同步人员信息到rtx
     * 新增：初始密码 为 null
     * 启用 禁用
     */
    public void saveUserInfo(JoinPoint pjp){
        UserDefAction action = (UserDefAction) pjp.getTarget();
        FUserinfo userInfo=action.getObject();//用户信息
        FUserunit userUnit=action.getUserUnit();//用户机构信息
        
        if(null!=userInfo && userUnit!=null ){
        
        /*用户基本信息 beg*/
        String UserName=userInfo.getLoginname();
        String DeptID=userUnit.getUnitcode();
        String ChsName=userInfo.getUsername();
        String Pwd="8888";//初始密码
        String isvalid=userInfo.getIsvalid();//是否启用
        /*用户基本信息 end*/
        
        RTXSvrApi  RtxsvrapiObj = new RTXSvrApi();              
        if( RtxsvrapiObj.Init()){
            //判断用户信息是否存在 int 0:离线 1:在线 2:离开 -984:不存在该用户 其它:未知状态
            //新增
            if("T".equals(isvalid)&&-984==(RtxsvrapiObj.QueryUserState( UserName))){
//                RtxsvrapiObj.addUser( UserName, DeptID,  ChsName,  Pwd );
               int iRet =RtxsvrapiObj.addUser(UserName, DeptID,  ChsName,  Pwd);
                if (iRet==0)
                {
                    System.out.println("添加成功");
                }
                else 
                {
                    System.out.println("添加失败");
                }
            }
           //禁用
            if("F".equals(isvalid)&&-984!=(RtxsvrapiObj.QueryUserState( UserName))){
                RtxsvrapiObj.deleteUser( UserName) ;
                
            }
        }
        RtxsvrapiObj.UnInit();
       } 
        
    }
    

    /**
     * 同步机构信息到rtx
     * 新增：
     * 启用 禁用
     */
    public void saveUnitInfo(JoinPoint pjp){
        UnitAction action = (UnitAction) pjp.getTarget();
        FUnitinfo unitInfo=action.getObject();//机构信息
        
        if(null!=unitInfo){
       
        /*机构基本信息 beg*/
        String DeptID=unitInfo.getUnitcode();
        String DetpInfo=unitInfo.getUnitdesc();
        String DeptName=unitInfo.getUnitname();
        String ParentDeptId=unitInfo.getParentunit();
        String isvalid=unitInfo.getIsvalid();//是否启用
        /*机构基本信息 end*/
        
        
        RTXSvrApi  RtxsvrapiObj = new RTXSvrApi();              
        if( RtxsvrapiObj.Init()){
            //int 0:存在 非0:不存在
            //新增
            if("T".equals(isvalid)){
                int iRet=RtxsvrapiObj.addDept(DeptID,DetpInfo, DeptName,ParentDeptId );
                RtxsvrapiObj.UnInit();
                if (iRet==0)
                {
                    System.out.println("添加成功");
                }
                else 
                {
                    System.out.println("添加失败");
                }
            }
           //禁用
            if("F".equals(isvalid)){
                RtxsvrapiObj.deleteDept( DeptID, "1" ) ;//String 0:只删除当前组织 1:删除下级组织及用户
                RtxsvrapiObj.UnInit();
            }
         }
       }  
    } 
    
    public void demandRefund(){
        System.out.println("Boo! We want money back");
    }
    
}
