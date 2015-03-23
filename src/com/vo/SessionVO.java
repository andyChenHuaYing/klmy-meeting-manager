package com.vo;

import java.util.Date;

public class SessionVO {
	private String sessionId;
	private Date createTime;
	private Date lastUpdateTime;
	
	public SessionVO(String sessionId,Date createTime){
		this.sessionId = sessionId;
		this.createTime = createTime;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	
}
