package com.jt.common.vo;

import java.io.Serializable;

public class EasyUITree implements Serializable{
	
	private Long id;		//树形结构的Id
	private String text;	//树形结构的名称
	private String state;	//节点状态  open/closed
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
