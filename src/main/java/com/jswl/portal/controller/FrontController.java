package com.jswl.portal.controller;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jswl.portal.dto.ArticleDto;
import com.jswl.portal.dto.ColumnTreeNodeDto;
import com.jswl.portal.entity.Article;
import com.jswl.portal.entity.Image;
import com.jswl.portal.service.ArticleService;
import com.jswl.portal.service.ColumnService;
import com.jswl.portal.service.ImageService;
@Controller
@RequestMapping("/front")
public class FrontController {
	@Autowired
	private ImageService imageService;
	@Autowired
	private ColumnService columnService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	//显示页面信息
	@GetMapping("/index")
	public String index(Model model) {

		//主页图片显示
		List<Image> images = imageService.findAllImages();
		List<Image> pic1List = new ArrayList<>();
		List<Image> pic2List = new ArrayList<>();
		List<Image> pic3List = new ArrayList<>();
		for (Image image : images) {
			switch (image.getType()) {
			case 1:
				pic1List.add(image);
				break;
			case 2:
				pic2List.add(image);
				break;
			default:
				pic3List.add(image);
				break;
			}
		}
		model.addAttribute("pic1List",pic1List);
		model.addAttribute("pic2List",pic2List);
		model.addAttribute("pic3List",pic3List);
		
		//主页最新动态
		ColumnTreeNodeDto column = columnService.findColumnTreeNodeDto(19, 4);
		model.addAttribute("topColumn",column);
		
		//主页中间内容显示
		List<ColumnTreeNodeDto> mainColumn = columnService.findColumnTreeNodeDtos(2, 5);
		model.addAttribute("mainColumnList",mainColumn);
		
		//主页底部信息显示
		List<ColumnTreeNodeDto> footColumn = columnService.findColumnTreeNodeDtos(3, 5);
		model.addAttribute("bottomColumnList",footColumn);
		
		return "front/index";
	}
	
	//实现首页页面跳转
	@RequestMapping("/articleDetailById")
	public String detile(Integer articleId,Model model) {
		//获取文章信息
		ArticleDto articleDto = articleService.selectArticleDtoById(articleId);
		//跳转到页面时获取之前的点击次数。
		Double score = stringRedisTemplate.opsForZSet().score("clicktime", articleId+"");
		//判断该文章是不是第一次被访问
		if (score==null) {
			//第一次被访问把被访问的次数设置为1
			score=1d;
			stringRedisTemplate.opsForZSet().add("clicktime",articleId+"",score);
		}else {
			//不是第一次被访问每次访问次数+1
			score=score+1;
			stringRedisTemplate.opsForZSet().add("clicktime",articleId+"",score);
		}
		//将被访问的次数封装到articleDto中
		articleDto.setReadNum(score.longValue());
		//上一页
		Article pre = articleService.beforeAsThisPage(articleDto);
		//下一页
		Article next = articleService.afterAsThisPage(articleDto);
		//将articleDto传给前台
		model.addAttribute("detail",articleDto);
		model.addAttribute("pre",pre);
		model.addAttribute("next",next);
		return "front/article-detail";
	}
	
	//更多
	@RequestMapping("/articleMore")
	public String articleMore(Integer columnId,Model model) {
		ColumnTreeNodeDto findMoreById = columnService.findMoreById(columnId);
		model.addAttribute("column",findMoreById);
		if (findMoreById.getChildNode()!=null  && findMoreById.getChildNode().size() != 0) {
			return "front/article-column";
		}
		return "front/article-more";
	}
	
	//文章模糊查询
	@RequestMapping("/articleSearch")
	public String selectLikeTitle(String word,Model model) {
		//调取方法查询
		List<Article> findArticleByTitle = articleService.findArticleByTitle(word);
		//传递给前台
		model.addAttribute("list",findArticleByTitle);
		return "front/article-search";
	}
	
	//跳转公司简介静态页面
	@RequestMapping("/companyProfile")
	public String companyProfile() {
		return "front/company-profile";
	}
	
	
	
	
	
}
