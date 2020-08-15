package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class Calc extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();

		String x_ = req.getParameter("x");
		String y_ = req.getParameter("y");
		String op = req.getParameter("operator");

		int x = 0;
		int y = 0;
		try {
			if (!x_.equals(""))
				x = Integer.parseInt(req.getParameter("x"));
			if (!y_.equals(""))
				y = Integer.parseInt(req.getParameter("y"));

		} catch (NumberFormatException e) {
			out.println("숫자를 입력하세요");
			return;
		}
		if (op.equals("덧셈")) {
			out.println("덧셈 결과 : " + (x + y) + "<br >");
		}
		else {
			out.println("뺼셈 결과 : " + (x - y) + "<br >");
			
		}

	}
}
