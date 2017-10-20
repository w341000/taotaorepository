package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;

	@Override
	public TaotaoResult addCartItem(long itemId, int num,
			HttpServletRequest request, HttpServletResponse response) {
		// 取购物车购物项列表
		List<CartItem> list = getCartItemList(request);
		CartItem cartItem = null;
		for (CartItem cItem : list) {
			if (cItem.getId() == itemId) {//购物车存在商品
				 cItem.setNum(cItem.getNum()+num);
				 cartItem=cItem;
				break;
			}
		}
		if (cartItem == null) {//购物车不存在商品
			// 取商品信息
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL
					+ itemId);
			cartItem=new CartItem();
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json,
					ItemInfo.class);
			if (taotaoResult.getStatus() == 200) {
				ItemInfo item = (ItemInfo) taotaoResult.getData();
				cartItem.setId(item.getId());
				cartItem.setTitle(item.getTitle());
				cartItem.setImage(item.getImages() == null ? "" : item
						.getImages()[0]);
				cartItem.setNum(num);
				cartItem.setPrice(item.getPrice());
			}
			list.add(cartItem);//将新商品添加到购物车
		}
		//把购物车写到cookie中
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(list), true);
		return TaotaoResult.ok();
	}

	/**
	 * 从cookie取商品列表,如果cookie中没有则返回一个新的list
	 */
	public List<CartItem> getCartItemList(HttpServletRequest request) {
		// 从cookie取商品列表
		String cartJson = CookieUtils.getCookieValue(request, "TT_CART", true);
		if(StringUtils.isBlank(cartJson)){
			return new ArrayList<CartItem>();
		}
		List<CartItem> list = JsonUtils.jsonToList(cartJson, CartItem.class);
		if(list==null){
			return  new ArrayList<CartItem>();
		}
		return list;
	}

	@Override
	public TaotaoResult deleteCartItem(long itemId, HttpServletRequest request,
			HttpServletResponse response) {
		// 取购物车购物项列表
		List<CartItem> list = getCartItemList(request);
		if(list.size()==0){
			return TaotaoResult.build(400, "没有该购物项");
		}
		for (CartItem cItem : list) {
			if (cItem.getId() == itemId) {//购物车存在商品
				 list.remove(cItem);
				break;
			}
		}
		//把购物车写到cookie中
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(list), true);
		return TaotaoResult.ok();
	}

}
