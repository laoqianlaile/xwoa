package com.centit.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import com.centit.app.dao.ThreadAnnexDao;
import com.centit.app.dao.ThreadDao;
import com.centit.app.po.FileinfoFs;
import com.centit.app.po.Forum;
import com.centit.app.po.Thread;
import com.centit.app.po.ThreadAnnex;
import com.centit.app.service.FileinfoFsManager;
import com.centit.app.service.FileinfoManager;
import com.centit.app.service.ThreadManager;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.support.searcher.DocDesc;
import com.centit.support.searcher.SearchCondition;
import com.centit.support.searcher.Searcher;
import com.centit.sys.po.TaskLog;
import com.centit.sys.service.TaskLogManager;
import com.centit.sys.service.impl.IndexManagerImpl;

public class ThreadManagerImpl extends BaseEntityManagerImpl<Thread>
        implements ThreadManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(ThreadManager.class);

    //private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

    private ThreadDao threadDao;

    private ThreadAnnexDao threadAnnexDao;
    
    private TaskLogManager taskLogManager;
    
    

    public void setTaskLogManager(TaskLogManager taskLogManager) {
		this.taskLogManager = taskLogManager;
	}

	public void setThreadDao(ThreadDao baseDao) {
        this.threadDao = baseDao;
        setBaseDao(this.threadDao);
    }

    public void setThreadAnnexDao(ThreadAnnexDao threadAnnexDao) {
        this.threadAnnexDao = threadAnnexDao;
    }

    private FileinfoFsManager fileinfoFsManager;



    public void setFileinfoFsManager(FileinfoFsManager fileinfoFsManager) {
        this.fileinfoFsManager = fileinfoFsManager;
    }

    @Override
    public void saveObject(Thread thread, List<ThreadAnnex> threadAnnex) {
        threadDao.saveObject(thread);

        for (ThreadAnnex annex : threadAnnex) {
            annex.getCid().setThreadid(thread.getThreadid());

            FileinfoFs fileinfo = fileinfoFsManager.getObjectById(annex.getCid().getFilecode());

            DocDesc threadAnnexDocDesc = new DocDesc(DocDesc.ResType.FILE, "THREAD", thread.getWirterid(), "THREAD_FILE" + fileinfo.getFilecode(),
                    thread.getTitol(), null, "view", "thread.threadid=" + thread.getThreadid());

            fileinfoFsManager.update4ConfirmFileinfo(fileinfo, threadAnnexDocDesc);

            threadAnnexDao.saveObject(annex);
            
            
        }
        JSONObject jsonObjectOptId = new JSONObject();
        jsonObjectOptId.put("forumid", thread.getForum().getForumid());
        jsonObjectOptId.put("threadid", thread.getThreadid());
        
        
        DocDesc threadDocDesc = new DocDesc(DocDesc.ResType.OPT, "THREAD", thread.getWirterid(), jsonObjectOptId.toString(),
        		thread.getTitol(), null, "view", "thread.threadid=" + thread.getThreadid());
        //threadDocDesc.setValue(thread.getContent());
        JSONObject jsonObject = JSONObject.fromObject(threadDocDesc);
        jsonObject.put(IndexManagerImpl.QUERY_CONTENT_JSON, "select t.content from M_THREAD t where t.threadid = " + thread.getThreadid());
        
        
        jsonObject.put(IndexManagerImpl.INDEX_TYPE, IndexManagerImpl.INDEX_TYPE_ADD);
        
        TaskLog taskLog = new TaskLog("ThreadToIndex", jsonObject.toString());
        taskLogManager.saveObject(taskLog);

    }

    @Override
    public void deleteObject(Thread o) {
        Set<ThreadAnnex> threadAnnexs = o.getThreadAnnexs();
       /* if (!CollectionUtils.isEmpty(threadAnnexs)) {
            //删除附件
        }*/

        super.deleteObject(o);
    }

    @Override
    public Thread getExternalUse(Forum forum, String titol) {
        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put("forumid", forum.getForumid());
        filterMap.put("titolEq", titol);
        
        List<Thread> threads = baseDao.listObjects(filterMap);
        if(!CollectionUtils.isEmpty(threads)){
            return threads.get(0);
        }
        
        
        Thread thread = new Thread();
        thread.setTitol(titol);
        
        thread.setForum(forum);
        saveObject(thread);
       
        return thread;
    }

	@Override
	public List<Thread> listBySearch(String usercode, String content, PageDesc pageDesc) {
		SearchCondition cond = new SearchCondition(content);
		cond.setsOpts(new String[]{"THREAD"});
		//cond.set
		
		List<DocDesc> solrSearch = Searcher.solrSearch(cond);
		
		if(CollectionUtils.isEmpty(solrSearch)) {
			return null;
		}
		
		Map<Long, DocDesc> resultMap = new HashMap<Long, DocDesc>();
		
		JSONObject jsonObject = null;
		
		Long forumid = null;
		
		for (DocDesc docDesc : solrSearch) {
			//System.out.println(docDesc.getDataID());
			//TODO 有脏数据
			if(!docDesc.getDataID().startsWith("{")) {
				continue;
			}
			
			jsonObject = JSONObject.fromObject(docDesc.getDataID());
			
			if(!jsonObject.containsKey("forumid") || !jsonObject.containsKey("threadid")) {
				continue;
			}
			
			forumid = jsonObject.getLong("forumid");
			
			resultMap.put(jsonObject.getLong("threadid"), docDesc);
		}
		
		if(CollectionUtils.isEmpty(resultMap)) {
			return new ArrayList<Thread>();
		}
		
		List<Thread> threads = threadDao.listBySearch(resultMap.keySet(), forumid, usercode, pageDesc);
		
		for (Thread thread : threads) {
			thread.setSummary(resultMap.get(thread.getThreadid()).getSummary());
		}
		
		return threads;
	}
}

