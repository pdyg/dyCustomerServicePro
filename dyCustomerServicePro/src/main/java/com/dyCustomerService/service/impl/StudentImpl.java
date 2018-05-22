package com.dyCustomerService.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dyCustomerService.dao.StudentMapper;
import com.dyCustomerService.service.StudentI;
@Service
public class StudentImpl implements StudentI{
	@Autowired
	private StudentMapper studentMapper;
	@Override
	public int addData(StudentMapper t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateData(StudentMapper t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteData(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> T queryByDataById(Integer id) {
		// TODO Auto-generated method stub
		return studentMapper.queryByDataById(id);
	}

	@Override
	public <T> List<T> queryByDataByPage(Map paginationMap) {
		// TODO Auto-generated method stub
		return null;
	}

}
