package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

public interface ItemService {

	TaotaoResult getItemBaseInfo(Long itemid);
	TaotaoResult getItemDesc(Long itemid);
	/**
	 * 获取商品规格参数信息
	 * @param itemid 商品id
	 * @return
	 */
	TaotaoResult getItemParam(Long itemid);
}
