package com.taotao.order.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.order.pojo.Order;
import com.taotao.order.service.OrderService;

@Controller
// 订单controller
public class OrderController {
	@Resource
	private OrderService orderService;
	public OrderController() {
		System.out.println(11);
	}

	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createOrder(@RequestBody Order order) {
		try{
		TaotaoResult result = orderService.createOrder(order,
				order.getOrderItems(), order.getOrderShipping());
		return result;
		}catch(Exception e){
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

}
