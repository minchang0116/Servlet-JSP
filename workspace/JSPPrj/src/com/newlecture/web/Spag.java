package com.newlecture.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/spag")
public class Spag extends HttpServlet {
	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {
		int num = 0;
		String num_ = request.getParameter("n");
		if (num_ != null && !num_.equals(""))
			num = Integer.parseInt(num_);

		String result;

		if (num % 2 != 0)
			result = "홀수";
		else
			result = "짝수";
		
		request.setAttribute("result", result);
		
		String[] names = {"newlec", "dragon"};
		request.setAttribute("names", names);
		
		Map<String, Object> notice = new HashMap<String, Object>();
		notice.put("id", 1);
		notice.put("title", "EL은 좋아요");
		request.setAttribute("notice", notice);
		
		// redirect : 현재와 상관없이 새로운 요청
		// forward : 현재 작업한 내용을 이어갈 수 있도록 공유
		RequestDispatcher dispatcher = request.getRequestDispatcher("spag.jsp");
		dispatcher.forward(request, response);
	};
}
