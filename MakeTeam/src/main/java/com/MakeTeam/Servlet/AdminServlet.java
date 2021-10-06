package com.MakeTeam.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String aemail=request.getParameter("aemail");
		String apassword=request.getParameter("apassword");
		PrintWriter out=response.getWriter();
		
		if(aemail.equals("admin@gmail.com")&&apassword.equals("admin123")) {
			out.print("<h4>admin login success</h4>");
			RequestDispatcher rd=request.getRequestDispatcher("adminoperation.html");
			rd.include(request, response);
			
		}else {
			out.print("<h4>admin login fails</h4>");
			RequestDispatcher rd=request.getRequestDispatcher("adminlogin.html");
			rd.include(request, response);
		}
	}

}
