package com.vo;

import java.util.Date;

/**
 * TbBaseRolegrp 实体类 Fri Sep 19 13:47:55 CST 2014 system
 */

public class TbBaseRolegrp {
	private Long rolegrpId;
	private String rolegrpName;
	private String note;
	private Long createUserId;
	private String createDate;
	private Long modifyUserId;
	private String modifyDate;
	private String createUserName;
	private String modifyUserName;

	public void setRolegrpId(Long rolegrpId) {
		this.rolegrpId = rolegrpId;
	}

	public Long getRolegrpId() {
		return rolegrpId;
	}

	public void setRolegrpName(String rolegrpName) {
		this.rolegrpName = rolegrpName;
	}

	public String getRolegrpName() {
		return rolegrpName;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNote() {
		return note;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Long getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getModifyUserName() {
		return modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}
}
