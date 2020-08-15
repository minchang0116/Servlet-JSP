package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add2")
public class Add2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		
		String[] num_ = req.getParameterValues("num");
		int result = 0;
		try {
			for(int i=0; i<num_.length; i++) {
				int num = Integer.parseInt(num_[i]); // for문 안에서 변수 선언하면 한번만 만들어짐
				result += num;
			}
		}catch(NumberFormatException e){
			out.println("숫자를 입력하세요");
			return;
		}
		
		out.println("덧셈 결과 : "+ result +"<br >");
		
	}
}
