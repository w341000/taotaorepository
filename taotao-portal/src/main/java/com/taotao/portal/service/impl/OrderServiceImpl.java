package com.taotao.portal.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbUser;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;
	@Override
	public String createOrder(Order order,HttpServletRequest request) {
		//补全用户信息
		TbUser user=(TbUser) request.getAttribute("user");
		order.setUserId(user.getId());
		order.setBuyerNick(user.getUsername());
		//提交订单,调用taotao-order服务
		String json = HttpClientUtil.doPostJson(ORDER_BASE_URL+ORDER_CREATE_URL, JsonUtils.objectToJson(order));
		TaotaoResult taotaoResult = TaotaoResult.format(json);
		if(taotaoResult!=null &&taotaoResult.getStatus()==200){
			return taotaoResult.getData().toString();//订单id
		}
		return "";
	}
}
