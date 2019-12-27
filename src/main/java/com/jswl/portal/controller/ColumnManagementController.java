package com.jswl.portal.controller;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jswl.portal.common.util.ResultUtil;
import com.jswl.portal.dto.ColumnTreeNodeDto;
import com.jswl.portal.dto.ResultDto;
import com.jswl.portal.entity.Column;
import com.jswl.portal.service.ColumnService;
@Controller
@RequestMapping("/admin/column")
public class ColumnManagementController {

	@Autowired
	private ColumnService columnService;
	
	//跳转页面
	@RequestMapping("/toColumnManagePage")
	public String toColumnManagePage() {
		return "admin/column/column-manage";
	}
	
	//栏目管理页面展示
	@RequestMapping("/getColumnTreeList")
	@ResponseBody
	public ResultDto<List<ColumnTreeNodeDto>> getColumnTreeList(Model model){
		List<ColumnTreeNodeDto> list = new ArrayList<ColumnTreeNodeDto>();
		List<ColumnTreeNodeDto> list1 = columnService.findColumnTreeNodeDtos(1, null);
		List<ColumnTreeNodeDto> list2 = columnService.findColumnTreeNodeDtos(2, null);
		List<ColumnTreeNodeDto> list3 = columnService.findColumnTreeNodeDtos(3, null);
		list.addAll(list1);
		list.addAll(list2);
		list.addAll(list3);
		return ResultUtil.success(list);
	}
	
	//保存和增加下一级按钮
	@RequestMapping("/saveColumn")
	@ResponseBody
	public ResultDto<Integer> saveColumn(Column column){
		try {
			Integer id = columnService.SaveOrUpdateColumn(column);
			return ResultUtil.success(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.fail();
		}
	}
	
	//删除
	@RequestMapping("/delColumn")
	@ResponseBody
	public ResultDto<String> delColumn(Integer columnId){
		try {
			columnService.delColumn(columnId);
			return ResultUtil.success("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.fail("删除失败");
		}
	}
	
	
	
}
