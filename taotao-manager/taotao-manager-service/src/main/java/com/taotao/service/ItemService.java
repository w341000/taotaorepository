package com.taotao.service;

import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;

public interface ItemService {

	TbItem getItemById(Long id);
	/**
	 * 商品列表分页方法
	 * @param page 开始页(从1开始)
	 * @param rows 每页显示数量
	 */
	EUDateGridResult getItemList(int page,int rows);
	/**
	 * 保存商品数据
	 * @param item 商品
	 * @param desc 商品描述
	 * @param itemParam 商品规格数据
	 * @return
	 */
	TaotaoResult createItem(TbItem item,String desc,String itemParam);
}
