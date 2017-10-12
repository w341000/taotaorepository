package com.taotao.rest.redis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	
	@Test
	public void testSingle(){
		//创建jedis对象
		Jedis jedis=new Jedis("192.168.1.106", 6379);
		System.out.println(jedis.ping());
		for(int i=0;i<=100;i++){
			jedis.del("k123"+i);
		}
		System.out.println(jedis.get("k1230"));
		jedis.close();
	}
	/**
	 * 使用连接池
	 */
	@Test
	public void testSingleWithPool(){
		//创建jedis池对象
		JedisPool pool=new JedisPool("192.168.1.106", 6379);
		Jedis jedis = pool.getResource();
		System.out.println(jedis.ping());
		//释放jedis链接
		jedis.close();
		pool.close();
	}
	/**
	 * 集群测试
	 */
	@Test
	public void testCluster(){
		
		try {
//			Set<HostAndPort> nodes=new HashSet<HostAndPort>();
//			nodes.add(new HostAndPort("192.168.1.106", 7001));
//			nodes.add(new HostAndPort("192.168.1.106", 7002));
//			nodes.add(new HostAndPort("192.168.1.106", 7003));
//		nodes.add(new HostAndPort("192.168.1.106", 7004));
//		nodes.add(new HostAndPort("192.168.1.106", 7005));
//		nodes.add(new HostAndPort("192.168.1.106", 7006));
//			//创建对象
//			JedisCluster jedisCluster=new JedisCluster(nodes);
			ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:applicationContext-redis.xml");
			JedisCluster jedisCluster=(JedisCluster) applicationContext.getBean("redisClient");
			jedisCluster.set("k12333", "v1233");
			System.out.println(jedisCluster.get("k12333"));
			jedisCluster.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
