package com.taotao.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.dao.JedisClient;
import com.taotao.sso.service.UserService;
@Service
//用户
public class UserServiceImpl implements UserService {
	@Resource
	private TbUserMapper userMapper;
	@Resource
	private JedisClient jedisClient;
	
	@Value("${REDIS_USER_SESSION_KEY}")
	private  String REDIS_USER_SESSION_KEY;
	
	@Value("${REDIS_SESSION_EXPIRE}")
	private Integer REDIS_SESSION_EXPIRE;
	


	@Override
	public TaotaoResult checkData(String content, Integer type) {
		TbUserExample example=new TbUserExample();
		Criteria criteria = example.createCriteria();//查询条件
		//对数据进行校验,可选参数1、2、3分别代表username、phone、email
		if(1==type){//用户名校验
			criteria.andUsernameEqualTo(content);
		}else if(2==type){//电话校验
			criteria.andPhoneEqualTo(content);
		}else{//邮箱校验
			criteria.andEmailEqualTo(content);
		}
		List<TbUser> list = userMapper.selectByExample(example);
		//返回数据，true：数据可用，false：数据不可用
		if(list==null || list.size()==0){
			return  TaotaoResult.ok(true);
		}
		return  TaotaoResult.ok(false);
	}

	@Override
	public TaotaoResult saveUser(TbUser user) {
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));//md5对密码加密
		user.setCreated(new Date());
		user.setUpdated(new Date());
		userMapper.insert(user);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult userLogin(String username, String password,
			HttpServletRequest request, HttpServletResponse response){
		TbUserExample example=new TbUserExample();
		example.createCriteria().andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);
		//如果用戶不存在
		if(list==null ||list.size()==0){
			return TaotaoResult.build(400, "用戶名或密码错误");
		}
		TbUser user = list.get(0);
		//比对密码
		String md5Pw=DigestUtils.md5DigestAsHex(password.getBytes());
		if(!md5Pw.equals(user.getPassword())){
			return TaotaoResult.build(400, "用戶名或密码错误");
		}
		//生成token
		String token=UUID.randomUUID().toString();
		//保存用户之前,将用户中的密码清空
		user.setPassword(null);
		//把用户信息写入redis
		jedisClient.set(REDIS_USER_SESSION_KEY+":"+token, JsonUtils.objectToJson(user));
		jedisClient.expire(REDIS_USER_SESSION_KEY+":"+token, REDIS_SESSION_EXPIRE);//设置过期时间
		
		//添加cookie逻辑
		CookieUtils.setCookie(request, response, "TT_TOKEN", token);
		return TaotaoResult.ok(token);
	}

	@Override
	public TaotaoResult getUserBytoken(String token) {
		//根据token从redis查询用户信息
		String json = jedisClient.get(REDIS_USER_SESSION_KEY+":"+token);
		//判断是否为空
		if(StringUtils.isBlank(json)){
			//用户信息过期或不存在
			return TaotaoResult.build(400, "请重新登录");
		}
		//更新redis过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY+":"+token, REDIS_SESSION_EXPIRE);//设置过期时间
		return TaotaoResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
	}

	

}
