package com.jswl.portal.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jswl.portal.dao.UserMapper;
import com.jswl.portal.entity.User;
import com.jswl.portal.entity.UserExample;
import com.jswl.portal.entity.UserExample.Criteria;
import com.jswl.portal.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	 private UserMapper usermapper;
	
	//验证登录的账号和密码
	public User checkUserMsg(String usercode, String password) {
		//构建查询样例
		UserExample example = new UserExample();
		//构建查询条件
		Criteria criteria = example.createCriteria();
		criteria.andUserCodeEqualTo(usercode).andPasswordEqualTo(password);
		//根据条件查询
		List<User> users = usermapper.selectByExample(example);
		//判断查询是否存在账户（用户密码是否正确）
		if (users != null) {
			//正确
			User user = users.get(0);
			return user;
		}
		//失败
		return null;
	}

	
	
	
	
}
