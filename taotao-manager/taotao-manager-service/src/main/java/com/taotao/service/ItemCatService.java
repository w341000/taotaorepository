package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;

/**
 * 商品类目service
 *
 */
public interface ItemCatService {
	/**
	 * 获得商品类目列表
	 * @param pid 商品父类目
	 */
	public List<EUTreeNode> getItemCatList(long pid);
}
