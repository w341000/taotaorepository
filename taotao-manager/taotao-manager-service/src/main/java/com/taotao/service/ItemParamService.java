package com.taotao.service;

import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public interface ItemParamService {

	/**
	 * 获得商品规格参数模板数据
	 * @param page
	 * @param rows
	 * @return
	 */
	EUDateGridResult getItemParamList(Integer page, Integer rows);
	/**
	 * 根据商品类目id获取商品规格信息
	 * @param cid
	 * @return
	 */
	TaotaoResult getItemParamByCid(Long cid);
	/**
	 * 保存商品类目模板规格信息
	 * @param itemParam
	 * @return
	 */
	TaotaoResult insertItemParam(TbItemParam itemParam);

}
