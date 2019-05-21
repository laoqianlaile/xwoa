package com.centit.app.po;

public class PaintFlowChart {
	private String department;// ���ڲ���
	private String status;// ����״̬
	private String note;//
	private String tache_name;// �����λ���
	private String user_name;
	private String process_time;

	private boolean isBeginNode = false;// �Ƿ���ͷ�ڵ�
	private boolean isEndNode = false;// �Ƿ��ǽ���ڵ�
	private boolean isColEnd = false; // �Ƿ����е����һ�����
	private boolean isEmptyNode = false; //�жϽڵ��Ƿ��ǿյĽڵ�
	private String hotspotUrl;
	private String imgName;
	private String moveImgName;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getTache_name() {
		return tache_name;
	}

	public void setTache_name(String tache_name) {
		this.tache_name = tache_name;
	}

	public boolean isBeginNode() {
		return isBeginNode;
	}

	public void setBeginNode(boolean isBeginNode) {
		this.isBeginNode = isBeginNode;
	}

	public boolean isEndNode() {
		return isEndNode;
	}

	public void setEndNode(boolean isEndNode) {
		this.isEndNode = isEndNode;
	}

	public String getHotspotUrl() {
		return hotspotUrl;
	}

	public void setHotspotUrl(String hotspotUrl) {
		this.hotspotUrl = hotspotUrl;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getMoveImgName() {
		return moveImgName;
	}

	public void setMoveImgName(String moveImgName) {
		this.moveImgName = moveImgName;
	}

	public boolean isColEnd() {
		return isColEnd;
	}

	public void setColEnd(boolean isColEnd) {
		this.isColEnd = isColEnd;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public boolean isEmptyNode() {
		return isEmptyNode;
	}

	public void setEmptyNode(boolean isEmptyNode) {
		this.isEmptyNode = isEmptyNode;
	}

	public String getProcess_time() {
		return process_time;
	}

	public void setProcess_time(String process_time) {
		this.process_time = process_time;
	}

}
