package com.vo;
import java.sql.*;

   /**
    * TConfigDictionary 实体类
    * Tue Sep 16 15:05:32 CST 2014 system
    */ 


public class TConfigDictionary{
	private Long id;
	private String keyword;
	private String name;
	private String deleteFlag;
	private String note;
	private Long seq;
	public void setId(Long id){
	this.id=id;
	}
	public Long getId(){
		return id;
	}
	public void setKeyword(String keyword){
	this.keyword=keyword;
	}
	public String getKeyword(){
		return keyword;
	}
	public void setName(String name){
	this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setDeleteFlag(String deleteFlag){
	this.deleteFlag=deleteFlag;
	}
	public String getDeleteFlag(){
		return deleteFlag;
	}
	public void setNote(String note){
	this.note=note;
	}
	public String getNote(){
		return note;
	}
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
}

