package com.jswl.portal.service;

import com.jswl.portal.dto.MenuTreeNodeDto;

public interface MenuService {
	//实现主页左侧功能
	MenuTreeNodeDto findMenuInfo(Integer userId);
}
