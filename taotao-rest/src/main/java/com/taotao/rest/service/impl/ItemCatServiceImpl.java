package com.taotao.rest.service.impl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Resource
	private TbItemCatMapper itemCatMapper;


	@Override
	public CatResult getItemCatList() {
		CatResult catResult=new CatResult();
		//查询分类列表
		catResult.setData(getCatList( (long) 0));
		
		return catResult;
	}

	/**
	 * 查询分类列表
	 * @param pid 父分类id
	 */
	private List<?> getCatList(Long pid){
		TbItemCatExample example=new TbItemCatExample();
		example.createCriteria().andParentIdEqualTo(pid);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//返回的list
		List resultList=new ArrayList<CatNode>();
		//向list添加节点
		int count=0;
		for (TbItemCat tbItemCat : list) {
			//判断是否为父节点
			if(tbItemCat.getIsParent()){
				CatNode catNode=new CatNode();
				if(tbItemCat.getParentId()==0){//当分类是顶级分类时才加a标签信息
					catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				}else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/"+tbItemCat.getId()+".html");
				catNode.setItem(getCatList(tbItemCat.getId()));
				
				resultList.add(catNode);
				count++;
				if(count>=14){
					break;
				}
			}else{//叶子节点
				resultList.add("/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName());
			}
		}
		return resultList;
	}
}
