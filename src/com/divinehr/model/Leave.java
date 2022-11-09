package com.divinehr.model;

public class Leave {
	private int eid, deptId;
	private String leaveMsg, date;
	//TODO create SQL table
	
	public Leave() {}

	public Leave(int eid, int deptId, String leaveMsg, String date) {
		super();
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

	@Override
	public String toString() {
		return "Leave [eid=" + eid + ", deptId=" + deptId + ", leaveMsg=" + leaveMsg + ", date=" + date + "]";
	}
	
}
