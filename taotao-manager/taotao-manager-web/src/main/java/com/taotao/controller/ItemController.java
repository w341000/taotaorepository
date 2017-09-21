package com.taotao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
public class ItemController {
	@Resource
	private ItemService itemService;
	
	@RequestMapping(value="/item/{itemId}",method=RequestMethod.GET)
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId){
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	//分页查询商品列表
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDateGridResult list(Integer page,Integer rows){
		EUDateGridResult euDateGridResult = itemService.getItemList(page, rows);
		return euDateGridResult;
	}
	
	//保存商品信息
		@RequestMapping(value="/item/save",method=RequestMethod.POST)
		@ResponseBody
		public TaotaoResult save(TbItem item){
			TaotaoResult result = itemService.createItem(item);
			return result;
		}
}
