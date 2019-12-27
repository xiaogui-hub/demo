package com.jswl.portal.controller;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.github.pagehelper.Page;
import com.jswl.portal.common.util.ResultUtil;
import com.jswl.portal.dto.ResultDto;
import com.jswl.portal.entity.Image;
import com.jswl.portal.service.ImageService;
@Controller
@RequestMapping("/admin/image")
public class ImageController {
	
	@Autowired
	private ImageService imageService;
	
	//信息展示页面跳转
	@RequestMapping("/toImageManagePage")
	public String toImageManagePage() {
		return "admin/image/image-manage";
	}
	
	//图片管理页面展示
	@RequestMapping("/getListPage")
	@ResponseBody
	public ResultDto<Map<String, Object>> getListPage(Integer page,Integer pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Image> images = imageService.selectAllImages(page, pageSize);
		map.put("rows", images.getResult());
		map.put("total", images.getTotal());
		return ResultUtil.success(map);
	}
	
	//添加图片页面跳转
	@RequestMapping("/toImageAddPage")
	public String toImageAddPage() {
		return "admin/image/image-add";
	}
	
	//添加图片
	@RequestMapping("/save")
	@ResponseBody
	public ResultDto<String> saveImage(Image image){
		try {
			imageService.saveImage(image);
			return ResultUtil.success("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.fail("添加失败");
		}
	}
	
	//删除图片
	@RequestMapping("/delByIds")
	@ResponseBody
	public ResultDto<String> delByIds(@RequestParam("ids[]")Integer[] ids){
		try {
			imageService.deleteImagesByIds(ids);
			return ResultUtil.success("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.fail("删除失败");
		}
	}
	
	//图片上传
	@RequestMapping("/upload")
	@ResponseBody
	public ResultDto<String> upload(MultipartFile file) throws IllegalStateException, IOException{
		// 设置上传路径
		Long mi = System.currentTimeMillis();
		String path = "D:/images/" + mi + "/";
		File file1 = new File(path);
		// 如果没有该文件夹就创建
		if (!file1.exists()) {
			file1.mkdirs();
		}
		String originalName = file.getOriginalFilename();
		File trueFile = new File(path + originalName);
		file.transferTo(trueFile);
		String url = "http://localhost:9090/admin/upload/" + mi + "/" + originalName;
		return ResultUtil.success("上传成功",url);
	}
	
	
	
}
