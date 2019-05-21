package com.centit.sys.po;


/**
 * create by scaffold
 *
 * @author codefan@hotmail.com
 */

public class TaskLog implements java.io.Serializable {
    private static final long serialVersionUID = 1L;


    private Long taskLogId;

    private String type;
    private String taskDesc;
    private String taskRun;

    // Constructors

    /**
     * default constructor
     */
    public TaskLog() {
    }

    /**
     * minimal constructor
     */
    public TaskLog(
            Long taskLogId
    ) {


        this.taskLogId = taskLogId;

    }

    /**
     * full constructor
     */
    public TaskLog(
            Long taskLogId
            , String type, String taskDesc, String taskRun) {


        this.taskLogId = taskLogId;

        this.type = type;
        this.taskDesc = taskDesc;
        this.taskRun = taskRun;
    }

    public TaskLog(String type, String taskDesc) {
        this.type = type;
        this.taskDesc = taskDesc;
        this.taskRun = "F";
    }

    public Long getTaskLogId() {
        return this.taskLogId;
    }

    public void setTaskLogId(Long taskLogId) {
        this.taskLogId = taskLogId;
    }
    // Property accessors

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTaskDesc() {
        return this.taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskRun() {
        return this.taskRun;
    }

    public void setTaskRun(String taskRun) {
        this.taskRun = taskRun;
    }


    public void copy(TaskLog other) {

        this.setTaskLogId(other.getTaskLogId());

        this.type = other.getType();
        this.taskDesc = other.getTaskDesc();
        this.taskRun = other.getTaskRun();

    }

    public void copyNotNullProperty(TaskLog other) {

        if (other.getTaskLogId() != null)
            this.setTaskLogId(other.getTaskLogId());

        if (other.getType() != null)
            this.type = other.getType();
        if (other.getTaskDesc() != null)
            this.taskDesc = other.getTaskDesc();
        if (other.getTaskRun() != null)
            this.taskRun = other.getTaskRun();

    }

    public void clearProperties() {

        this.type = null;
        this.taskDesc = null;
        this.taskRun = null;

    }

    public void setIsRun() {
        this.taskRun = "T";
    }
}
