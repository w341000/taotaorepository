package com.taotao.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemCatService;

public class TestPageHelper {
	@Test
	public void testPage(){
		//创建一个spring容器
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
		TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
		//执行查询 并分页
		TbItemExample example=new TbItemExample();
		example.createCriteria().andStatusEqualTo((byte) 1);
		PageHelper.startPage(1, 10);
		List<TbItem> list = itemMapper.selectByExample(example);
		for (TbItem tbItem : list) {
			System.out.println(tbItem.getTitle());
		}
		//取分页信息
		PageInfo<TbItem> pageInfo=new PageInfo<TbItem>(list);
		System.out.println("共有商品:"+pageInfo.getTotal());
		
	}
	
	@Test
	public void testItemCat(){
		//创建一个spring容器
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
		ItemCatService itemCatService = applicationContext.getBean(ItemCatService.class);
		List<EUTreeNode> list = itemCatService.getItemCatList(2);
		
		System.out.println(list);
		
	}

}
