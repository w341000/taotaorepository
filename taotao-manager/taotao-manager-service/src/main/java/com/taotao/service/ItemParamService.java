package com.taotao.service;

import com.taotao.common.pojo.EUDateGridResult;

public interface ItemParamService {

	/**
	 * 获得商品规格参数模板数据
	 * @param page
	 * @param rows
	 * @return
	 */
	EUDateGridResult getItemParamList(Integer page, Integer rows);

}
