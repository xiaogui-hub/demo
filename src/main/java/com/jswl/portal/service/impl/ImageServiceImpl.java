package com.jswl.portal.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jswl.portal.dao.ImageMapper;
import com.jswl.portal.entity.Image;
import com.jswl.portal.service.ImageService;
@Service
public class ImageServiceImpl implements ImageService{
	
	@Autowired 
	private ImageMapper imageMapper;
	
	//获取所有的图片（首页的上中下展示图片）
	public List<Image> findAllImages() {
		return imageMapper.selectByExample(null);
	}

	//后台获取图片分页信息
	public Page<Image> selectAllImages(Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		Page<Image> pages = (Page<Image>)imageMapper.selectByExample(null);
		return pages;
	}

	//添加图片
	public void saveImage(Image image) {
		imageMapper.insertSelective(image);
	}
	
	//删除图片
	public void deleteImagesByIds(Integer[] ids) {
		for (Integer id : ids) {
			imageMapper.deleteByPrimaryKey(id);
		}
	}

	
	
	
	
	
	
	
	
}
