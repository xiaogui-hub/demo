package com.jswl.portal.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class MenuTreeNodeDto implements Serializable {

	private static final long serialVersionUID = -5238464300900812377L;
	
	private Integer id;

	private String name;
	
	private String url;
	
	private Integer type;
	
	private String icon;
	
	private Integer sort;
	
	private String target;
	
	private List<MenuTreeNodeDto> childNode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<MenuTreeNodeDto> getChildNode() {
		return childNode;
	}

	public void setChildNode(List<MenuTreeNodeDto> childNode) {
		this.childNode = childNode;
	}

	public String getTarget() {
		if(StringUtils.isBlank(this.target)) {
			return "main";
		}
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
