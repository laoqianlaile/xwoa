package com.centit.app.po;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;

import org.springframework.util.StringUtils;

import com.centit.app.service.IToDoMatterData;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class TaskList implements java.io.Serializable, IToDoMatterData {
	private static final long serialVersionUID = 1L;

	private Long taskid;

	private String taskowner;
	private String tasktag;
	private String taskrank;
	private String taskstatus;
	private String tasktitle;
	private String taskmemo;
	private String tasktype;
	private String optid;
	private String optmethod;
	private String opttag;
	private String creator;
	private Date created;
	private Date planbegintime;
	private Date planendtime;
	private Date begintime;
	private Date endtime;
	private String finishmemo;
	private String noticesign;
	private Date lastnoticetime;
	private Date taskdeadline;

	private String taskvalue;
	
	
	private Set<TaskListAnnex> taskListAnnexs = new HashSet<TaskListAnnex>();

	private String day;
	// Constructors
	/** default constructor */
	public TaskList() {
	}

	/** minimal constructor */
	public TaskList(Long taskid, String taskowner, String tasktag, String taskrank, String taskstatus, String taskmemo,
			String tasktype, String optid, String creator, Date created, Date planbegintime) {

		this.taskid = taskid;

		this.taskowner = taskowner;
		this.tasktag = tasktag;
		this.taskrank = taskrank;
		this.taskstatus = taskstatus;
		this.taskmemo = taskmemo;
		this.tasktype = tasktype;
		this.optid = optid;
		this.creator = creator;
		this.created = created;
		this.planbegintime = planbegintime;
	}

	/** full constructor */
	public TaskList(Long taskid, String taskowner, String tasktag, String taskrank, String taskstatus, String taskmemo,
			String tasktype, String optid, String optmethod, String opttag, String creator, Date created,
			Date planbegintime, Date planendtime, Date begintime, Date endtime, String finishmemo, String noticesign,
			Date lastnoticetime, Date taskdeadline) {

		this.taskid = taskid;

		this.taskowner = taskowner;
		this.tasktag = tasktag;
		this.taskrank = taskrank;
		this.taskstatus = taskstatus;
		this.taskmemo = taskmemo;
		this.tasktype = tasktype;
		this.optid = optid;
		this.optmethod = optmethod;
		this.opttag = opttag;
		this.creator = creator;
		this.created = created;
		this.planbegintime = planbegintime;
		this.planendtime = planendtime;
		this.begintime = begintime;
		this.endtime = endtime;
		this.finishmemo = finishmemo;
		this.noticesign = noticesign;
		this.lastnoticetime = lastnoticetime;
		this.taskdeadline = taskdeadline;
	}

	public Long getTaskid() {
		return this.taskid;
	}

	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}

	// Property accessors

	public String getTaskowner() {
		return this.taskowner;
	}

	public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

	public void setTaskowner(String taskowner) {
		this.taskowner = taskowner;
	}

	public String getTaskvalue() {
		return taskvalue;
	}

	public void setTaskvalue(String taskvalue) {
		this.taskvalue = taskvalue;
	}

	public String getTasktag() {
		return this.tasktag;
	}

	public void setTasktag(String tasktag) {
		this.tasktag = tasktag;
	}

	public String getTaskrank() {
		return this.taskrank;
	}

	public void setTaskrank(String taskrank) {
		this.taskrank = taskrank;
	}

	public String getTaskstatus() {
		return this.taskstatus;
	}

	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}

	public String getTasktitle() {
		return tasktitle;
	}

	public void setTasktitle(String tasktitle) {
		this.tasktitle = tasktitle;
	}

	public String getTaskmemo() {
		return this.taskmemo;
	}

	public void setTaskmemo(String taskmemo) {
		this.taskmemo = taskmemo;
	}

	public String getTasktype() {
		return this.tasktype;
	}

	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}

	public String getOptid() {
		return this.optid;
	}

	public void setOptid(String optid) {
		this.optid = optid;
	}

	public String getOptmethod() {
		return this.optmethod;
	}

	public void setOptmethod(String optmethod) {
		this.optmethod = optmethod;
	}

	public String getOpttag() {
		return this.opttag;
	}

	public void setOpttag(String opttag) {
		this.opttag = opttag;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getPlanbegintime() {
		return this.planbegintime;
	}

	public void setPlanbegintime(Date planbegintime) {
		this.planbegintime = planbegintime;
	}

	public Date getPlanendtime() {
		return this.planendtime;
	}

	public void setPlanendtime(Date planendtime) {
		this.planendtime = planendtime;
	}

	public Date getBegintime() {
		return this.begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public Date getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getFinishmemo() {
		return this.finishmemo;
	}

	public void setFinishmemo(String finishmemo) {
		this.finishmemo = finishmemo;
	}

	public String getNoticesign() {
		return this.noticesign;
	}

	public void setNoticesign(String noticesign) {
		this.noticesign = noticesign;
	}

	public Date getLastnoticetime() {
		return this.lastnoticetime;
	}

	public void setLastnoticetime(Date lastnoticetime) {
		this.lastnoticetime = lastnoticetime;
	}

	public Date getTaskdeadline() {
		return this.taskdeadline;
	}

	public void setTaskdeadline(Date taskdeadline) {
		this.taskdeadline = taskdeadline;
	}

	public void copy(TaskList other) {

		this.setTaskid(other.getTaskid());

		this.taskowner = other.getTaskowner();
		this.tasktag = other.getTasktag();
		this.taskrank = other.getTaskrank();
		this.taskstatus = other.getTaskstatus();
		this.taskmemo = other.getTaskmemo();
		this.tasktype = other.getTasktype();
		this.optid = other.getOptid();
		this.optmethod = other.getOptmethod();
		this.opttag = other.getOpttag();
		this.creator = other.getCreator();
		this.created = other.getCreated();
		this.planbegintime = other.getPlanbegintime();
		this.planendtime = other.getPlanendtime();
		this.begintime = other.getBegintime();
		this.endtime = other.getEndtime();
		this.finishmemo = other.getFinishmemo();
		this.noticesign = other.getNoticesign();
		this.lastnoticetime = other.getLastnoticetime();
		this.taskdeadline = other.getTaskdeadline();

	}

	public void copyNotNullProperty(TaskList other) {

		if (other.getTaskid() != null)
			this.setTaskid(other.getTaskid());

		if (other.getTaskowner() != null)
			this.taskowner = other.getTaskowner();
		if (other.getTasktag() != null)
			this.tasktag = other.getTasktag();
		if (other.getTaskrank() != null)
			this.taskrank = other.getTaskrank();
		if (other.getTaskstatus() != null)
			this.taskstatus = other.getTaskstatus();
		if (null != other.getTasktitle()) {
			this.tasktitle = other.getTasktitle();
		}
		if (other.getTaskmemo() != null)
			this.taskmemo = other.getTaskmemo();
		if (other.getTasktype() != null)
			this.tasktype = other.getTasktype();
		if (other.getOptid() != null)
			this.optid = other.getOptid();
		if (other.getOptmethod() != null)
			this.optmethod = other.getOptmethod();
		if (other.getOpttag() != null)
			this.opttag = other.getOpttag();
		if (other.getCreator() != null)
			this.creator = other.getCreator();
		if (other.getCreated() != null)
			this.created = other.getCreated();
		if (other.getPlanbegintime() != null)
			this.planbegintime = other.getPlanbegintime();
		if (other.getPlanendtime() != null)
			this.planendtime = other.getPlanendtime();
		if (other.getBegintime() != null)
			this.begintime = other.getBegintime();
		if (other.getEndtime() != null)
			this.endtime = other.getEndtime();
		if (other.getFinishmemo() != null)
			this.finishmemo = other.getFinishmemo();
		if (other.getNoticesign() != null)
			this.noticesign = other.getNoticesign();
		if (other.getLastnoticetime() != null)
			this.lastnoticetime = other.getLastnoticetime();
		if (other.getTaskdeadline() != null)
			this.taskdeadline = other.getTaskdeadline();
		if (other.getDay() != null)
		    this.day = other.getDay();

	}

	public void clearProperties() {

		this.taskowner = null;
		this.tasktag = null;
		this.taskrank = null;
		this.taskstatus = null;
		this.taskmemo = null;
		this.tasktype = null;
		this.optid = null;
		this.optmethod = null;
		this.opttag = null;
		this.creator = null;
		this.created = null;
		this.planbegintime = null;
		this.planendtime = null;
		this.begintime = null;
		this.endtime = null;
		this.finishmemo = null;
		this.noticesign = null;
		this.lastnoticetime = null;
		this.taskdeadline = null;

	}
	
	


	public Set<TaskListAnnex> getTaskListAnnexs() {
		return taskListAnnexs;
	}

	public void setTaskListAnnexs(Set<TaskListAnnex> taskListAnnexs) {
		this.taskListAnnexs = taskListAnnexs;
	}

	@Override
	public Serializable getId() {
		return getTaskid();
	}

	@Override
	public String getTitle() {
		return getTasktitle();
	}

	@Override
	public Date getStart() {
		return getPlanbegintime();
	}

	@Override
	public Date getEnd() {
		return getPlanendtime();
	}

	@Override
	public boolean isAllDay() {
		if (StringUtils.hasText(getTaskvalue())) {
			JSONObject jsonObject = JSONObject.fromObject(getTaskvalue());
			if (jsonObject.containsKey("allDay")) {
				return jsonObject.getBoolean("allDay");
			}
		}
		return false;
	}
	
	

	@Override
	public boolean isEditable() {
		return true;
	}

	@Override
	public String getUrl() {
		return "/app/taskList!edit.do?taskid=" + getTaskid();
	}
    @Override
    public String getOwner() {
        return getTaskowner();
    }
}
