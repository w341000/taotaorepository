package com.taotao.portal.service;
import com.taotao.portal.pojo.SearchResult;

public interface SearchService {
	
	public SearchResult search(String q,Integer page,Integer rows);

}
