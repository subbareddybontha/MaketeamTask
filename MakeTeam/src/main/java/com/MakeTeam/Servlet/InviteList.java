package com.MakeTeam.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import org.hibernate.internal.build.AllowSysOut;
import org.hibernate.query.Query;

import com.MakeTeam.Pojo.SignupPojo;

/**
 * Servlet implementation class InviteList
 */
@WebServlet("/InviteList")
public class InviteList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		ServletContext sc1=getServletContext();
		String uemail=(String)sc1.getAttribute("Email");
		System.out.println(uemail);
		PrintWriter out=response.getWriter();
		
		Configuration con=new Configuration();
		con.configure("map.cfg.xml");
		SessionFactory sf=con.buildSessionFactory();
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		Query q1=s.createQuery("from SignupPojo where email=:ref1");
		q1.setParameter("ref1", uemail);
		List l1=q1.list();
		Iterator itr1=l1.iterator();
		
		 
		out.println("<h1>List Of Details</h1>");
        out.print("<table border='1' width='100%'");  
        out.print("<tr><th>Id</th><th>Name</th><th>Email</th><th>Mobile Number</th><th>Subject</th><th>Action</th></tr>");
		if(itr1.hasNext()) {
			//out.print("output");
			
			SignupPojo sp=(SignupPojo)itr1.next();
			try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con3=DriverManager.getConnection("jdbc:mysql://localhost:3306/maketeam","root","12345");
			PreparedStatement ps=con3.prepareStatement("select * from signup where subject not in ('"+sp.getSubject()+"')");
			//ps.setString(1, uemail);
			//ps.setString(2, pass);
			ResultSet rs=ps.executeQuery();
			//PrintWriter pw=response.getWriter();

			/*Query q=s.createQuery("from SignupPojo");
			//q.setParameter("ref", sp.getSubject());
			List l=q.list();
			Iterator itr=l.iterator();*/
			
			while(rs.next()) {
				//System.out.println(sp.getSubject());
				//SignupPojo sp1=(SignupPojo)itr.next();
				out.print("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getLong(3)+"</td><td>"+rs.getString(6)+"</td><td><a href='Invite?iemail="+rs.getString(2)+"'>Invite</a></td></tr>");
				
				
			}
			}catch(Exception e) {
				System.out.println(e);
			}
			
		
		}
		
			
	}

}
