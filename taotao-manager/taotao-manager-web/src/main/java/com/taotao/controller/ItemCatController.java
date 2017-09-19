package com.taotao.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.service.ItemCatService;
/**
 * ��Ʒ�������controller
 *
 */
@Controller
public class ItemCatController {
	@Resource
	private ItemCatService itemCatService;
	//�г���Ʒ������Ϣ
	@RequestMapping("/item/cat/list")
	public @ResponseBody List list(@RequestParam(value="id",defaultValue="0") Long parentId){
		List<EUTreeNode> list = itemCatService.getItemCatList(parentId);
		return list;
	}

}
