package com.sobot.online.api;

import java.util.List;

public class OnlineBaseListThirdCode<T> {

	private String retCode;
	private List<T> items;

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}
}