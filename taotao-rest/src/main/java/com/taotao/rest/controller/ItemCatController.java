package com.taotao.rest.controller;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.JsonUtils;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
/**
 * 商品分类列表
 */
@Controller
public class ItemCatController {
	@Resource
	private ItemCatService itemCatService;
	
	//springmvc4.1之前对jsonp的使用
//	@RequestMapping(value="/itemcat/all",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
//	@ResponseBody
//	public String getItemCatList(String callback){
//		CatResult catResult = itemCatService.getItemCatList();
//		//pojo转成json
//		String json=JsonUtils.objectToJson(catResult);
//		//拼装返回值
//		String result=callback+"("+json+");";
//		return result;
//	}
	
	
	//springmvc 4.1支持的对jsonp的使用
	@RequestMapping(value="/itemcat/all")
	@ResponseBody
	public Object getItemCatList(String callback){
		CatResult catResult = itemCatService.getItemCatList();
		MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(catResult);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}

}
