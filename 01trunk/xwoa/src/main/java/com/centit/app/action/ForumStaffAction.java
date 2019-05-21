package com.centit.app.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.app.po.ForumStaff;
import com.centit.app.po.ForumStaffId;
import com.centit.app.service.ForumManager;
import com.centit.app.service.ForumStaffManager;
import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.dao.CodeBook;
import com.centit.core.utils.PageDesc;


public class ForumStaffAction  extends BaseEntityDwzAction<ForumStaff>  {
	private static final Log log = LogFactory.getLog(ForumStaffAction.class);
	
	//private static final ISysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog("optid");
	
	private static final long serialVersionUID = 1L;
	private ForumStaffManager forumStaffMag;


    private ForumManager forumManager;

    public void setForumManager(ForumManager forumManager) {
        this.forumManager = forumManager;
    }

    public void setForumStaffManager(ForumStaffManager basemgr)
	{
		forumStaffMag = basemgr;
		this.setBaseEntityManager(forumStaffMag);
	}

    @SuppressWarnings("unchecked")
	@Override
    public String list() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
//        String userids = request.getParameter("userids");
        String orderField = request.getParameter("orderField");
        String orderDirection = request.getParameter("orderDirection");

        Map<String, Object> filterMap = convertSearchColumn(paramMap);

        filterMap.put("userrole", "2");
        if (StringUtils.hasText(orderField) && StringUtils.hasText(orderDirection)) {

            filterMap.put(CodeBook.SELF_ORDER_BY, orderField + " " + orderDirection);

        }
        PageDesc pageDesc = makePageDesc();
        objList = baseEntityManager.listObjects(filterMap, pageDesc);

        this.pageDesc = pageDesc;


        request.setAttribute("forum", forumManager.getObjectById(Integer.valueOf((String) filterMap.get("forumid"))));
        totalRows = pageDesc.getTotalRows();
        setbackSearchColumn(filterMap);
        return LIST;
    }

    public String confirmApplyJoin() {

        String userids = request.getParameter("userids");
        if(!StringUtils.hasText(userids)) {
        	
        	return SUCCESS;
        }
        String[] usercodes = userids.split(",");

        List<ForumStaff> forumStaffs = new ArrayList<ForumStaff>();
        for (String usercode : usercodes) {
            forumStaffs.add(baseEntityManager.getObjectById(new ForumStaffId(object.getCid().getForumid(), usercode)));
        }

        forumStaffMag.saveObjects(forumStaffs);


        return SUCCESS;
    }

    public String delete() {
	    super.delete();      
	    
	    return "delete";
	}
}
