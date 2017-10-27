package com.taotao.search.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrCloudTest {
	@Test
	public void testAddDocument() throws SolrServerException, IOException{
		//创建一个连接,zookeeper地址列表使用逗号分隔
		String zkHost="192.168.1.106:2181,192.168.1.106:2182,192.168.1.106:2183";
		CloudSolrServer solrServer=new CloudSolrServer(zkHost);
		//设置默认colection
		solrServer.setDefaultCollection("collection2");
		//创建文档对象
		SolrInputDocument document=new SolrInputDocument();
		//文档中添加field
		document.addField("id", "test001");
		document.addField("item_title", "商品");
		//把文档添加到索引库
		solrServer.add(document);
		solrServer.commit();
	}

}
