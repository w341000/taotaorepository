package com.taotao.rest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.rest.service.ContentService;
@Service//内容管理service
public class ContentServiceImpl implements ContentService {
	@Resource
	private TbContentMapper contentMapper;

	@Override
	public List<TbContent> getContentList(Long contentCid) {
		//根据内容分类id查询内容列表
		TbContentExample example=new TbContentExample();
		example.createCriteria().andCategoryIdEqualTo(contentCid);
		//执行查询
		List<TbContent> list = contentMapper.selectByExample(example);
		return list;
	}

}
