package com.centit.oa.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.centit.bbs.po.OaLeaveMessagereply;
import com.centit.bbs.service.OaLeaveMessagereplyManager;
import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.DwzTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaLeaveMessage;
import com.centit.oa.service.OaHelpinfoManager;
import com.centit.oa.service.OaLeaveMessageManager;
import com.centit.sys.security.FUserDetail;
	

public class OaLeaveMessageAction  extends BaseEntityDwzAction<OaLeaveMessage>  {
	@SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(OaLeaveMessageAction.class);
	private static final long serialVersionUID = 1L;
	private OaLeaveMessageManager oaLeaveMessageMag;
	private OaLeaveMessagereplyManager oaLeaveMessagereplyManager;
	private OaHelpinfoManager oaHelpinfoManager;
	
	public void setOaLeaveMessagereplyManager(
            OaLeaveMessagereplyManager oaLeaveMessagereplyManager) {
        this.oaLeaveMessagereplyManager = oaLeaveMessagereplyManager;
    }


    public OaHelpinfoManager getOaHelpinfoManager() {
        return oaHelpinfoManager;
    }


    public void setOaHelpinfoManager(OaHelpinfoManager oaHelpinfoManager) {
        this.oaHelpinfoManager = oaHelpinfoManager;
    }


    public void setOaLeaveMessageManager(OaLeaveMessageManager basemgr)
	{
		oaLeaveMessageMag = basemgr;
		this.setBaseEntityManager(oaLeaveMessageMag);
	}

	 private String s_infoType;//留言类型
	 private String s_djid;//业务流水号
	 
	 private String backcolumnType;
	 
	   @Override
	    public String save() {
	       FUserDetail user = (FUserDetail) getLoginUser();
	       //留言虽然只有新增，还是保留可能编辑的情况
	       if(StringUtils.isEmpty(object.getNo())){
	           object.setCreater(user.getUsercode());//留言人
	           object.setCreatertime(new Date());//留言日期
	           object.setState("T");//审核状态 默认通过（无审核功能）
	           if(StringUtils.isNotBlank(s_djid)&&s_djid.indexOf("HELP")>-1){
	               oaHelpinfoManager.updateAfterReply(s_djid);
	           }
/*	           //更新帖子回复数，更新子模块帖子数
	           if("BBS".equals(s_infoType)){
//	               oaHelpinfoManager.updateAfterReply(s_djid);
               }*/
	           
	           object.setInfoType(s_infoType);//info_type
	           object.setDjid(s_djid);
	           
	       }
	       OaLeaveMessage dbObject = oaLeaveMessageMag.getObject(object);
           if (dbObject != null) {
               oaLeaveMessageMag.copyObjectNotNullProperty(dbObject, object);
               object = dbObject;
           }
           oaLeaveMessageMag.saveObject(object);
	       return replayList();
	   }
	   
	public String replayNew(){
	    try {
            //edit
            object = getEntityClass().newInstance();
            //list
            @SuppressWarnings("unchecked")
            //infoType;留言类型djid;业务流水号
             Map<String, Object> filterMap = convertSearchColumn(request
                     .getParameterMap());
            filterMap.put("state", "T");//审核通过的留言
            PageDesc pageDesc =null;
           if(StringUtils.isNotBlank(s_djid)&&s_djid.indexOf("HELP")>-1){
               //重新构造PageDesc
                pageDesc = this.makeSearchPageDesc(request);
            }else{
                pageDesc = DwzTableUtils.makePageDesc(this.request);
            }
             objList = baseEntityManager.listObjects(filterMap, pageDesc);
             for(OaLeaveMessage o :objList){
                 filterMap.put("lno", o.getNo());
                 filterMap.put("state", "N");
                 List<OaLeaveMessagereply> infos = oaLeaveMessagereplyManager.listObjects(filterMap);
                 if(infos!=null&&infos.size()>0){
                     o.setOaLeaveMessagereplys(infos);
                 }
             }
             totalRows = pageDesc.getTotalRows();
             if(StringUtils.isNotBlank(s_djid)&&s_djid.indexOf("HELP")>-1){
                 this.pageDesc = pageDesc;
                 String columnType=request.getParameter("backcolumnType");
                 request.setAttribute("backcolumnType",columnType );
                 return "replayList1";
             }
             
             if("BBS".equals(s_infoType)){
                 this.pageDesc = pageDesc;
                 return "bbsReplayList";
             }
             else
             return "replayList"; 
         } catch (Exception e) {
             e.printStackTrace();
             return ERROR;
         } 
	}   
    public String replayList(){
        FUserDetail user = (FUserDetail) getLoginUser();
        request.setAttribute("user", user);
        this.replayNew();
        return "replayListNew"; 
	   }
	   public  PageDesc makeSearchPageDesc(HttpServletRequest request) {
	        
	        String pagesize = request.getParameter("numPerPage");
	        String pageno = request.getParameter("pageNum");
	        String offset = request.getParameter("pager.offset");
	        int pageSize = isNumber(pagesize) ? Integer.parseInt(pagesize) : 10;
	        int pageNo = Integer.parseInt(isNumber(pageno) ? pageno : "1");
	        if(StringUtils.isNotBlank(offset) && !StringUtils.isNotBlank(pageno) && isNumber(offset)) {
	            int offsetNum = Integer.parseInt(offset);
	            
	            pageNo = (offsetNum / pageSize) + 1;
	        }

	        PageDesc pageDesc = new PageDesc(pageNo, pageSize);

	        return pageDesc;

	    }
	      private static boolean isNumber(String input) {
	          if (null == input) {
	              return false;
	          }
	          return Pattern.matches("\\d+", input);
	      }
	      
	      
	public void deleteMessage() throws IOException{
	    String replaynos=request.getParameter("replaynos");
	  
        if (StringUtils.isNotEmpty(replaynos)) {
          //可能为OA_LEAVE_MESSAGEREPLY /OA_LEAVE_MESSAGE 中数据
            for (String replayno: replaynos.split(",")) {
               
               OaLeaveMessage leaveMessage=oaLeaveMessageMag.getObjectById(replayno);
               OaLeaveMessagereply leaveReply=oaLeaveMessagereplyManager.getObjectById(replayno);
                if(null!=leaveMessage&&StringUtils.isNotBlank(leaveMessage.getNo())){
                    leaveMessage.setState("D");
                    oaLeaveMessageMag.saveObject(leaveMessage);
                }else if(null!=leaveReply&&StringUtils.isNotBlank(leaveReply.getLyno())){
                    leaveReply.setState("D");
                    oaLeaveMessagereplyManager.saveObject(leaveReply);
                }
                
            }
           
        }
	    
	    ServletActionContext.getResponse().getWriter().print("true");
	}
	/**
	 * 删除留言--state状态置D
	 * @return
	 */
   public String deleteReplay(){
        
        super.delete();
        
        return "deleteMessage";
    }

    public String getS_infoType() {
        return s_infoType;
    }


    public void setS_infoType(String s_infoType) {
        this.s_infoType = s_infoType;
    }


    public String getS_djid() {
        return s_djid;
    }


    public void setS_djid(String s_djid) {
        this.s_djid = s_djid;
    }


    public String getBackcolumnType() {
        return backcolumnType;
    }


    public void setBackcolumnType(String backcolumnType) {
        this.backcolumnType = backcolumnType;
    }
    
    
		
}
