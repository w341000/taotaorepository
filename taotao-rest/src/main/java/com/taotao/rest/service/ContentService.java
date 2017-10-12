package com.taotao.rest.service;

import java.util.List;

import com.taotao.pojo.TbContent;

public interface ContentService {
	/**
	 * @param contentCid 内容分类id
	 * @return
	 */
	List<TbContent> getContentList(Long contentCid);
}
