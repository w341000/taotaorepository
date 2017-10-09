package com.taotao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

@Controller//���ݹ���controller
@RequestMapping("/content")
public class ContentController {

	@Resource
	private ContentService contentService;
	
	@RequestMapping("/save")
	@ResponseBody
	//��������
	public TaotaoResult save(TbContent content){
		TaotaoResult result = contentService.insertContent(content);
		return result;
	}
}
