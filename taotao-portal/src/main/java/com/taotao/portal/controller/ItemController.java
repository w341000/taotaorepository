package com.taotao.portal.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;

@Controller
//商品controller
public class ItemController {
	@Resource
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	public String getItemInfo(@PathVariable Long itemId,Model model){
		ItemInfo item = itemService.getItemById(itemId);
		model.addAttribute("item", item);
		return "item";
	}
	
	@RequestMapping(value="/item/desc/{itemId}",produces="text/html;charset=utf-8")
	@ResponseBody
	public String getItemDesc(@PathVariable Long itemId,Model model){
		String desc = itemService.getItemDescById(itemId);
		return desc;
	}
	
	@RequestMapping(value="/item/param/{itemId}",produces="text/html;charset=utf-8")
	@ResponseBody
	public String getItemParam(@PathVariable Long itemId,Model model){
		String desc = itemService.getItemParamById(itemId);
		return desc;
	}

}
