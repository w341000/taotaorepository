package com.taotao.portal.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Resource
	private CartService cartService;

	@RequestMapping("/cartSuccess")
	public String cartSuccess() {
		return "cartSuccess";
	}

	@RequestMapping("/add/{itemId}")
	public String addCartItem(@PathVariable Long itemId,
			@RequestParam(defaultValue = "1") Integer num,
			HttpServletRequest request, HttpServletResponse response) {
		cartService.addCartItem(itemId, num, request, response);
		return "redirect:/cart/cartSuccess.html";
	}

	@RequestMapping("/cart")
	public String getCart(HttpServletRequest request, Model model) {
		List<CartItem> list = cartService.getCartItemList(request);
		model.addAttribute("cartList", list);
		return "cart";
	}

	@RequestMapping("/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId,
			HttpServletRequest request, HttpServletResponse response) {
		TaotaoResult result = cartService.deleteCartItem(itemId, request,
				response);
		return "redirect:/cart/cart.html";
	}

}
