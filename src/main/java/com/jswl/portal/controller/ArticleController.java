package com.jswl.portal.controller;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.Page;
import com.jswl.portal.common.util.ResultUtil;
import com.jswl.portal.dto.ArticleDto;
import com.jswl.portal.dto.ResultDto;
import com.jswl.portal.entity.Article;
import com.jswl.portal.entity.User;
import com.jswl.portal.service.ArticleService;
@Controller
@RequestMapping("/admin/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	//跳转文章显示页面
	@RequestMapping("/toArticleManagePage")
	public String toArticleManagePage() {
		return "admin/article/article-manage";
	}
	 
	//文章展示
	@RequestMapping("/getListPage")
	@ResponseBody
	public ResultDto<Map<String, Object>> getListPage(Integer pageSize,Integer page){
		Page<ArticleDto> listPage = articleService.getListPage(page, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", listPage.getResult());
		map.put("total", listPage.getTotal());
		return ResultUtil.success(map);
	}
	
	//文章添加页面跳转
	@RequestMapping("/toArticleAddPage")
	public String toArticleAddPage() {
		return "admin/article/article-add";
	}
	
	//文章添加功能实现
	@RequestMapping("/save")
	@ResponseBody
	public ResultDto<String> saveArticle(Article article,HttpSession session){
		User user = (User)session.getAttribute("user");
		if (user == null) {
			return ResultUtil.fail("用户session失效,需要登录");
		}
		article.setCreateUserId(user.getId());
		article.setModifyUserId(user.getId());
		article.setCreateTime(System.currentTimeMillis());
		article.setModifyTime(System.currentTimeMillis());
		article.setReleasTime(System.currentTimeMillis());
		article.setStatus(new Byte("1"));
		try {
			articleService.saveArticle(article);
			return ResultUtil.success("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.fail("保存失败");
		}
	}
	
	//文章修改页面跳转
	@RequestMapping("/toArticleEditPage")
	public String toArticleEditPage(Integer id,Model model) {
		model.addAttribute("id",id);
		return "admin/article/article-edit";
	}
	
	//文章原始信息展现
	@RequestMapping("/getDetailById")
	@ResponseBody
	public ResultDto<ArticleDto> getDetailById(Integer id){
		ArticleDto articleDto = articleService.selectArticleDtoById(id);
		return ResultUtil.success(articleDto);
	}
	
	//文章修改功能
	@RequestMapping("/update")
	@ResponseBody
	public ResultDto<String> updateArticle(Article article){
		try {
			articleService.updateArticle(article);
			return ResultUtil.success("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.fail("修改失败");
		}
	}
	
	//文章删除功能
	@RequestMapping("/delByIds")
	@ResponseBody
	public ResultDto<String> delByIds(@RequestParam("ids[]")Integer[] ids){
		try {
			articleService.deleteByIds(ids);
			return ResultUtil.success("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.fail("删除失败");
		}
	}
	
}
