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

import com.MakeTeam.Pojo.SignupPojo;
import com.MakeTeam.Pojo.TeamCreate;

/**
 * Servlet implementation class MyTeam
 */
@WebServlet("/MyTeam")
public class MyTeam extends HttpServlet {
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
		out.println("<h1>List Of Details</h1>");
        out.print("<table border='1' width='100%'");  
        out.print("<tr><th>Name</th><th>Email</th><th>Mobile Number</th><th>Subject</th></tr>");
        Query q=s.createQuery("from SignupPojo where email=:ref");
        q.setParameter("ref", uemail);
        List l=q.list();
        Iterator itr=l.iterator();
        if(itr.hasNext()) {
        	SignupPojo sp2=(SignupPojo)itr.next();
        	out.print("<tr><td>"+sp2.getName()+"</td><td>"+sp2.getEmail()+"</td><td>"+sp2.getMobile()+"</td><td>"+sp2.getSubject()+"</td></tr>");
        	
        	Query q1=s.createQuery("from TeamCreate where teamadmin=:ref");
            q1.setParameter("ref", uemail);
            List l1=q1.list();
            Iterator itr1=l1.iterator();
            while(itr1.hasNext()) {
            	TeamCreate tc=(TeamCreate)itr1.next();
            	//System.out.println(tc.getTeammember());
            	Query q2=s.createQuery("from SignupPojo where email=:ref");
                q2.setParameter("ref", tc.getTeammember());
                List l2=q2.list();
                Iterator itr2=l2.iterator();
                while(itr2.hasNext()) {
                	SignupPojo sp=(SignupPojo)itr2.next();
                	out.print("<tr><td>"+sp.getName()+"</td><td>"+sp.getEmail()+"</td><td>"+sp.getMobile()+"</td><td>"+sp.getSubject()+"</td></tr>");
                	
                	
                }
            	
            }
            
            
        	
        }
		
		
	}

}
