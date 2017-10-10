package com.taotao.rest.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.pojo.TbContent;
import com.taotao.rest.service.ContentService;

@Controller
@RequestMapping("/content")
//内容管理controller
public class ContentController {
	@Resource
	private ContentService contentService;
	
	@RequestMapping("/list/{contentCId}")
	@ResponseBody//获得内容列表信息
	public TaotaoResult getContentList(@PathVariable Long contentCId){
		try{
		List<TbContent> list = contentService.getContentList(contentCId);
		return TaotaoResult.ok(list);
		}catch (Exception e) {
			e.printStackTrace();
			return 	TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

}
