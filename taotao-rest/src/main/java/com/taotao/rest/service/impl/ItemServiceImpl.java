package com.taotao.rest.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemService;
@Service
//商品信息管理
public class ItemServiceImpl implements ItemService {

	@Resource
	private TbItemMapper itemMapper;
	@Resource
	private JedisClient jedisClient;
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;//redis中保存的key
	@Value("${REDIS_ITEM_EXPIRE}")
	private Integer REDIS_ITEM_EXPIRE;//过期时间
	
	@Override
	public TaotaoResult getItemBaseInfo(Long itemid) {
		try {
			//添加缓存逻辑
			//从缓存中取商品信息
			String json = jedisClient.get(REDIS_ITEM_KEY+":"+itemid+":base");
			if(StringUtils.isNotBlank(json)){
				TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
				return TaotaoResult.ok(tbItem);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//缓存中没有,从数据库查询商品信息
		TbItem item = itemMapper.selectByPrimaryKey(itemid);
		
		try {
			//把商品信息写入缓存
			jedisClient.set(REDIS_ITEM_KEY+":"+itemid+":base", JsonUtils.objectToJson(item));
			//设置key有效期
			jedisClient.expire(REDIS_ITEM_KEY+":"+itemid+":base", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok(item);
	}

}
