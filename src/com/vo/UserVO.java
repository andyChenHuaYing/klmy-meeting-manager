package com.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2572919400630357863L;
	private Long userId;
	private Long coId;
	private Long departId;
	private String userCode;
	private String userPassword;
	private Date expireDate;
	private Long createUserId;
	private Date createDate;
	private Long modifyUserId;
	private Date modifyDate;
	private String remark;
	private Long version;
	private Integer recordStatus;
	private Person person;
	private List<TBRole> roleList;
	private String userName;
	private String phone;
	private String email;
	private String departName;
	private Map roleAreaList;
    private Long normalAreaId;

    public Long getNormalAreaId() {
        return normalAreaId;
    }

    public void setNormalAreaId(Long normalAreaId) {
        this.normalAreaId = normalAreaId;
    }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCoId() {
		return coId;
	}

	public void setCoId(Long coId) {
		this.coId = coId;
	}

	public Long getDepartId() {
		return departId;
	}

	public void setDepartId(Long departId) {
		this.departId = departId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<TBRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<TBRole> roleList) {
		this.roleList = roleList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public Map getRoleAreaList() {
		return roleAreaList;
	}

	public void setRoleAreaList(Map roleAreaList) {
		this.roleAreaList = roleAreaList;
	}

}
