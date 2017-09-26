package com.taotao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.service.ItemParamService;

@Controller
public class ItemParamController {
	@Resource
	private ItemParamService itemParamService;
	
	@RequestMapping("/item/param/list")
	@ResponseBody
	public EUDateGridResult list(Integer page,Integer rows){
		EUDateGridResult euDateGridResult = itemParamService.getItemParamList(page, rows);
		return euDateGridResult;
	}
}
