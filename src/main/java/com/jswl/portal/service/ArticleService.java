package com.jswl.portal.service;
import java.util.List;
import com.github.pagehelper.Page;
import com.jswl.portal.dto.ArticleDto;
import com.jswl.portal.entity.Article;
public interface ArticleService {
	//根据前台传递的文章ID查询文章
	ArticleDto selectArticleDtoById(Integer id);
	 //跳转上一页
	Article beforeAsThisPage(ArticleDto articleDto);
	//跳转下一页
	Article afterAsThisPage(ArticleDto articleDto);
	//文章模糊查询
	List<Article> findArticleByTitle(String title);
	//后台查询所有文章展示
	Page<ArticleDto> getListPage(Integer pageNum,Integer pageSize);
	//文章增加
	void saveArticle(Article article);
	//文章修改
	void updateArticle(Article article);
	//文章删除
	void deleteByIds(Integer[] ids);
}
