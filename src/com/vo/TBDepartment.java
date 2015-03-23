package com.vo;

import java.io.Serializable;
import java.util.Date;

public class TBDepartment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1412522853737575151L;
	private Long departId;
	private Long coId;
	private String name;
	private Long parentDepartId;
	private Long createUserId;
	private Date createDate;
	private Long modifyUserId;
	private Date modifyDate;
	private String remark;
	private Long version;
	private Integer recordStatus;
	public Long getDepartId() {
		return departId;
	}
	public void setDepartId(Long departId) {
		this.departId = departId;
	}
	public Long getCoId() {
		return coId;
	}
	public void setCoId(Long coId) {
		this.coId = coId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getParentDepartId() {
		return parentDepartId;
	}
	public void setParentDepartId(Long parentDepartId) {
		this.parentDepartId = parentDepartId;
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public Integer getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}
	
}
