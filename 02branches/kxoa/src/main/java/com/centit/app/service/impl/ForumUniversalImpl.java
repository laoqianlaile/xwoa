package com.centit.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.CollectionUtils;

import com.centit.app.po.Forum;
import com.centit.app.po.Reply;
import com.centit.app.po.ReplyAnnex;
import com.centit.app.po.ReplyAnnexId;
import com.centit.app.po.Thread;
import com.centit.app.po.ThreadAnnex;
import com.centit.app.po.ThreadAnnexId;
import com.centit.app.service.ForumManager;
import com.centit.app.service.IForumUniversal;
import com.centit.app.service.ReplyManager;
import com.centit.app.service.ThreadManager;
import com.centit.core.utils.PageDesc;

public class ForumUniversalImpl implements IForumUniversal {
	
	private ForumManager forumManager;
	private ThreadManager threadManager;
	private ReplyManager replyManager;
	
	public void setReplyManager(ReplyManager replyManager) {
		this.replyManager = replyManager;
	}

	public void setForumManager(ForumManager forumManager) {
		this.forumManager = forumManager;
	}

	public void setThreadManager(ThreadManager threadManager) {
		this.threadManager = threadManager;
	}


	@Override
	public void saveReply(String boardcode, String resourceId, String usercode, String reply, String... annexsId) {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("boardcodeEq", boardcode);
		filterMap.put("forumnameEq", boardcode);

		Forum forum = null;
		
		List<Forum> forums = forumManager.listObjects(filterMap);
		if (!CollectionUtils.isEmpty(forums)) {
			forum = forums.get(0);
		}else {
			forum = new Forum();
			forum.setCreatetime(new Date());
			forum.setBoardcode(boardcode);
			forum.setForumname(boardcode);
			forum.setForumstate("2");
			
			forumManager.saveObject(forum);
		}
		
		filterMap.clear();
		filterMap.put("forumid", forum.getForumid());
		filterMap.put("titolEq", resourceId);
		
		Thread thread = null;
		
		List<Thread> threads = threadManager.listObjects(filterMap);
		if(!CollectionUtils.isEmpty(threads)) {
			thread = threads.get(0);
		}else {
			thread = new Thread();
			thread.setForum(forum);
			thread.setTitol(resourceId);
			thread.setPosttime(new Date());
			threadManager.saveObject(thread);
		}
		
		saveReply(usercode, reply, thread, annexsId);
	}

	private void saveReply(String usercode, String reply, Thread thread, String... annexsId) {
		List<ReplyAnnex> replyAnnexs = new ArrayList<ReplyAnnex>();
		if(!ArrayUtils.isEmpty(annexsId)) {
			for (String string : annexsId) {
				replyAnnexs.add(new ReplyAnnex(new ReplyAnnexId(null, string)));
			}
		}
		
		Reply replyInstance = new Reply();
		replyInstance.setThread(thread);
		replyInstance.setReply(reply);
		replyInstance.setUserid(usercode);
		
		replyManager.saveObject(replyInstance, replyAnnexs);
	}

	
	@Override
	public void saveReply(Long threadId, String usercode, String reply, String... annexsId) {
		Thread thread = threadManager.getObjectById(threadId);
		saveReply(usercode, reply, thread, annexsId);
	}


	@Override
	public List<Reply> listReplyByExternalUse(String boardcode, String resourceId, PageDesc pageDesc) {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("forumname", boardcode);
		filterMap.put("forumboardcode", boardcode);
		filterMap.put("titol", resourceId);
		
		return replyManager.listObjects(filterMap, pageDesc);
	}

	@Override
	public Long saveThread(String boardcode, String forumName, String title, String content, String usercode,
			boolean threadLock, boolean threadReply, String... annexsId) {
		Forum forum = forumManager.getExternalUse(boardcode, forumName);
		
		Thread thread = new Thread();
		thread.setTitol(title);
		thread.setContent(content);
		thread.setWirterid(usercode);
		if(threadLock) {
			thread.setThreadLock("T");
		}
		if(threadReply) {
			thread.setThreadReply("T");
		}
		
		thread.setForum(forum);
		
		List<ThreadAnnex> threadAnnexs = new ArrayList<ThreadAnnex>();
		if(ArrayUtils.isNotEmpty(annexsId)) {
			for (String string : annexsId) {
				ThreadAnnex threadAnnex = new ThreadAnnex(new ThreadAnnexId(null, string));
				
				threadAnnexs.add(threadAnnex);
			}
		}
		
		threadManager.saveObject(thread, threadAnnexs);
		
		return thread.getThreadid();
	}

	@Override
	public Long saveThread(String boardcode, String forumName, String title, String content, String usercode, String... annexsId) {
		return saveThread(boardcode, forumName, title, content, usercode, true, true, annexsId);
	}

	@Override
	public Long saveThread(String boardcode, String title, String content, String usercode, boolean threadLock,
			boolean threadReply, String... annexsId) {
		return saveThread(boardcode, boardcode, title, content, usercode, threadLock, threadReply, annexsId);
	}

	@Override
	public Long saveThread(String boardcode, String title, String content, String usercode, String... annexsId) {
		return saveThread(boardcode, boardcode, title, content, usercode, true, true, annexsId);
	}

	@Override
	public void updateThread(Long threadId, boolean threadLock, boolean threadReply) {
		Thread dbThread = threadManager.getObjectById(threadId);
		dbThread.setThreadLock(threadLock ? "T" : "F");
		dbThread.setThreadReply(threadReply ? "T" : "F");
		
		threadManager.saveObject(dbThread);
	}

	@Override
	public void deleteThread(Long threadId) {
		threadManager.deleteObjectById(threadId);
		
	}

}
