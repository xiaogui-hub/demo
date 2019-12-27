package com.jswl.portal.service;

import java.util.List;

import com.jswl.portal.dto.ColumnTreeNodeDto;
import com.jswl.portal.entity.Column;

public interface ColumnService {
	/**
	 * 查询最新动态的方法
	 * @param columnId:要查询的栏目名（最新动态的id）
	 * @param num：要显示的数量
	 * @return
	 */
	ColumnTreeNodeDto findColumnTreeNodeDto(Integer columnId,Integer num);
	/**
	 * 查询主页其他信息的方法
	 * @param columnId:要查询的栏目名
	 * @param num：要显示的数量
	 * @return
	 */
    List<ColumnTreeNodeDto> findColumnTreeNodeDtos(Integer type,Integer num);
	/**
	 * 显示更多的方法
	 * @param columnId:要查询的栏目名
	 */
    ColumnTreeNodeDto findMoreById(Integer columnId);
    
    //后台更新或者保存栏目管理信息
    Integer SaveOrUpdateColumn(Column column);
    
    //删除
    void delColumn(Integer id);
    
    
}
