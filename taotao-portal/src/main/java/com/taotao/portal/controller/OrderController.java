package com.taotao.portal.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.CartService;
import com.taotao.portal.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Resource
	private CartService cartService;
	@Resource
	private OrderService orderService;
	@RequestMapping("/order-cart")
	public String showOrderCart(HttpServletRequest request,Model model){
		List<CartItem> list = cartService.getCartItemList(request);
		model.addAttribute("cartList", list);
		return "order-cart";
	}
	
	@RequestMapping("/create")
	public String createOrder(Order order,Model model){
		try {
			String orderId = orderService.createOrder(order);
			model.addAttribute("orderId", orderId);
			model.addAttribute("payment", order.getPayment());
			model.addAttribute("orderId", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "创建订单出错.请重试");
			return "error/exception";
		}
	}
}
