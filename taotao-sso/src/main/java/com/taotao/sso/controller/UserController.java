package com.taotao.sso.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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
	/**
	 * 展示注册页面
	 * @return
	 */
	@RequestMapping("/showRegister")
	public String showRegister(){
		return "register";
	}
	/**
	 * 展示登录页面
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/showLogin")
	public String showLogin(String redirect,Model model) throws UnsupportedEncodingException{
		model.addAttribute("redirect", URLDecoder.decode(redirect, "UTF-8"));
		return "login";
	}
	
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
	public TaotaoResult login(String username,String password,HttpServletRequest request,HttpServletResponse response){
		try {
			TaotaoResult result = userService.userLogin(username, password,request,response);
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
	
	
	//用户登出
		@RequestMapping(value="/logout")
		public String logout(@CookieValue(required=true,value="TT_TOKEN") String token,HttpServletRequest request, HttpServletResponse response){
				userService.userLogout(token, request, response);
				return "redirect:http://www.taotao.com/index.html";
		}
}
