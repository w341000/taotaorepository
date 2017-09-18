package com.taotao.common.pojo;

import java.util.List;
/**
 * easy ui datagrid 数据传递对象
 *
 */
public class EUDateGridResult {
	private Long total;
	private List<?> rows;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
