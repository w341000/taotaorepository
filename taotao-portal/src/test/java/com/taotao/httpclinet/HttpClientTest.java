package com.taotao.httpclinet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {
	@Test
	public void doGet() throws ClientProtocolException, IOException{
		//创建httpclinet对象
		CloseableHttpClient httpClient=HttpClients.createDefault();
		//创建get对象
		HttpGet get=new HttpGet("http://www.sogou.com");
		//执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		//获取响应结果
		int code = response.getStatusLine().getStatusCode();
		System.out.println(code);
		HttpEntity entity = response.getEntity();
		System.out.println(EntityUtils.toString(entity,"utf-8"));
		//关闭httpclient
		response.close();
		httpClient.close();
	}
	@Test
	public void doGetWithParam() throws Exception{
		//创建httpclinet对象
		CloseableHttpClient httpClient=HttpClients.createDefault();
		//创建get对象
		URIBuilder uriBuilder=new URIBuilder("http://www.sogou.com/web");
		uriBuilder.addParameter("query", "花千骨");
		HttpGet get=new HttpGet(uriBuilder.build());
		//执行请求
				CloseableHttpResponse response = httpClient.execute(get);
				//获取响应结果
				int code = response.getStatusLine().getStatusCode();
				System.out.println(code);
				HttpEntity entity = response.getEntity();
				System.out.println(EntityUtils.toString(entity,"utf-8"));
				//关闭httpclient
				response.close();
				httpClient.close();
	}
	@Test
	public void doPost() throws Exception{
		//创建httpclinet对象
		CloseableHttpClient httpClient=HttpClients.createDefault();
		//创建post对象
		URIBuilder uriBuilder=new URIBuilder("http://www.baidu.com");
		HttpPost post=new HttpPost(uriBuilder.build());
		CloseableHttpResponse response = httpClient.execute(post);
		//获取响应结果
		int code = response.getStatusLine().getStatusCode();
		System.out.println(code);
		HttpEntity entity = response.getEntity();
		System.out.println(EntityUtils.toString(entity,"utf-8"));
		//关闭httpclient
		response.close();
		httpClient.close();
		
	}
	
	@Test
	public void doPostWithParam() throws Exception{
		//创建httpclinet对象
		CloseableHttpClient httpClient=HttpClients.createDefault();
		//创建post对象
		URIBuilder uriBuilder=new URIBuilder("http://www.baidu.com");
		HttpPost post=new HttpPost(uriBuilder.build());
		List<NameValuePair> kvlist=new ArrayList<NameValuePair>();
		kvlist.add(new BasicNameValuePair("username", "zhangsan"));
		//包装成entity
		StringEntity sentity=new UrlEncodedFormEntity(kvlist);
		//设置请求内容
		post.setEntity(sentity);
		CloseableHttpResponse response = httpClient.execute(post);
		//获取响应结果
		int code = response.getStatusLine().getStatusCode();
		System.out.println(code);
		HttpEntity entity = response.getEntity();
		System.out.println(EntityUtils.toString(entity,"utf-8"));
		//关闭httpclient
		response.close();
		httpClient.close();
		
	}

}
