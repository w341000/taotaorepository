package com.taotao.search.service.impl;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.stereotype.Service;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {
	@Resource
	private SearchDao searchDao;

	@Override
	public SearchResult search(String q, int page, int rows) throws Exception {
			SolrQuery query = new SolrQuery();
			// 设置查询条件
			query.setQuery(q);
			query.setStart((page - 1) * rows).setRows(rows);// 分页
			query.setHighlight(true).addHighlightField("item_title")
					.setHighlightSimplePre("<em style='color:red' >")
					.setHighlightSimplePost("</em>");// 高亮
			query.set("df", "item_keywords");// 默认搜索域
			SearchResult result = searchDao.search(query);
			//计算总页数
			long count = result.getRecordCount();
			long pageCount=count/rows;
			if(count % rows >0){
				pageCount++;
			}
			result.setPageCount(pageCount);
			result.setCurPage(page);
		return result;
	}

}
