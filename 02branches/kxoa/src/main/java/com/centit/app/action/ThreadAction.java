package com.centit.app.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.centit.app.po.Forum;
import com.centit.app.po.ForumStaff;
import com.centit.app.po.ForumStaffId;
import com.centit.app.po.Reply;
import com.centit.app.po.Thread;
import com.centit.app.po.ThreadAnnex;
import com.centit.app.po.ThreadAnnexId;
import com.centit.app.service.ForumManager;
import com.centit.app.service.ForumStaffManager;
import com.centit.app.service.ThreadManager;
import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.dao.CodeBook;
import com.centit.core.utils.PageDesc;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.security.FUserDetail;

public class ThreadAction extends BaseEntityDwzAction<Thread> {
    private static final Log log = LogFactory.getLog(ThreadAction.class);

    //private static final ISysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog("optid");

    private static final long serialVersionUID = 1L;
    private ThreadManager threadMag;

    private ForumStaffManager forumStaffManager;
     
    private String pageBoardCood;//标识新闻 infoR，新闻管理infoRM，讨论版info，讨论版管理infoM
    
    public String getPageBoardCood() {
        return pageBoardCood;
    }

    public void setPageBoardCood(String pageBoardCood) {
        this.pageBoardCood = pageBoardCood;
    }

    public void setForumStaffManager(ForumStaffManager forumStaffManager) {
		this.forumStaffManager = forumStaffManager;
	}
    
    public void setThreadManager(ThreadManager basemgr) {
        threadMag = basemgr;
        this.setBaseEntityManager(threadMag);
    }

    private ForumManager forumManager;

    public void setForumManager(ForumManager forumManager) {
        this.forumManager = forumManager;
    }

    private List<Reply> replys;

    public List<Reply> getNewReplys() {
        return this.replys;
    }

    public void setNewReplys(List<Reply> replys) {
        this.replys = replys;
    }

    private List<ThreadAnnex> threadAnnexs;

    public List<ThreadAnnex> getNewThreadAnnexs() {
        return this.threadAnnexs;
    }

    public void setNewThreadAnnexs(List<ThreadAnnex> threadAnnexs) {
        this.threadAnnexs = threadAnnexs;
    }

    @Override
	public String edit() {
		Thread dbThread = baseEntityManager.getObject(object);
    	if(null != dbThread) {
    		object = dbThread;
    	} else {
    		object.setForum(forumManager.getObjectById(object.getForum().getForumid()));
    	}
		
    	return EDIT;
	}

	@SuppressWarnings("unchecked")
	@Override
    public String list() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        String orderField = request.getParameter("orderField");
        String orderDirection = request.getParameter("orderDirection");

        Map<String, Object> filterMap = convertSearchColumn(paramMap);

        filterMap.put("forumid", object.getForum().getForumid());
        if (StringUtils.hasText(orderField) && StringUtils.hasText(orderDirection)) {

            filterMap.put(CodeBook.SELF_ORDER_BY, orderField + " " + orderDirection);
        }else {
            filterMap.put(CodeBook.SELF_ORDER_BY, "posttime desc");
        }
        PageDesc pageDesc = makePageDesc();
        objList = baseEntityManager.listObjects(filterMap, pageDesc);

        //获取Forum
        object.setForum(forumManager.getObject(object.getForum()));

        //当前登录用户代码
        request.setAttribute("usercode", ((FUserDetail)getLoginUser()).getUsercode());
        
        
        //判断当前讨论版是否设置了发帖权限 postright
        request.setAttribute("canCreate", true);
        if(1 == object.getForum().getPostright()) {
        	ForumStaff forumStaff = forumStaffManager.getObjectById(new ForumStaffId(object.getForum().getForumid(), getLoginUsercode()));
        	
        	if(null == forumStaff || "2".equals(forumStaff.getUserrole())) {
        		request.setAttribute("canCreate", false);
        	}
        }

        this.pageDesc = pageDesc;
        
        
        //标记 新闻管理平台
        if(Forum.INFO_RELEASE.equals(object.getForum().getBoardcode())) {
        	request.setAttribute("isInfoReleaseAudit", true);
        }
        
        
        return LIST;
    }

    @Override
    public String save() {
        //附件
        String threadAnnexId = request.getParameter("threadAnnexId");

        List<ThreadAnnex> threadAnnexes = new ArrayList<ThreadAnnex>();
        if (StringUtils.hasText(threadAnnexId)) {
            String[] annexId = threadAnnexId.split(",");
            for (String s : annexId) {
                threadAnnexes.add(new ThreadAnnex(new ThreadAnnexId(null, StringUtils.trimWhitespace(s))));
            }
        }


        //
        object.setPosttime(new Date());
        object.setWirterid(((FUserinfo) super.getLoginUser()).getUsercode());

        if (!CollectionUtils.isEmpty(threadAnnexes)) {
            object.setAnnexnum(Long.valueOf(threadAnnexes.size()));
        }
        object.getForum().setThreadnum(null == object.getForum().getThreadnum()?1:object.getForum().getThreadnum()+1);
        threadMag.saveObject(object, threadAnnexes);


        return this.list();
    }
    
    
    public String search() {
    	PageDesc pageDesc = makePageDesc();
    	
    	objList = threadMag.listBySearch(getLoginUsercode(), request.getParameter("key"), pageDesc);
    	
    	this.pageDesc = pageDesc;
    	return LIST;
    }



    @Override
    public String view() {
        return super.view();
    }

    @Override
    public String delete() {
        super.delete();

        return "delete";
    }
    
    private String getLoginUsercode() {
        return ((FUserinfo)super.getLoginUser()).getUsercode();
    }
    
    
    
    /**
     * 新闻审核
     * @return
     */
	public String forumByInfoReleaseAudit(){

        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put("forumid", object.getForum().getForumid());
        filterMap.put("threadLockEq", "T");
        filterMap.put(CodeBook.SELF_ORDER_BY, "posttime desc");
    	
        PageDesc pageDesc = makePageDesc();
        objList = baseEntityManager.listObjects(filterMap, pageDesc);
        
        this.pageDesc = pageDesc;
        
        
        //object.setForum(forumManager.getObjectById((Long)(filterMap.get("forumid"))));
        
        
        //标识为新闻审核功能
        request.setAttribute("isInfoReleaseAudit", true);
        
    	return LIST;
    }
	
	
	/**
	 * 确认新闻审核
	 * @throws IOException 
	 */
	public void passInfoAudit() throws IOException {
		object = baseEntityManager.getObject(object);
		object.setThreadLock("F");
		
		baseEntityManager.saveObject(object);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("info", true);
		jsonObject.put("id", object.getThreadid());
		
		ServletActionContext.getResponse().getWriter().println(jsonObject.toString());
		
	}
    
    
    
    
    
}
