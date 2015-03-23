package com.vo;
import java.util.Date;
import java.util.List;

   /**
    * TbBaseDepartment 实体类
    * Wed Sep 17 10:45:11 CST 2014 system
    */ 


public class TbBaseDepartment{
	private Long departId;
	private Long coId;
	private String name;
	private Long parentDepartId;
	private String parentDepartName;
	private Long createUserId;
	private String createUser;
	private Date createDate;
	private Long modifyUserId;
	private Date modifyDate;
	private String modifyUser;
	private String remark;
	private Long version;
	private Long recordStatus;
	private Long areaId;
	private String areaName;
	private Long deptLevel;
	private List<TbBaseDepartment> children; 
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
	public String getParentDepartName() {
		return parentDepartName;
	}
	public void setParentDepartName(String parentDepartName) {
		this.parentDepartName = parentDepartName;
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
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
	public Long getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(Long recordStatus) {
		this.recordStatus = recordStatus;
	}
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
	public Long getDeptLevel() {
		return deptLevel;
	}
	public void setDeptLevel(Long deptLevel) {
		this.deptLevel = deptLevel;
	}
	public List<TbBaseDepartment> getChildren() {
		return children;
	}
	public void setChildren(List<TbBaseDepartment> children) {
		this.children = children;
	}
	
}

