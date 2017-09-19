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
		//����һ��spring����
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
		TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
		//ִ�в�ѯ ����ҳ
		TbItemExample example=new TbItemExample();
		example.createCriteria().andStatusEqualTo((byte) 1);
		PageHelper.startPage(1, 10);
		List<TbItem> list = itemMapper.selectByExample(example);
		for (TbItem tbItem : list) {
			System.out.println(tbItem.getTitle());
		}
		//ȡ��ҳ��Ϣ
		PageInfo<TbItem> pageInfo=new PageInfo<TbItem>(list);
		System.out.println("������Ʒ:"+pageInfo.getTotal());
		
	}
	
	@Test
	public void testItemCat(){
		//����һ��spring����
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
		ItemCatService itemCatService = applicationContext.getBean(ItemCatService.class);
		List<EUTreeNode> list = itemCatService.getItemCatList(2);
		
		System.out.println(list);
		
	}

}
