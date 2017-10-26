package com.taotao.portal.service;

import javax.servlet.http.HttpServletRequest;

import com.taotao.portal.pojo.Order;

public interface OrderService {
	/**
	 * 
	 * @return orderID 
	 */
	String createOrder(Order order,HttpServletRequest request); 

}
