package com.taotao.portal.service;

import com.taotao.pojo.TbUser;

public interface UserService {
	/**
	 * 根据token从登录系统获取用户信息
	 * @param token
	 * @return
	 */
	public TbUser getUserByToken(String token);

}
