package com.taotao.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;

@Service //商品管理service
public class ItemServiceImpl implements ItemService{

	@Resource
	private TbItemMapper itemMapper;
	@Override
	public TbItem getItemById(Long id){
		TbItem item = itemMapper.selectByPrimaryKey(id);
		return item;
	}
	@Override
	public EUDateGridResult getItemList(int page, int rows) {
		TbItemExample example=new TbItemExample();
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		EUDateGridResult euDateGridResult=new EUDateGridResult();
		euDateGridResult.setRows(list);
		//取记录总条数
		PageInfo<TbItem> pageInfo=new PageInfo<TbItem>(list);
		euDateGridResult.setTotal(pageInfo.getTotal());
		return euDateGridResult;
	}
	
	
}
