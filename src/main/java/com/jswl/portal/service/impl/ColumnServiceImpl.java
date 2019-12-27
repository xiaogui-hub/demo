package com.jswl.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jswl.portal.dao.ArticleMapper;
import com.jswl.portal.dao.ColumnMapper;
import com.jswl.portal.dto.ArticleDto;
import com.jswl.portal.dto.ColumnTreeNodeDto;
import com.jswl.portal.dto.ResultDto;
import com.jswl.portal.entity.Article;
import com.jswl.portal.entity.ArticleExample;
import com.jswl.portal.entity.Column;
import com.jswl.portal.entity.ColumnExample;
import com.jswl.portal.entity.ColumnExample.Criteria;
import com.jswl.portal.service.ColumnService;
@Service
public class ColumnServiceImpl implements ColumnService{

	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private ColumnMapper columnMapper;
	
	//最新动态实现代码
	public ColumnTreeNodeDto findColumnTreeNodeDto(Integer columnId, Integer num) {
		Column column = columnMapper.selectByPrimaryKey(columnId);
		ColumnTreeNodeDto columnTreeNodeDto = new ColumnTreeNodeDto();
		BeanUtils.copyProperties(column, columnTreeNodeDto );
		List<Article> findArticles = articleMapper.findArticles(columnId, num);
		List<ArticleDto> articleDtos = getArticleDtos(column,findArticles);
		columnTreeNodeDto.setArticleList(articleDtos);
		return columnTreeNodeDto;
	}
	
	//封装获取List<ArticleDto>的方法
	private List<ArticleDto>  getArticleDtos(Column column,List<Article> findArticles) {
		List<ArticleDto> articleDtos = new ArrayList<>();
		for (Article article : findArticles) {
			ArticleDto articleDto =new ArticleDto();
			BeanUtils.copyProperties(article, articleDto);
			articleDto.setColumnCode(column.getCode());
			articleDto.setColumnTitle(column.getName());
			articleDtos.add(articleDto);
		}
		return articleDtos;
	}
	
	//主页其他内容的显示
	public List<ColumnTreeNodeDto> findColumnTreeNodeDtos(Integer type,Integer num) {
		//构建返回参数
		 List<ColumnTreeNodeDto> columnTreeNodeDtos = new ArrayList<ColumnTreeNodeDto>();
		 //构建查询样例
		 ColumnExample example = new ColumnExample();
		 //构建查询条件
		 Criteria createCriteria = example.createCriteria();
		 //定义查询条件
		 createCriteria.andTypeEqualTo(type);
		 //定义排序方式
		 example.setOrderByClause("sort");
		 //查询获得column
		List<Column> columns = columnMapper.selectByExample(example);
		//循环遍历赋值
		for (Column column : columns) {
			//判断是否是顶级目录
			if (column.getPid()==0) {
				//构建list集合值
				ColumnTreeNodeDto columnTreeNodeDto = new ColumnTreeNodeDto();
				//赋值
				BeanUtils.copyProperties(column, columnTreeNodeDto);
				//查询栏目下的文章信息
				List<Article> findArticles = articleMapper.findArticles(column.getId(), num);
				//将文章信息转化为dto格式
				List<ArticleDto> articleDtos = getArticleDtos(column,findArticles);
				//存储文章信息
				columnTreeNodeDto.setArticleList(articleDtos);
				//递归获得顶级目录下的所有子目录
				List<ColumnTreeNodeDto> childNode = getAllChildsPart(column.getId(),columns,num);
				//将子目录存入
				columnTreeNodeDto.setChildNode(childNode);
				//把值放到list集合里
				columnTreeNodeDtos.add(columnTreeNodeDto);
			}
		}
		return columnTreeNodeDtos;
	}
	
	//递归获得所有子栏目
	private List<ColumnTreeNodeDto> getAllChildsPart(Integer id,List<Column> columns,Integer num) {
		//构建返回值
		List<ColumnTreeNodeDto> childDtos = new ArrayList<ColumnTreeNodeDto>();
		//遍历所有column找到子栏目
		for (Column column : columns) {
			//找到子类的条件
			if (column.getPid() == id) {
				//构建list集合的值
				ColumnTreeNodeDto childDto = new ColumnTreeNodeDto();
				//赋值
				BeanUtils.copyProperties(column, childDto);
				//查询栏目下的文章信息
				List<Article> findArticles = articleMapper.findArticles(column.getId(), num);
				//将文章信息转化为dto格式
				List<ArticleDto> articleDtos = getArticleDtos(column,findArticles);
				//将文章信息存入
				childDto.setArticleList(articleDtos);
				//递归
				childDto.setChildNode(getAllChildsPart(column.getId(),columns,num));
				childDtos.add(childDto);
			}
		}
		return childDtos;
	}

	//显示更多
	public ColumnTreeNodeDto findMoreById(Integer columnId) {
		//构建返回对象
		ColumnTreeNodeDto columnTreeNodeDto = new ColumnTreeNodeDto();
		//查询column
		Column column = columnMapper.selectByPrimaryKey(columnId);
		//赋值
		BeanUtils.copyProperties(column, columnTreeNodeDto);
		//查询所有文章信息
		List<Article> findArticles = articleMapper.findArticles(columnId, null);
		//将文章信息转换为dto
		List<ArticleDto> articleDtos = getArticleDtos(column,findArticles);
		columnTreeNodeDto.setArticleList(articleDtos);
		//构建查询条件
		ColumnExample  example = new ColumnExample();
		Criteria criteria = example.createCriteria();
		criteria.andPidEqualTo(columnId);
		//根据条件查询column的集合
		List<Column> columns = columnMapper.selectByExample(example);
		//创建子栏目集合
		List<ColumnTreeNodeDto> columnTreeNodeDtos = new ArrayList<ColumnTreeNodeDto>();
		//判断是否有子栏目
		if (columns != null && columns.size() != 0) {
			//有子栏目
			//循环遍历赋值
			for (Column c : columns) {
				//构建list集合值
				ColumnTreeNodeDto dto = new ColumnTreeNodeDto();
				//赋值
				BeanUtils.copyProperties(c, dto);
				//查询栏目下的文章信息
				List<Article> childArticles = articleMapper.findArticles(c.getId(), null);
				//将文章信息转化为dto格式
				List<ArticleDto> at_dto = getArticleDtos(c,childArticles);
				//存储文章信息
				dto.setArticleList(at_dto);
				//递归获得顶级目录下的所有子目录
				List<ColumnTreeNodeDto> childNode = getAllChildsPart(c.getId(),columns,null);
				//将子目录存入
				dto.setChildNode(childNode);
				//把值放到list集合里
				columnTreeNodeDtos.add(dto);
			}
		}
		columnTreeNodeDto.setChildNode(columnTreeNodeDtos);
		return columnTreeNodeDto;
	}

	 //后台更新或者保存栏目管理信息
	public Integer SaveOrUpdateColumn(Column column) {
		if (column.getId() == null ) {
			//新增
			columnMapper.insertSelective(column);
			return column.getId();
		}else {
			//修改
			columnMapper.updateByPrimaryKeySelective(column);
			return column.getId();
		}
	}

	//删除
	public void delColumn(Integer id) {
		columnMapper.deleteByPrimaryKey(id);
	}
	
	
	
	
	
	
	
}
