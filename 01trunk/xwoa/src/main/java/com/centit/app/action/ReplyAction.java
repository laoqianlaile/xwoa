package com.centit.app.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


import com.centit.app.po.FileinfoFs;
import com.centit.app.po.ForumStaff;
import com.centit.app.po.ForumStaffId;
import com.centit.app.po.Reply;
import com.centit.app.po.ReplyAnnex;
import com.centit.app.po.ReplyAnnexId;
import com.centit.app.po.Thread;
import com.centit.app.po.ThreadAnnex;
import com.centit.app.service.FileinfoFsManager;
import com.centit.app.service.ForumStaffManager;
import com.centit.app.service.ReplyManager;
import com.centit.app.service.ThreadManager;
import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.PageDesc;
import com.centit.sys.po.FUserinfo;

public class ReplyAction  extends BaseEntityDwzAction<Reply>  {
	private static final Log log = LogFactory.getLog(ReplyAction.class);
	
	//private static final ISysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog("optid");
	
	private static final long serialVersionUID = 1L;
	private ReplyManager replyMag;

    private ThreadManager threadManager;

    private FileinfoFsManager fileinfoFsManager;
    
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



    public void setFileinfoFsManager(FileinfoFsManager fileinfoFsManager) {
        this.fileinfoFsManager = fileinfoFsManager;
    }



    public void setThreadManager(ThreadManager threadManager) {
        this.threadManager = threadManager;
    }

    public void setReplyManager(ReplyManager basemgr)
	{
		replyMag = basemgr;
		this.setBaseEntityManager(replyMag);
	}

	private List<ReplyAnnex> replyAnnexs;
	public List<ReplyAnnex> getNewReplyAnnexs() {
		return this.replyAnnexs;
	}
	public void setNewReplyAnnexs(List<ReplyAnnex> replyAnnexs) {
		this.replyAnnexs = replyAnnexs;
	}

    @SuppressWarnings("unchecked")
	@Override
    public String list() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        Thread thread = threadManager.getObject(object.getThread());
        thread.setViewnum(null == thread.getViewnum() ? 1 : thread.getViewnum() + 1);
        threadManager.saveObject(thread);


        //附件
        if (!CollectionUtils.isEmpty(thread.getThreadAnnexs())){
            List<FileinfoFs> fileinfos = new ArrayList<FileinfoFs>();
            for (ThreadAnnex threadAnnex : thread.getThreadAnnexs()) {
                fileinfos.add(fileinfoFsManager.getObjectById(threadAnnex.getCid().getFilecode()));
            }

            request.setAttribute("fileinfos", fileinfos);
        }

        object.setThread(thread);


        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        filterMap.put("threadid", object.getThread().getThreadid());

        PageDesc pageDesc = makePageDesc();
        objList = baseEntityManager.listObjects(filterMap, pageDesc);

        //附件
        Map<Long, List<FileinfoFs>> replyAnnexs = new HashMap<Long, List<FileinfoFs>>();
        for (Reply reply : objList) {
            if (!CollectionUtils.isEmpty(reply.getReplyAnnexs())) {
                List<FileinfoFs> fileinfos = new ArrayList<FileinfoFs>();

                for (ReplyAnnex replyAnnex : reply.getReplyAnnexs()) {
                    fileinfos.add(fileinfoFsManager.getObjectById(replyAnnex.getFilecode()));
                }

                replyAnnexs.put(reply.getReplyid(), fileinfos);
            }
        }
        request.setAttribute("replyAnnexs", replyAnnexs);
        
        
        //页面上回复权限问题，只有本讨论版成员才可回复
        request.setAttribute("canReply", true);
        if(1 == thread.getForum().getReplyright()) {
        	ForumStaff forumStaff = forumStaffManager.getObjectById(new ForumStaffId(thread.getForum().getForumid(), getLoginUsercode()));
        	
        	if(null == forumStaff || "2".equals(forumStaff.getUserrole())) {
        		request.setAttribute("canReply", false);
        	}
        }
        

        this.pageDesc = pageDesc;


        return LIST;
    }

    public String save(){
        Thread thread = threadManager.getObject(object.getThread());
        thread.setReplnum(null == thread.getReplnum() ? 1 : thread.getReplnum() + 1);
        thread.setPosttime(new Date());
        thread.getForum().setReplynum(null == thread.getForum().getReplynum() ? 1 : thread.getForum().getReplynum() + 1);

        object.setThread(thread);

        object.setUserid(getLoginUsercode());
        object.setReplytime(new Date());


        List<ReplyAnnex> replyAnnexes = new ArrayList<ReplyAnnex>();
        String replyAnnexId = request.getParameter("replyAnnexId");
        if (StringUtils.hasText(replyAnnexId)) {
            for (String s : replyAnnexId.split(",")) {
                replyAnnexes.add(new ReplyAnnex(new ReplyAnnexId(null, StringUtils.trimWhitespace(s))));
            }
        }

        replyMag.saveObject(object, replyAnnexes);

        return SUCCESS;
	}
	
		
	public String delete() {
	    super.delete();      
	    
	    return "delete";
	}


    private String getLoginUsercode() {
        return ((FUserinfo)super.getLoginUser()).getUsercode();
    }
}
