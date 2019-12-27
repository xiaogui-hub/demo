package com.jswl.portal.service;

import com.jswl.portal.entity.User;

public interface UserService {
	//验证登录的账号和密码
	User checkUserMsg(String usercode,String password);
}
