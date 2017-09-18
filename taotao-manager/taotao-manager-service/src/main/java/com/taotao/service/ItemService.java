package com.taotao.service;

import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(Long id);
	/**
	 * 商品列表分页方法
	 * @param page 开始页(从1开始)
	 * @param rows 每页显示数量
	 */
	EUDateGridResult getItemList(int page,int rows);
}
