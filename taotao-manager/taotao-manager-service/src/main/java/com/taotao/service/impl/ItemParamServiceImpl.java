package com.taotao.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.pojo.TaotaoResult;
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

	@Override
	public TaotaoResult getItemParamByCid(Long cid) {
		TbItemParamExample example=new TbItemParamExample();
		//类目id查询
		example.createCriteria().andItemCatIdEqualTo(cid);
		List<TbItemParam> list =itemParamMapper.selectByExampleWithBLOBs(example);
		if(list!=null &&list.size()>0){
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		//插入规格信息表
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}

}
