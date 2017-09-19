package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;
@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Resource
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<EUTreeNode> getItemCatList(long pid) {
		//查询出所有商品类目
		TbItemCatExample example=new TbItemCatExample();
		example.createCriteria().andParentIdEqualTo(pid);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List<EUTreeNode> treeNodes=new ArrayList<EUTreeNode>();
		//将商品类目信息封装到easyui的树形控件属性中
		for (TbItemCat tbItemCat : list) {
			EUTreeNode treeNode=new EUTreeNode();
			treeNode.setId(tbItemCat.getId());
			treeNode.setState(tbItemCat.getIsParent()?"closed":"open");
			treeNode.setText(tbItemCat.getName());
			treeNodes.add(treeNode);
		}
		return treeNodes;
	}

}
