package com.MakeTeam.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

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

import com.MakeTeam.Pojo.SignupPojo;

/**
 * Servlet implementation class AdminView
 */
@WebServlet("/AdminView")
public class AdminView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		Configuration con=new Configuration();
		con.configure("map.cfg.xml");
		SessionFactory sf=con.buildSessionFactory();
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		PrintWriter out=response.getWriter();
		
		Query q=s.createQuery("from SignupPojo");
		List l=q.list();
		Iterator itr=l.iterator();
		out.println("<h1>List Of Details</h1>");
        out.print("<table border='1' width='100%'");  
        out.print("<tr><th>Id</th><th>Name</th><th>Email</th><th>Mobile Number</th><th>Subject</th></tr>");
		while(itr.hasNext()) {
			SignupPojo sp=(SignupPojo)itr.next();
			out.print("<tr><td>"+sp.getId()+"</td><td>"+sp.getName()+"</td><td>"+sp.getEmail()+"</td><td>"+sp.getMobile()+"</td><td>"+sp.getSubject()+"</td></tr>");
			
			
			
		}
		
	}

}
