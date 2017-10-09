package com.taotao.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;
/**
 * ƒ⁄»›π‹¿Ìcontroller
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	@Resource
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getCateList(@RequestParam(value="id",defaultValue="0") Long parentid){
		List<EUTreeNode> list = contentCategoryService.getCategoryList(parentid);
		return list;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult createContentCategory(Long parentId,String name){
		TaotaoResult taotaoResult = contentCategoryService.insertContentCategory(parentId, name);
		return taotaoResult;
	}

}
