package com.vo;

import java.util.Date;

public class LogVO {
	private Long logId;
	private Long userId;
	private String remoteIp;
	private String resourceTable;
	private Long resourceId;
	private Integer operationType;//0.未知 1.登录 2.�?�� 3.增加 4.修改 5.删除 6.查询
	private String descption;
	private String operationTypeDesc;
	private Date logTime;
	public Long getLogId() {
		return logId;
	}
	public void setLogId(Long logId) {
		this.logId = logId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getRemoteIp() {
		return remoteIp;
	}
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}
	public String getResourceTable() {
		return resourceTable;
	}
	public void setResourceTable(String resourceTable) {
		this.resourceTable = resourceTable;
	}
	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	public Integer getOperationType() {
		return operationType;
	}
	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}
	public String getDescption() {
		return descption;
	}
	public void setDescption(String descption) {
		this.descption = descption;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	public String getOperationTypeDesc() {
		return operationTypeDesc;
	}
	public void setOperationTypeDesc(String operationTypeDesc) {
		this.operationTypeDesc = operationTypeDesc;
	}
	
}
