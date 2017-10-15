package com.taotao.portal.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
@Service
public class SearchServiceImpl implements SearchService {
	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;

	@Override
	public SearchResult search(String q, Integer page, Integer rows) {
		Map<String, String> param=new HashMap<String, String>();//搜索参数
		param.put("q", q);//查询条件
		param.put("page", page+"");
		param.put("rows", rows+"");
		//调用taotao-search
		String json=HttpClientUtil.doGet(SEARCH_BASE_URL, param);
		try {
			TaotaoResult taotaoResult=TaotaoResult.formatToPojo(json, SearchResult.class);
			if(taotaoResult.getStatus()==200){
				SearchResult searchResult=(SearchResult) taotaoResult.getData();
				return searchResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
