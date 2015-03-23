package com.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import net.sf.json.JSONObject;


public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -748297288562519142L;
	private Long personId;
	private Long coId;
	private String personCode;
	private String personName;
	private Integer sex;
	private String birth;
	private String idCard;
	private String medicareCard;
	private String mobile;
	private String tel;
	private String education;
	private String national;
	private String origin;
	private Integer ismarried;
	private Integer height;
	private String address;
	private Long createUserId;
	private String createDate;
	private Long modifyUserId;
	private String modifyDate;
	private String remark;
	private Integer version;
	private Integer recordStatus;
	private Long medicalRecordNumber;
	private Long departId;
	private String departName;
	
	public Long getMedicalRecordNumber() {
		return medicalRecordNumber;
	}
	public void setMedicalRecordNumber(Long medicalRecordNumber) {
		this.medicalRecordNumber = medicalRecordNumber;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public Long getCoId() {
		return coId;
	}
	public void setCoId(Long coId) {
		this.coId = coId;
	}
	public String getPersonCode() {
		return personCode;
	}
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getMedicareCard() {
		return medicareCard;
	}
	public void setMedicareCard(String medicareCard) {
		this.medicareCard = medicareCard;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getNational() {
		return national;
	}
	public void setNational(String national) {
		this.national = national;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public Integer getIsmarried() {
		return ismarried;
	}
	public void setIsmarried(Integer ismarried) {
		this.ismarried = ismarried;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Long getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Integer getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

	public Long getDepartId() {
		return departId;
	}
	public void setDepartId(Long departId) {
		this.departId = departId;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
}
