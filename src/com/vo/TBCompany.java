package com.vo;

import java.io.Serializable;
import java.util.Date;

public class TBCompany implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2229160827726567696L;
	
	private Long coId;
	private String coCode;
	private String coName;
	private String shortName;
	private String postalAddress;
	private String postalCode;
	private String officeTel;
	private String email;
	private String website;
	private Long createUserId;
	private Date createDate; 
	private Long modifyUserId;
	private Date modifyDate;
	private String remark;
	private Long version;
	private Integer recordstatus;
	
	public Long getCoId() {
		return coId;
	}
	public void setCoId(Long coId) {
		this.coId = coId;
	}
	public String getCoCode() {
		return coCode;
	}
	public void setCoCode(String coCode) {
		this.coCode = coCode;
	}
	public String getCoName() {
		return coName;
	}
	public void setCoName(String coName) {
		this.coName = coName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getOfficeTel() {
		return officeTel;
	}
	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
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
	public Integer getRecordstatus() {
		return recordstatus;
	}
	public void setRecordstatus(Integer recordstatus) {
		this.recordstatus = recordstatus;
	}

	
	
}
