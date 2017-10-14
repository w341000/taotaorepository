package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;

public interface SearchService {
	/**
	 * 
	 * @param q 查询条件
	 * @param page 页码
	 * @param rows 每页纪录
	 * @return
	 * @throws Exception 
	 */
	SearchResult search(String q,int page,int rows) throws Exception;

}
