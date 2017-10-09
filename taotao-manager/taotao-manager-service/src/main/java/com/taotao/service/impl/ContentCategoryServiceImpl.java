package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.service.ContentCategoryService;
@Service 
/**
 * 内容管理service
 */
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Resource
	private TbContentCategoryMapper contentCategoryMapper;

	@Override
	public List<EUTreeNode> getCategoryList(Long pid) {
		//根据pid查询节点列表
		TbContentCategoryExample example=new TbContentCategoryExample();
		example.createCriteria().andParentIdEqualTo(pid);
		//执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EUTreeNode> treeNodes=new ArrayList<EUTreeNode>();
		for (TbContentCategory tbContentCategory : list) {
			EUTreeNode treeNode=new EUTreeNode();
			treeNode.setId(tbContentCategory.getId());
			treeNode.setState(tbContentCategory.getIsParent()?"closed":"open");
			treeNode.setText(tbContentCategory.getName());
			treeNodes.add(treeNode);
		}
		return treeNodes;
	}

	@Override
	public TaotaoResult insertContentCategory(Long parentid, String name) {
		TbContentCategory contentCategory=new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		contentCategory.setStatus(1);
		contentCategory.setParentId(parentid);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		//添加记录
		contentCategoryMapper.insert(contentCategory);
		//查看父节点isparent是否为true
		TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentid);
		if(!parentCat.getIsParent()){
			parentCat.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parentCat);
		}
		//返回结果
		return TaotaoResult.ok(contentCategory);
	}

}
