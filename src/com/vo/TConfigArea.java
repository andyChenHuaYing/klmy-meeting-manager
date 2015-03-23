package com.vo;

import java.util.Date;
import java.util.List;

/**
 * TConfigArea 实体类 Wed Sep 03 14:24:07 CST 2014 system
 */

public class TConfigArea {
	private Long areaId;
	private String areaName;
	private Long areaLevel;
	private Long parentAreaId;
	private String note;
	private String parentAreaName;
	private List<TConfigArea> children;
	private Long createUserId;
	private Date createDate;
	private Long modifyUserId;
	private Date modifyDate;
	
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Long getAreaLevel() {
		return areaLevel;
	}
	public void setAreaLevel(Long areaLevel) {
		this.areaLevel = areaLevel;
	}
	public Long getParentAreaId() {
		return parentAreaId;
	}
	public void setParentAreaId(Long parentAreaId) {
		this.parentAreaId = parentAreaId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getParentAreaName() {
		return parentAreaName;
	}
	public void setParentAreaName(String parentAreaName) {
		this.parentAreaName = parentAreaName;
	}
	public List<TConfigArea> getChildren() {
		return children;
	}
	public void setChildren(List<TConfigArea> children) {
		this.children = children;
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

}
