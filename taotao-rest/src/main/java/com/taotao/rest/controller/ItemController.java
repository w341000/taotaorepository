package com.taotao.rest.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.service.ItemService;

@Controller
@RequestMapping("/item")
//商品controller
public class ItemController {
	@Resource
	private ItemService itemService;
	
	@RequestMapping("/info/{itemid}")
	@ResponseBody
	//获取商品信息
	public TaotaoResult getItemInfo(@PathVariable Long itemid){
		TaotaoResult taotaoResult = itemService.getItemBaseInfo(itemid);
		return taotaoResult;
	}
	
	@RequestMapping("/desc/{itemid}")
	@ResponseBody
	//获取商品描述信息
	public TaotaoResult getItemDesc(@PathVariable Long itemid){
		TaotaoResult taotaoResult = itemService.getItemDesc(itemid);
		return taotaoResult;
	}
	
	@RequestMapping("/param/{itemid}")
	@ResponseBody
	//获取商品规格信息
	public TaotaoResult getItemParam(@PathVariable Long itemid){
		TaotaoResult taotaoResult = itemService.getItemParam(itemid);
		return taotaoResult;
	}

}
