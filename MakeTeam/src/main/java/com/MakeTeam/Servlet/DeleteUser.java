package com.MakeTeam.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String demail=request.getParameter("demail");
		
		Configuration con=new Configuration();
		con.configure("map.cfg.xml");
		SessionFactory sf=con.buildSessionFactory();
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		PrintWriter out=response.getWriter();
		
		Query q=s.createQuery("from SignupPojo");
		List l=q.list();
		Iterator itr=l.iterator();
		if(itr.hasNext()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con3=DriverManager.getConnection("jdbc:mysql://localhost:3306/maketeam","root","12345");
				PreparedStatement ps=con3.prepareStatement("delete from signup where email=?");
				ps.setString(1, demail);
				PreparedStatement ps1=con3.prepareStatement("delete from invite where email=? or uemail=?");
				ps1.setString(1, demail);
				ps1.setString(2, demail);
				PreparedStatement ps2=con3.prepareStatement("delete from team_table where teamadmin=? or teammember=?");
				ps2.setString(1, demail);
				ps2.setString(2, demail);
				ps.executeUpdate();
				ps1.executeUpdate();
				ps2.executeUpdate();
				
				out.print("user data was deleted");
				
			}catch(Exception e) {
				System.out.println(e);
			}
			
			
		}else {
			out.print("<h4>you enter email wrong</h4>");
			RequestDispatcher rd=request.getRequestDispatcher("demindelete.html");
			rd.include(request, response);
		}
	}

}
