package com.centit.dispatchdoc.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VTransactUser;
import com.centit.dispatchdoc.po.VoadDcdbNum;
import com.centit.dispatchdoc.po.VuserTaskListOA;


public interface VuserTaskListOAManager extends BaseEntityManager<VuserTaskListOA> 
{

 public   List<VoadDcdbNum> getdcdbnum(Map<String, Object> filterMap,
            PageDesc pageDesc);
 public boolean checkUserTask(Long nodeInstId, String userCode);
 
 /**
  * 获取办件提醒状态：收发文添加办件是否超时提醒
  * @param o
  */
 public String getOverDueState(VuserTaskListOA o);
 
 /**
  * 获取当前步骤中待办及已办的人员
  * @param nodeinstId
  * @return
  */
 public List<VTransactUser> getCurrentTransactUsers(String djId);
 
 /**
  * 获取办件当前的办理步骤，如果有多个的，就用逗号隔开
  * @param djId
  * @return
  */
 public String getCurrentNodeNames(String djId);
 
 /**
  * 获取办件当前的节点nodeInstId，如果有多个的，就用逗号隔开
  * @param djId
  * @return
  */
 public String getCurrentNodeInstId(String djId);
 
}
