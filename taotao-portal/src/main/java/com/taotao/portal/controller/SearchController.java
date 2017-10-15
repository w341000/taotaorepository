package com.taotao.portal.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;

@Controller
//搜索Controller
public class SearchController {
	
	@Resource
	private SearchService searchService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/search")
	public String search(String q,@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="60") Integer rows,Model model){
		SearchResult result = searchService.search(q, page, rows);
		model.addAttribute("itemList",result.getItemList() );
		model.addAttribute("page", result.getCurPage());
		model.addAttribute("totalPages", result.getPageCount());
		model.addAttribute("query",q );
		return "search";
	}

}
