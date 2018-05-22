package com.dyCustomerService.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dyCustomerService.entity.Student;
import com.dyCustomerService.service.StudentI;


@Controller
public class LoginController {
	@Autowired
	private StudentI  studentI;

	@RequestMapping(value = "/getLogin")
	public void getLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {

		PrintWriter out;
		out = response.getWriter();
		out.flush();
		out.close();

	}

	@RequestMapping("/queryStudentById")
	@ResponseBody //加上这个注解返回json
	public void getBusinessAllianceIndex(HttpServletRequest request,int id,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8"); // 设置编码格式
		response.setContentType("text/html"); // 设置数据格式
		PrintWriter out;
		out = response.getWriter();
		//查询学生信息
		Student stu=studentI.queryByDataById(id);

		
		out.print(JSON.toJSON(stu));//将stu 转换成json 并打印
		out.flush();
		out.close();
	}
	
	
	@RequestMapping("/showIndex")
	@ResponseBody //加上这个注解返回json
	public String showIndex(HttpServletRequest request,HttpServletResponse response) throws IOException {
		return "login";
	}
	
	@RequestMapping("/testHtmlviews")
	public String testHtmlviews(HttpServletRequest request) {
		request.setAttribute("TK","耍耍");
		return "views";
	}
}
