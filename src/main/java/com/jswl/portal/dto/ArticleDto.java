package com.jswl.portal.dto;

public class ArticleDto {
	
	private Integer id;

    private String title;

    private Integer columnId;
    
    private String columnCode;
    
    private String columnTitle;

    private Long releasTime;

    private Integer status;

    private Integer sort;

    private Integer recommend;

    private Long createTime;

    private String content;
    
    private Integer createUserId;
    
    private String createUserName;

    private Integer modifyUserId;
    
    private String modifyUserName;
    //读取的次数
    private Long readNum;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getColumnId() {
		return columnId;
	}

	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}

	public String getColumnCode() {
		return columnCode;
	}

	public void setColumnCode(String columnCode) {
		this.columnCode = columnCode;
	}

	public String getColumnTitle() {
		return columnTitle;
	}

	public void setColumnTitle(String columnTitle) {
		this.columnTitle = columnTitle;
	}

	public Long getReleasTime() {
		return releasTime;
	}

	public void setReleasTime(Long releasTime) {
		this.releasTime = releasTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Integer getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Integer modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getModifyUserName() {
		return modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	public Long getReadNum() {
		return readNum;
	}

	public void setReadNum(Long readNum) {
		this.readNum = readNum;
	}

	@Override
	public String toString() {
		return "ArticleDto [id=" + id + ", title=" + title + ", columnId=" + columnId + ", columnCode=" + columnCode
				+ ", columnTitle=" + columnTitle + ", releasTime=" + releasTime + ", status=" + status + ", sort="
				+ sort + ", recommend=" + recommend + ", createTime=" + createTime + ", content=" + content
				+ ", createUserId=" + createUserId + ", createUserName=" + createUserName + ", modifyUserId="
				+ modifyUserId + ", modifyUserName=" + modifyUserName + ", readNum=" + readNum + "]";
	}
	
	
}
