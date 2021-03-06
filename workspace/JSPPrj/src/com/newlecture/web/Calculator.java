package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/calculator")
public class Calculator extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
PrintWriter out = resp.getWriter();
		
		Cookie[] cookies = req.getCookies();
		String exp = "0";
		if(cookies != null) {
		//expression
			for (Cookie c : cookies) {
				if (c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}
			}
		
		}
		
		out.write("<!DOCTYPE html>\r\n" + 
				"		<html>\r\n" + 
				"		<head>\r\n" + 
				"		<meta charset=\"UTF-8\">\r\n" + 
				"		<title>Insert title here</title>\r\n" + 
				"		<style>\r\n" + 
				"		input{\r\n" + 
				"			width:50px;\r\n" + 
				"			height:50px;\r\n" + 
				"		}\r\n" + 
				"		.output{\r\n" + 
				"			height: 50px;\r\n" + 
				"			background: #e9e9e9;\r\n" + 
				"			font-size:24px;\r\n" + 
				"			font-weight: bold;\r\n" + 
				"			text-align: right;\r\n" + 
				"			padding: 0px 5px;\r\n" + 
				"		}\r\n" + 
				"		</style>\r\n" + 
				"		</head>\r\n" + 
				"		<body>\r\n" + 
				"			<form method=\"post\">\r\n" + 
				"				<table>	<!--6행 4열 -->\r\n" + 
				"					<tr>\r\n" + 
				"						<td class=\"output\" colspan=\"4\">"+exp+"</td>\r\n" + 
				"					</tr>\r\n" + 
				"					<tr>\r\n" + 
				"						<td><input type=\"submit\" name=\"operator\" value=\"CE\" /></td>\r\n" + 
				"						<td><input type=\"submit\" name=\"operator\" value=\"C\" /></td>\r\n" + 
				"						<td><input type=\"submit\" name=\"operator\" value=\"BS\" /></td>\r\n" + 
				"						<td><input type=\"submit\" name=\"operator\" value=\"/\" /></td>\r\n" + 
				"					</tr>\r\n" + 
				"					<tr>\r\n" + 
				"						<td><input type=\"submit\" name=\"value\" value=\"7\" /></td>\r\n" + 
				"						<td><input type=\"submit\" name=\"value\" value=\"8\" /></td>\r\n" + 
				"						<td><input type=\"submit\" name=\"value\" value=\"9\" /></td>\r\n" + 
				"						<td><input type=\"submit\" name=\"operator\" value=\"*\" /></td>\r\n" + 
				"					</tr>\r\n" + 
				"					<tr>\r\n" + 
				"						<td><input type=\"submit\" name=\"value\" value=\"4\" /></td>\r\n" + 
				"						<td><input type=\"submit\" name=\"value\" value=\"5\" /></td>\r\n" + 
				"						<td><input type=\"submit\" name=\"value\" value=\"6\" /></td>\r\n" + 
				"						<td><input type=\"submit\" name=\"operator\" value=\"-\" /></td>\r\n" + 
				"					</tr>\r\n" + 
				"					<tr>\r\n" + 
				"						<td><input type=\"submit\" name=\"value\" value=\"1\" /></td>\r\n" + 
				"						<td><input type=\"submit\" name=\"value\" value=\"2\" /></td>\r\n" + 
				"						<td><input type=\"submit\" name=\"value\" value=\"3\" /></td>\r\n" + 
				"						<td><input type=\"submit\" name=\"operator\" value=\"+\" /></td>\r\n" + 
				"					</tr>\r\n" + 
				"					<tr>\r\n" + 
				"						<td></td>\r\n" + 
				"						<td><input type=\"submit\" name=\"value\" value=\"0\" /></td>\r\n" + 
				"						<td><input type=\"submit\" name=\"dot\" value=\".\" /></td>\r\n" + 
				"						<td><input type=\"submit\" name=\"operator\" value=\"=\" /></td>\r\n" + 
				"					</tr>\r\n" + 
				"				</table>\r\n" + 
				"\r\n" + 
				"				<div>\r\n" + 
				"					결과 : 0\r\n" + 
				"				</div>\r\n" + 
				"			</form>\r\n" + 
				"		</body>\r\n" + 
				"		</html>"
				);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();

		String value = req.getParameter("value");
		String operator = req.getParameter("operator");
		String dot = req.getParameter("dot");

		String exp = "";
		if(cookies != null) {
		//expression
			for (Cookie c : cookies) {
				if (c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}
			}
		}
		
		if(operator != null && operator.equals("=")) {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(operator != null && operator.equals("C")) {
			exp= "";
		}
		else {
			exp += (value == null)?"":value;
			exp += (operator == null)?"":operator;
			exp += (dot == null)?"":dot;
		}
		
		 
		Cookie expCookie = new Cookie("exp", exp);
		if(operator != null && operator.equals("C")) {
			expCookie.setMaxAge(0);
		}
		
		expCookie.setPath("/calculator");
		resp.addCookie(expCookie);
		resp.sendRedirect("calculator");

	}
}
