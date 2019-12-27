package com.jswl.portal.dto;

import java.io.Serializable;

import com.jswl.portal.common.util.DateUtil;
public class ResultDto<T> implements Serializable {

	private static final long serialVersionUID = 2150861474997325836L;

	private String code;
	
	private String msg;
	
	private Long operateTime;
	
	private T data;

	public ResultDto(String code, String msg) {
		this.code = code;
		this.msg = msg;
		this.operateTime = DateUtil.nowTime();
	}

	public ResultDto(String code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.operateTime = DateUtil.nowTime();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Long getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Long operateTime) {
		this.operateTime = operateTime;
	}
}