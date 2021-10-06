package com.MakeTeam.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TeamMember
 */
@WebServlet("/TeamMember")
public class TeamMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		ServletContext sc1=getServletContext();
		String uemail=(String)sc1.getAttribute("Email");
		PrintWriter out=response.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con3=DriverManager.getConnection("jdbc:mysql://localhost:3306/maketeam","root","12345");
			PreparedStatement ps=con3.prepareStatement("select * from team_table where teammember=?");
			ps.setString(1, uemail);
			//ps.setString(2, email);
			//ps.executeUpdate();
			ResultSet rs=ps.executeQuery();
			out.println("<h1>You are Team Member of below Team Admins</h1>");
	        out.print("<table border='1' width='100%'");  
	        out.print("<tr><th>Team Admin</th></tr>");
			while(rs.next()) {
				
				out.print("<tr><td>"+rs.getString(2)+"</td></tr>");
				
				
				
			}
		//s.close();
			//out.print("<h4>you are reject invitation</h4>");
			//out.print("<h4>thank you</h4>");
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
