package com.taotao.rest.solrj;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
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
//	<field name="item_title" type="text_ik" indexed="true" stored="true"/>
//	<field name="item_sell_point" type="text_ik" indexed="true" stored="true"/>
//	<field name="item_price"  type="long" indexed="true" stored="true"/>
//	<field name="item_image" type="string" indexed="false" stored="true" />
//	<field name="item_category_name" type="string" indexed="true" stored="true" />
//	<field name="item_desc" type="text_ik" indexed="true" stored="false" />
	@Test
	public void queryDocument() throws SolrServerException, IOException{
		SolrServer solrServer=new HttpSolrServer("http://192.168.1.106:8080/solr");
		//创建查询对象
		SolrQuery solrQuery=new SolrQuery();
		solrQuery.setQuery("*:*");
		//执行查询
		QueryResponse response = solrServer.query(solrQuery);
		//取查询结果
		SolrDocumentList solrDocumentList = response.getResults();
		System.out.println(solrDocumentList.getNumFound());
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_sell_point"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));
		}
	}

}
