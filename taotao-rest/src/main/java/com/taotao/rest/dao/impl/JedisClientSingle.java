package com.taotao.rest.dao.impl;

import javax.annotation.Resource;
import com.taotao.rest.dao.JedisClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

//对jedis的操作封装一层
public class JedisClientSingle implements JedisClient {
	@Resource
	private JedisPool jedisPool;

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get(key);
		jedis.close();
		return string;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.set(key, value);
		jedis.close();
		return string;
	}

	@Override
	public Long hset(String key, String field, String value) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hset(key, field, value);
		jedis.close();
		return result;
	}

	@Override
	public String hget(String key, String field) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.hget(key, field);
		jedis.close();
		return string;
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long incr = jedis.incr(key);
		jedis.close();
		return incr;
	}

	@Override
	public Long expire(String key, int seconds) {
		Jedis jedis = jedisPool.getResource();
		Long result  = jedis.expire(key, seconds);
		jedis.close();
		return result;
	}

	@Override
	public Long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result  = jedis.ttl(key);
		jedis.close();
		return result;
	}

	@Override
	public Long del(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result  = jedis.del(key);
		jedis.close();
		return result;
	}

	@Override
	public Long hdel(String key, String... fields) {
		Jedis jedis = jedisPool.getResource();
		Long result  = jedis.hdel(key, fields);
		jedis.close();
		return result;
	}
	
	

}
