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
	public TaotaoResult getItemInfo(@PathVariable Long itemid){
		TaotaoResult taotaoResult = itemService.getItemBaseInfo(itemid);
		return taotaoResult;
	}

}
