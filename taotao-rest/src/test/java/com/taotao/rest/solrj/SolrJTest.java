package com.taotao.rest.solrj;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrJTest {
	
	@Test
	public void addDocument() throws SolrServerException, IOException{
		//创建链接
		SolrServer solrServer=new HttpSolrServer("http://192.168.1.106:8080/solr");
		//创建文档对象
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "001");
		document.addField("item_title", "这是标题");
		document.addField("item_price", 1024);
		//写入索引库
		solrServer.add(document);
		//提交
		solrServer.commit();
	}
	@Test
	public void deleteDocument() throws SolrServerException, IOException{
		SolrServer solrServer=new HttpSolrServer("http://192.168.1.106:8080/solr");
//		solrServer.deleteById("001");
		solrServer.deleteByQuery("*:*");
		solrServer.commit();
		
	}

}
