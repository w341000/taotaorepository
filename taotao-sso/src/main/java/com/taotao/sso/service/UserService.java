package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface UserService {
	
	TaotaoResult checkData(String content,Integer type);

	TaotaoResult saveUser(TbUser user);
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return TaotaoResult 包装了token信息
	 */
	TaotaoResult userLogin(String username,String password);
	/**
	 * 通过token查询用户信息
	 * @param token
	 * @return
	 */
	TaotaoResult getUserBytoken(String token);

}
