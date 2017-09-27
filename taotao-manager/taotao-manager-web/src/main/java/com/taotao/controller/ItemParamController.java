package com.taotao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	@Resource
	private ItemParamService itemParamService;
	//分页查询规格信息
	@RequestMapping("/list")
	@ResponseBody
	public EUDateGridResult list(Integer page,Integer rows){
		EUDateGridResult euDateGridResult = itemParamService.getItemParamList(page, rows);
		return euDateGridResult;
	}
	//根据类目id查询是否有规格信息
	@RequestMapping("/query/itemcatid/{itemcatid}")
	@ResponseBody
	public TaotaoResult getItemParamByCid(@PathVariable Long itemcatid){
		TaotaoResult taotaoResult = itemParamService.getItemParamByCid(itemcatid);
		return taotaoResult;
	}
	//保存规格参数模板
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TaotaoResult save(@PathVariable Long cid,TbItemParam itemParam){
		itemParam.setItemCatId(cid);
		TaotaoResult taotaoResult = itemParamService.insertItemParam(itemParam);
		return taotaoResult;
	}
}
