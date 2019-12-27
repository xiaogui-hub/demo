package com.jswl.portal.service;
import java.util.List;

import com.github.pagehelper.Page;
import com.jswl.portal.entity.Image;
public interface ImageService {
	//获取所有的图片（首页的上中下展示图片）
	List<Image> findAllImages();
	//后台获取图片分页信息
	Page<Image> selectAllImages(Integer page,Integer pageSize);
	//添加图片
	void saveImage(Image image);
	//删除图片
	void deleteImagesByIds(Integer[] ids);
}
