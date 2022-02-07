package com.sobot.online.model;

/**
 * 公共实体例，针对只有status字段的情况
 */
public class OnlineCommonModel {

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OnlineCommonModel{" +
				"status='" + status + '\'' +
				'}';
	}
}
