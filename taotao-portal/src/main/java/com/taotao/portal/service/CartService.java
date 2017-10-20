package com.taotao.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;

public interface CartService {
	/**
	 * 添加购物车的 购物项
	 * @param itemId 商品id
	 * @param num 数量
	 * @return
	 */
	TaotaoResult addCartItem(long itemId,int num,HttpServletRequest request,HttpServletResponse response);
	/**
	 * 从cookie中获取购物车,如果cookie中没有则返回空的list
	 * @param request
	 * @return
	 */
	List<CartItem> getCartItemList(HttpServletRequest request);
	/**
	 * 删除购物项
	 * @param itemId
	 * @param request
	 * @param response
	 * @return
	 */
	TaotaoResult deleteCartItem(long itemId,HttpServletRequest request,HttpServletResponse response);

}
