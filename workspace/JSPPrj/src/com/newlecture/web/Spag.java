package com.newlecture.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/spag")
public class Spag extends HttpServlet {
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		int num = 0;
		String num_ = req.getParameter("n");
		if (num_ != null && !num_.equals(""))
			num = Integer.parseInt(num_);

		String result;

		if (num % 2 != 0)
			result = "홀수";
		else
			result = "짝수";
		
		req.setAttribute("result", result);
		
		// redirect : 현재와 상관없이 새로운 요청
		// forward : 현재 작업한 내용을 이어갈 수 있도록 공유
		RequestDispatcher dispatcher = req.getRequestDispatcher("spag.jsp");
		dispatcher.forward(req, resp);
	};
}
