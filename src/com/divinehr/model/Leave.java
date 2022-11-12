package com.divinehr.model;

public class Leave {
	private int eid, deptId, id;
	private String leaveMsg, date, status;
	//TODO create SQL table
	
	public Leave() {}

	public Leave(int id, int eid, int deptId, String leaveMsg, String date) {
		super();
		this.id = id;
		this.eid = eid;
		this.deptId = deptId;
		this.leaveMsg = leaveMsg;
		this.date = date;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getLeaveMsg() {
		return leaveMsg;
	}

	public void setLeaveMsg(String leaveMsg) {
		this.leaveMsg = leaveMsg;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Leave [id=" + id + ", eid=" + eid + ", deptId=" + deptId + ", leaveMsg=" + leaveMsg + ", date=" + date
				+ ", status=" + status + "]";
	}
	
}
