package com.MakeTeam.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Reject
 */
@WebServlet("/Reject")
public class Reject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String email=request.getParameter("iemail");
		PrintWriter out=response.getWriter();
		
		ServletContext sc1=getServletContext();
		String uemail=(String)sc1.getAttribute("Email");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con3=DriverManager.getConnection("jdbc:mysql://localhost:3306/maketeam","root","12345");
			PreparedStatement ps=con3.prepareStatement("delete from invite where ilemail=? and uemail=?");
			ps.setString(1, uemail);
			ps.setString(2, email);
			ps.executeUpdate();
		//s.close();
			out.print("<h4>you are reject invitation</h4>");
			out.print("<h4>thank you</h4>");
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
