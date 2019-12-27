package com.jswl.portal.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jswl.portal.dao.MenuMapper;
import com.jswl.portal.dto.MenuTreeNodeDto;
import com.jswl.portal.entity.Menu;
import com.jswl.portal.service.MenuService;
@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuMapper menuMapper;
	
	//实现主页左侧功能
	public MenuTreeNodeDto findMenuInfo(Integer userId) {
		MenuTreeNodeDto leftMenu  = new MenuTreeNodeDto();
		List<Menu> menus = menuMapper.findMenusByUserId(userId);
		for (Menu menu : menus) {
			if (menu.getPid() == 0) {
				BeanUtils.copyProperties(menu, leftMenu);
				leftMenu.setChildNode(findChildNodeDtos(menu.getId(),menus));
			}
		}
		return leftMenu;
	}

	//递归查询子栏目
	public List<MenuTreeNodeDto> findChildNodeDtos(Integer id,List<Menu> menus){
		List<MenuTreeNodeDto>  dtos = new ArrayList<>();
		for (Menu menu : menus) {
			if (menu.getPid() == id) {
				MenuTreeNodeDto dto = new MenuTreeNodeDto();
				BeanUtils.copyProperties(menu, dto);
				dto.setChildNode(findChildNodeDtos(menu.getId(),menus));
				dtos.add(dto);
			}
		}
		return dtos;
	}
	
	
	
	
	
	
	
	
}
