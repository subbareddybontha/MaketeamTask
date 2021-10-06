package com.MakeTeam.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
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

import com.MakeTeam.Pojo.StoreInvite;

/**
 * Servlet implementation class Response
 */
@WebServlet("/Response")
public class Response extends HttpServlet {
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
		Configuration con=new Configuration();
		con.configure("map.cfg.xml");
		SessionFactory sf=con.buildSessionFactory();
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		
		Query q=s.createQuery("from StoreInvite where ilemail=:ref");
		q.setParameter("ref", uemail);
		List l=q.list();
		Iterator itr=l.iterator();
		out.println("<h1>List Of Details</h1>");
        out.print("<table border='1' width='100%'");  
        out.print("<tr><th>Email</th><th>Action1</th><th>Action2</th></tr>");
		while(itr.hasNext()) {
			StoreInvite si=(StoreInvite)itr.next();
			out.print("<tr><td>"+si.getUemail()+"</td><td><a href='Accept?iemail="+si.getUemail()+"'>Accept</a></td><td><a href='Reject?iemail="+si.getUemail()+"'>Reject</a></td></tr>");
			
		}
		
		
		
	}

}
