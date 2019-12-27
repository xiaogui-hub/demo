package com.jswl.portal.service.impl;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jswl.portal.dao.ArticleMapper;
import com.jswl.portal.dao.ColumnMapper;
import com.jswl.portal.dto.ArticleDto;
import com.jswl.portal.entity.Article;
import com.jswl.portal.entity.ArticleExample;
import com.jswl.portal.entity.ArticleExample.Criteria;
import com.jswl.portal.entity.Column;
import com.jswl.portal.service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private ColumnMapper columnMapper;
	
	//点文章进入阅读模式
	public ArticleDto selectArticleDtoById(Integer id) {
		//构建返回参数
		ArticleDto articleDto = new ArticleDto();
		//查询article基础信息
		Article article = articleMapper.selectByPrimaryKey(id);
		//赋值
		BeanUtils.copyProperties(article, articleDto);
		//根据文章id对应的栏目id查询栏目信息
		Column column = columnMapper.selectByPrimaryKey(article.getColumnId());
		//通过栏目信息补全articleDto封装信息
		articleDto.setColumnCode(column.getCode());
		articleDto.setColumnTitle(column.getName());
		return articleDto;
	}

	//上一页
	public Article beforeAsThisPage(ArticleDto articleDto) {
		return articleMapper.beforeAsThisPage(articleDto);
	}

	//下一页
	public Article afterAsThisPage(ArticleDto articleDto) {
		return articleMapper.afterAsThisPage(articleDto);
	}

	//文章模糊查询
	public List<Article> findArticleByTitle(String title) {
		//构建查询样例
		ArticleExample example = new ArticleExample();
		//构建查询条件
		Criteria criteria = example.createCriteria();
		criteria.andTitleLike("%"+title+"%");
		//按照样例模糊查询
		List<Article> selectByExample = articleMapper.selectByExample(example );
		return selectByExample;
	}

	//后台展示所有的文章信息
	public Page<ArticleDto> getListPage(Integer page,Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		Page<ArticleDto> dtos = (Page<ArticleDto>)articleMapper.findAllArticleDtos();
		return dtos;
	}
	
	//文章增加
	public void saveArticle(Article article) {
		articleMapper.insertSelective(article);
	}

	//文章修改
	public void updateArticle(Article article) {
		articleMapper.updateByPrimaryKeySelective(article);
	}

	//文章删除
	public void deleteByIds(Integer[] ids) {
		for (Integer id : ids) {
			articleMapper.deleteByPrimaryKey(id);
		}
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
