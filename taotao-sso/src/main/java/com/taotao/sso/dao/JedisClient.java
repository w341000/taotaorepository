package com.taotao.sso.dao;
/**
 * 对jedis的操作进行一层封装
 */
public interface JedisClient {
	
	String get(String key);
	String set(String key,String value);
	
	Long hset(String key,String field,String value);
	
	String hget(String key,String field);

	Long incr(String key);
	/**
	 * 设置过期时间
	 * @param key
	 * @param seconds
	 * @return
	 */
	Long expire(String key,int  seconds);
	
	Long ttl(String key);
	
	Long del(String key);
	Long hdel(String key,String... fields);
}
