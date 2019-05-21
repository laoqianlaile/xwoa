package com.centit.app.po;


/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class TaskListAnnex implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Long tlaid;
	
	private TaskList taskList;
	
	private FileinfoFs fileinfoFs;
	
    public Long getTlaid() {
        return tlaid;
    }

    public void setTlaid(Long tlaid) {
        this.tlaid = tlaid;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public FileinfoFs getFileinfoFs() {
        return fileinfoFs;
    }

    public void setFileinfoFs(FileinfoFs fileinfoFs) {
        this.fileinfoFs = fileinfoFs;
    }

	public TaskListAnnex(Long tlaid, TaskList taskList, FileinfoFs fileinfoFs) {
		super();
		this.tlaid = tlaid;
		this.taskList = taskList;
		this.fileinfoFs = fileinfoFs;
	}

	public TaskListAnnex() {
		super();
	}

	public TaskListAnnex(Long tlaid) {
		super();
		this.tlaid = tlaid;
	}


	
	
	
	
	
}
