package com.taotao.rest.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.service.RedisService;

@Controller
@RequestMapping("/cache/sync")
public class RedisController {
	
	@Resource
	private RedisService redisService;
	
	@RequestMapping("/content/{contentCid}")
	@ResponseBody
	//对缓存进行同步
	public TaotaoResult s(@PathVariable Long contentCid){
		TaotaoResult result = redisService.syncContent(contentCid);
		return result;
	}

}
