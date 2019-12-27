package com.jswl.portal.controller;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller
@RequestMapping("/ueditor")
public class UploadController {
	
	@RequestMapping("/imgUpdate")
	@ResponseBody
	public Map<String, Object> uploadImages(MultipartFile upfile) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 设置上传路径
			Long mi = System.currentTimeMillis();
			String path = "D:/images/" + mi + "/";
			File file = new File(path);
			// 如果没有该文件夹就创建
			if (!file.exists()) {
				file.mkdirs();
			}
			String originalName = upfile.getOriginalFilename();
			File trueFile = new File(path + originalName);
			upfile.transferTo(trueFile);
			map.put("state", "SUCCESS");
			map.put("url", "http://localhost:9090/admin/upload/" + mi + "/" + originalName);
			map.put("title", originalName);
			map.put("original", originalName);
			return map;
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			map.put("state", "文件上传失败");
			map.put("url", "");
			map.put("title", "");
			map.put("original", "");
			return map;
		}
	}
}
