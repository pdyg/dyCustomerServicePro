package com.dyCustomerService.dao.basedao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@SuppressWarnings("hiding")
public interface BaseDao<T> extends Serializable {
	/**
	 * 添加
	 * @param t
	 * @return
	 */
	public int addData(T t);
	/**
	 * 修改
	 * @param t
	 * @return
	 */
	public int updateData(T t);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int deleteData(Integer id);
	/**
	 * 根据id查询返回一个对象
	 * @param id
	 * @return
	 */
	public   <T> T   queryByDataById(Integer id);
	/**
	 * 查询分页
	 * @param map-- paginationMap
	 * @return
	 */
	public <T> List<T> queryByDataByPage(Map paginationMap);
}
