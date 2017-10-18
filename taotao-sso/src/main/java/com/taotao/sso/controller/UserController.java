package com.taotao.sso.controller;

import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	
	//数据校验,用户名,密码和email
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkDate(@PathVariable String param, @PathVariable Integer type,String callback){
		if(StringUtils.isBlank(param)){
			return TaotaoResult.build(400, "内容不能为空");
		}
		if(type==null){
			return TaotaoResult.build(400, "类型不能为空");
		}
		if(type!=1 && type !=2 && type !=3){
			return TaotaoResult.build(400, "类型不能错误");
		}
		//调用服务
		TaotaoResult result = userService.checkData(param, type);
		
		if(StringUtils.isNotBlank(callback)){//jsonp支持
			MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
		return result;
	}
	//用户注册
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult register(TbUser user){
		try {
			TaotaoResult result = userService.saveUser(user);
			return result;
		} catch (Exception e) {
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	//用户登录
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult login(String username,String password){
		try {
			TaotaoResult result = userService.userLogin(username, password);
			return result;
		} catch (Exception e) {
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	//通过token查询用户信息
	@RequestMapping(value="/token/{token}")
	@ResponseBody
	public Object token(@PathVariable String token,String callback){
		try {
			TaotaoResult result = userService.getUserBytoken(token);
			
			if(StringUtils.isNotBlank(callback)){//jsonp支持
				MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			}
			return result;
		} catch (Exception e) {
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	/*
	//用户登出
		@RequestMapping(value="/logout/{token}",method=RequestMethod.POST)
		@ResponseBody
		public TaotaoResult login(@PathVariable String token,String callback){
			try {
				TaotaoResult result = userService.userLogout(token);
				return result;
			} catch (Exception e) {
				return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
			}
		}
		*/
}
