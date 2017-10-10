package com.taotao.portal.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.portal.service.ContentService;

@Controller
public class IndexController {
	@Resource
	private ContentService contentService;
	@RequestMapping("/index")
	public String showIndex(Map map){
		String adJson = contentService.getContentList();
		map.put("ad1", adJson);
		return "index";
	}
}
