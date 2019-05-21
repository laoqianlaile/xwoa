package com.centit.app.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.CollectionUtils;

import com.centit.app.po.Forum;
import com.centit.app.po.ForumStaff;
import com.centit.app.po.ForumStaffId;
import com.centit.app.po.ForumType;
import com.centit.app.po.Thread;
import com.centit.app.service.ForumManager;
import com.centit.app.service.ForumStaffManager;
import com.centit.app.service.ThreadManager;
import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.dao.CodeBook;
import com.centit.core.utils.PageDesc;
import com.centit.sys.po.FUserinfo;

public class ForumAction extends BaseEntityDwzAction<Forum> {
	private static final Log log = LogFactory.getLog(ForumAction.class);

	// private static final ISysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog("optid");

	private static final long serialVersionUID = 1L;
	private ForumManager forumMag;

	private ThreadManager threadManager;

	public void setThreadManager(ThreadManager threadManager) {
		this.threadManager = threadManager;
	}

	public void setForumManager(ForumManager basemgr) {
		forumMag = basemgr;
		this.setBaseEntityManager(forumMag);
	}

	private ForumStaffManager forumStaffManager;

	private List<ForumType> forumTypes;

	public List<ForumType> getNewForumTypes() {
		return forumTypes;
	}

	public void setNewForumTypes(List<ForumType> forumTypes) {
		this.forumTypes = forumTypes;
	}

	public void setForumStaffManager(ForumStaffManager forumStaffManager) {
		this.forumStaffManager = forumStaffManager;
	}

	private List<Thread> threads;

	public List<Thread> getNewThreads() {
		return this.threads;
	}

	public void setNewThreads(List<Thread> threads) {
		this.threads = threads;
	}

	private List<ForumStaff> forumStaffs;

	public List<ForumStaff> getNewForumStaffs() {
		return this.forumStaffs;
	}

	public void setNewForumStaffs(List<ForumStaff> forumStaffs) {
		this.forumStaffs = forumStaffs;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> getFilterMap() {
		Map<Object, Object> paramMap = request.getParameterMap();
		resetPageParam(paramMap);

		Map<String, Object> filterMap = convertSearchColumn(paramMap);
		filterMap.put("boardcodeEq", "FORUM");

		String orderField = request.getParameter("orderField");
		String orderDirection = request.getParameter("orderDirection");

		if (!StringUtils.isBlank(orderField) && !StringUtils.isBlank(orderDirection)) {

			filterMap.put(CodeBook.SELF_ORDER_BY, orderField + " " + orderDirection);

		} else {
			filterMap.put(CodeBook.SELF_ORDER_BY, "createtime desc");
		}
		setbackSearchColumn(filterMap);
		return filterMap;
	}

	@Override
	public String list() {
		Map<String, Object> filterMap = getFilterMap();
		filterMap.put("VIEWRIGHT", getLoginUsercode());
		filterMap.put("forumstate", "1");

		listObjects(filterMap);

		// 可以申请加入的讨论版

		PageDesc waitPageDesc = new PageDesc(0, 20);
		
		filterMap.put("JOINRIGHT", getLoginUsercode());
		Map<Forum, Integer> objs = forumMag.listByJoinRight(filterMap, waitPageDesc);
		
		List<Forum> waitJoinForums = new ArrayList<Forum>();
		for (Entry<Forum, Integer> entry : objs.entrySet()) {
			if(1 == entry.getValue()) {
				waitJoinForums.add(entry.getKey());
			}
		}
		
		
		
		request.setAttribute("waitJoinForums", waitJoinForums);
		totalRows = pageDesc.getTotalRows();
		
		return LIST;
	}

	private void listObjects(Map<String, Object> filterMap) {
		PageDesc pageDesc = makePageDesc();
		objList = baseEntityManager.listObjects(filterMap, pageDesc);
	
		this.pageDesc = pageDesc;
	}

	/**
	 * 展示所有需要申请加入讨论版，并可以进行申请
	 * 
	 * @return
	 */
	public String listApply() {
		Map<String, Object> filterMap = getFilterMap();
		filterMap.put("JOINRIGHT", getLoginUsercode());
		PageDesc pageDesc = makePageDesc();

		Map<Forum, Integer> objs = forumMag.listByJoinRight(filterMap, pageDesc);

		request.setAttribute("objs", objs);
		
		  
		//--begin    
	    //add by lq 页面ectable 
        List<Forum> objects = new ArrayList<Forum>();
        for (Entry<Forum, Integer> entry : objs.entrySet()) {
                entry.getKey().setFlag(entry.getValue());
                objects.add(entry.getKey());
        }
        request.setAttribute("objects", objects);
        //--end

		//request.setAttribute("loginusercode", getLoginUsercode());
		super.pageDesc = pageDesc;
		totalRows = pageDesc.getTotalRows();
		return "listApply";
	}
	  /**
     * 批量删除功能
     * @return
     */
    public String deleteIds() {
        //批量ids
        String ids = request.getParameter("ids");
        if (StringUtils.isNotEmpty(ids)) {
        for (String forumid : ids.split(",")) {
            //存放作修改字段
            object.setForumid(Long.valueOf(forumid));
            super.delete();    
         }
        }
        return this.manager();
    } 
	/**
	 * 讨论版管理台
	 * 
	 * @return
	 */
	public String manager() {
		Map<String, Object> filterMap = getFilterMap();

		listObjects(filterMap);

		// 查询当前Forum是否需要有需要审批的人员
		Map<Long, Boolean> staffRole = new HashMap<Long, Boolean>();
		Map<String, Object> staffFilterMap = new HashMap<String, Object>();
		staffFilterMap.put("usercodeEq", getLoginUsercode());
		staffFilterMap.put("userrole", "2");
		for (Forum f : objList) {
			staffFilterMap.put("forumidEq", f.getForumid());

			staffRole.put(f.getForumid(), !CollectionUtils.isEmpty(forumStaffManager.listObjects(staffFilterMap)));
		}

		request.setAttribute("staffRole", staffRole);
		totalRows = pageDesc.getTotalRows();
		return "manager";
	}

	/**
	 * 加入或退出讨论版
	 * 
	 * @return
	 */
	public String inOut() {
		ForumStaff forumStaff = new ForumStaff(new ForumStaffId(object.getForumid(), getLoginUsercode()));

		forumMag.saveObjectByInOut(object, forumStaff);

		return "tolistApply";
	}

	/**
	 * 申请加入
	 * 
	 * @return
	 */
	public String applyJoin() {
		ForumStaff forumStaff = new ForumStaff(new ForumStaffId(object.getForumid(), getLoginUsercode()));
		forumStaff.setUserrole("2");
		forumStaff.setJointime(new Date());

		forumStaffManager.saveObject(forumStaff);

		return "tolistApply";
	}

	/**
	 * ajax申请加入
	 * @throws IOException 
	 */
	public void ajaxApplyJoin() throws IOException {
		object = baseEntityManager.getObject(object);
		int result = -1;
		
		ForumStaff forumStaff = new ForumStaff(new ForumStaffId(object.getForumid(), getLoginUsercode()));
		
		//按现有逻辑公开板块的是没有地方申请加入的下面if执行不到 
		if(0 == object.getJoinright()) {
			forumStaff.setUserrole("0");
			
			result = 0;
		}else if(1 == object.getJoinright()) {
			forumStaff.setUserrole("2");
			
			result = 2;
		}
		
		
		forumStaff.setJointime(new Date());

		forumStaffManager.saveObject(forumStaff);
		
		ServletActionContext.getResponse().getWriter().print(result);
	}

	/**
	 * 确认用户申请加入讨论版
	 * 
	 * @return
	 */
	public String confirmApplyJoin() {
		PageDesc pageDesc = makePageDesc();
		objList = forumMag.listConfirmApplyJoin(object, pageDesc);
		totalRows = pageDesc.getTotalRows();
		super.pageDesc = pageDesc;

		return "confirmApplyJoin";
	}

	@Override
	public String save() {
		Forum dbObject = baseEntityManager.getObject(object);
		if (dbObject != null) {
			baseEntityManager.copyObjectNotNullProperty(dbObject, object);
			object = dbObject;
		}

		ForumStaff forumStaff = new ForumStaff(new ForumStaffId(null, getLoginUsercode()));

		forumMag.saveObject(object, forumStaff, forumTypes);

		return SUCCESS;
	}

	private String getLoginUsercode() {
		return ((FUserinfo) super.getLoginUser()).getUsercode();
	}

	/**
	 * 新闻平台管理
	 * 
	 * @return
	 */
	public String listInfoReleaseManager() {
		Map<String, Object> filterMap = getFilterMap();
		filterMap.put("boardcodeEq", "INFO_REL");

		listObjects(filterMap);
		totalRows = pageDesc.getTotalRows();
		return "infoReleaseManager";
	}

	/**
	 * 添加新闻管理
	 * 
	 * @return
	 */
	public String saveInfoRelease() {
		Forum dbForum = baseEntityManager.getObject(object);
		if (null != dbForum) {
			dbForum.copyNotNullProperty(object);

			object = dbForum;
		}

		object.setCreatetime(new Date());
		forumMag.saveObject(object, null, forumTypes);
//		for (ForumType forumType : forumTypes) {
//			forumType.getCid().setForumid(object.getForumid());
//		}
//		object.replaceForumTypes(forumTypes);
//		baseEntityManager.saveObject(object);
		return "saveInfoRelease";
	}

	/**
	 * 新闻平台编辑
	 * 
	 * @return
	 */
	public String editInfoRelease() {
		object = baseEntityManager.getObject(object);

		return "editInfoRelease";
	}

	/**
	 * 信息发布
	 * 
	 * @return
	 */
	public String forumByInfoRelease() {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("boardcodeEq", "INFO_REL");

		objList = baseEntityManager.listObjects(filterMap);

		for (Forum forum : objList) {
			Map<String, Object> threadFilterMap = new HashMap<String, Object>();
			threadFilterMap.put("forumid", forum.getForumid());
			threadFilterMap.put("threadLock", "F");
			threadFilterMap.put(CodeBook.SELF_ORDER_BY, "posttime desc");

			PageDesc pageDesc = new PageDesc(1, 7);

			forum.setThreadList(threadManager.listObjects(threadFilterMap, pageDesc));
		}

		return "infoRelease";
	}

	public String deleteInfoRelease() {
		super.delete();

		return "deleteInfoRelease";
	}
	//set
	   public String edit() {
	        try {
	            object = baseEntityManager.getObject(object);
	                
	          
	            return EDIT;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ERROR;
	        }
	    }
}
