package com.taotao.portal.service;

import com.taotao.portal.pojo.Order;

public interface OrderService {
	/**
	 * 
	 * @param order
	 * @return orderID 
	 */
	String createOrder(Order order); 

}
