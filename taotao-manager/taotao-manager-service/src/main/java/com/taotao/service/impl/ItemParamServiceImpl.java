package com.taotao.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.service.ItemParamService;
/**
 * 商品规格模板service
 *
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
	@Resource
	private TbItemParamMapper itemParamMapper;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public EUDateGridResult getItemParamList(Integer page, Integer rows) {
		TbItemParamExample example=new TbItemParamExample();
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		EUDateGridResult euDateGridResult=new EUDateGridResult();
		euDateGridResult.setRows(list);
		//取记录总条数
		PageInfo pageInfo=new PageInfo(list);
		euDateGridResult.setTotal(pageInfo.getTotal());
		return euDateGridResult;
	}

}
