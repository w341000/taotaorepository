package com.taotao.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Repository;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;
@Repository
//商品搜索dao
public class SearchDaoImpl implements SearchDao {
	@Resource
	private SolrServer solrServer;

	@Override
	public SearchResult search(SolrQuery query) throws Exception {
		//返回值对象
		SearchResult result=new SearchResult();
		//根据查询条件查询索引
		QueryResponse response = solrServer.query(query);
		SolrDocumentList solrDocumentList = response.getResults();
		//取查询结果总数量
		result.setRecordCount(solrDocumentList.getNumFound());
		//取高亮显示
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		//商品列表
		List<Item> itemList=new ArrayList<Item>();
		for (SolrDocument solrDocument : solrDocumentList) {
			//商品对象
			Item item=new Item();
			item.setId((String) solrDocument.get("id"));
			String title="";
			//取高亮title
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			if(list!=null && list.size()>0){
				title=list.get(0);
			}else{
				title=(String) solrDocument.get("item_title");
			}
			item.setTitle(title);
			item.setSell_point((String) solrDocument.get("item_sell_point"));
			item.setPrice((Long) solrDocument.get("item_price"));
			item.setImage((String) solrDocument.get("item_image"));
			item.setCategory_name((String) solrDocument.get("item_category_name"));
			//添加到list
			itemList.add(item);
		}
		result.setItemList(itemList);
		return result;
	}

}
