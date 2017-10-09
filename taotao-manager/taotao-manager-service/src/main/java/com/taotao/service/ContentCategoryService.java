package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentCategoryService {

	/**
	 * 获得内容管理分类信息
	 * @param pid 父分类信息
	 * @return
	 */
	List<EUTreeNode> getCategoryList(Long pid);
	/**
	 * 保存内容分类
	 * @param parentid
	 * @param name
	 */
	TaotaoResult insertContentCategory(Long parentid,String name);
}
