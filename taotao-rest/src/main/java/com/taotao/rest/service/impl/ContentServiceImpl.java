package com.taotao.rest.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ContentService;
@Service//内容管理service
public class ContentServiceImpl implements ContentService {
	@Resource
	private TbContentMapper contentMapper;
	@Resource
	private JedisClient jedisClient;
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;

	@Override
	public List<TbContent> getContentList(Long contentCid) {
		//首先从缓存中查询内容
		try {
			String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCid+"");
			if(!StringUtils.isBlank(result)){
				List<TbContent> list = JsonUtils.jsonToList(result, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//根据内容分类id查询内容列表
		TbContentExample example=new TbContentExample();
		example.createCriteria().andCategoryIdEqualTo(contentCid);
		//执行查询
		List<TbContent> list = contentMapper.selectByExample(example);
		
		//向redis缓存添加内容
		try {
			if(list!=null && !list.isEmpty()){
				//把list转换成字符串json
				String jsonList=JsonUtils.objectToJson(list);
				jedisClient.hset(INDEX_CONTENT_REDIS_KEY, contentCid+"", jsonList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
