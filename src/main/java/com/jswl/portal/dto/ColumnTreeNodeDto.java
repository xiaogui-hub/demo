package com.jswl.portal.dto;
import java.io.Serializable;
import java.util.List;
public class ColumnTreeNodeDto implements Serializable {

	private static final long serialVersionUID = -5238464300900812377L;

	private Integer id;

	private String code;

	private String name;

	private Integer pid;

	private Integer sort;

	private Integer type;

	private String style;

	private List<ColumnTreeNodeDto> childNode;
	
	private List<ArticleDto> articleList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public List<ColumnTreeNodeDto> getChildNode() {
		return childNode;
	}

	public void setChildNode(List<ColumnTreeNodeDto> childNode) {
		this.childNode = childNode;
	}
	
	public List<ArticleDto> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<ArticleDto> articleList) {
		this.articleList = articleList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ColumnTreeNodeDto [id=" + id + ", code=" + code + ", name=" + name + ", pid=" + pid + ", sort=" + sort
				+ ", type=" + type + ", style=" + style + ", childNode=" + childNode + ", articleList=" + articleList
				+ "]";
	}
	
	
}
