package com.robot.sanlian.common;


public class ResponseResult {
	private String optId;
	private String token;
	private boolean result;
	private String message;
//	private String sessionid; 
	private Page page;
	private Object obj;
	public String getToken() {
		return token;
	}
	public String getOptId() {
		return optId;
	}
	public void setOptId(String optId) {
		this.optId = optId;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
//	public String getSessionid() {
//		return sessionid;
//	}
//	public void setSessionid(String sessionid) {
//		this.sessionid = sessionid;
//	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
}
