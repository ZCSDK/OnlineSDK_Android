package com.sobot.online.model;

import java.io.Serializable;
import java.util.List;

//转接客服
public class OnLineServiceModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String face;
	private int maxcount;
	private int status;
	private String uname;
	private String statusCode;
	private int count;
	private List<String> groupId;
	private List<String> groupName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public int getStatus() {
		return status;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getMaxcount() {
		return maxcount;
	}

	public void setMaxcount(int maxcount) {
		this.maxcount = maxcount;
	}

	public List<String> getGroupId() { return groupId; }

	public void setGroupId(List<String> groupId) { this.groupId = groupId; }

	public List<String> getGroupName() { return groupName; }

	public void setGroupName(List<String> groupName) { this.groupName = groupName; }

	@Override
	public String toString() {
		return "OnLineGroupModel{" +
				"id='" + id + '\'' +
				", face='" + face + '\'' +
				", maxcount=" + maxcount +
				", status=" + status +
				", statusCode=" + statusCode +
				", uname='" + uname + '\'' +
				", count=" + count +
				", groupId=" + groupId +
				", groupName=" + groupName +
				'}';
	}
}