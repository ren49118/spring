package com.mmit.jpit.api.controller;

import java.util.ArrayList;
import java.util.List;

public class Message {
	private String responeMsg;
	private List<?> data= new ArrayList<>();
	public Message(String message, List<?> data) {
		super();
		this.responeMsg = message;
	
		this.data = data;
	}
	
	public String getResponeMsg() {
		return responeMsg;
	}

	public void setResponeMsg(String responeMsg) {
		this.responeMsg = responeMsg;
	}

	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	};
}
