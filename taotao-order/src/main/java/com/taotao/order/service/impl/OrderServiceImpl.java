package com.taotao.order.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.dao.JedisClient;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

@Service
public class OrderServiceImpl implements OrderService {
	@Resource
	private TbOrderMapper orderMapper;
	@Resource
	private TbOrderItemMapper orderItemMapper;
	@Resource
	private TbOrderShippingMapper orderShippingMapper;
	@Resource
	private JedisClient jedisClient;
	@Value("${ORDER_GEN_KEY}")
	private String ORDER_GEN_KEY;
	@Value("${ORDER_DETAIL_GEN_KEY}")
	private String ORDER_DETAIL_GEN_KEY;//订单明细key
	@Value("${ORDER_INIT_ID}")
	private String ORDER_INIT_ID;//初始化的id

	@Override
	public TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList,
			TbOrderShipping orderShipping) {
		//插入订单表
		String str = jedisClient.get(ORDER_GEN_KEY);
		if(StringUtils.isBlank(str)){
			jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
		}
		Long orderId = jedisClient.incr(ORDER_GEN_KEY);
		order.setOrderId(orderId+"");
		order.setStatus(1);//状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
		order.setCreateTime(new Date());
		order.setUpdateTime(new Date());
		order.setBuyerRate(0);//1: 已评价 0:未评价
		orderMapper.insert(order);
		//插入订单明细
		for (TbOrderItem tbOrderItem : itemList) {
			Long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
			//补全订单明细
			tbOrderItem.setId(orderDetailId+"");
			tbOrderItem.setOrderId(orderId+"");
			orderItemMapper.insert(tbOrderItem);//插入订单明细
		}
		//插入物流表
		orderShipping.setOrderId(orderId+"");
		orderShipping.setCreated(new Date());
		orderShipping.setUpdated(new Date());
		orderShippingMapper.insert(orderShipping);
		return TaotaoResult.ok(orderId);
	}
	

}
