package com.MakeTeam.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import org.hibernate.query.Query;

import com.MakeTeam.Pojo.SignupPojo;
import com.MakeTeam.Pojo.StoreInvite;
import com.MakeTeam.Pojo.TeamCreate;
import com.MakeTeam.Pojo.TeamPojo;

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

/**
 * Servlet implementation class Accept
 */
@WebServlet("/Accept")
public class Accept extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String email=request.getParameter("iemail");
		PrintWriter out=response.getWriter();
		//System.out.println(email);
		ServletContext sc1=getServletContext();
		String uemail=(String)sc1.getAttribute("Email");
		//System.out.println(uemail);
		Configuration con=new Configuration();
		con.configure("map.cfg.xml");
		SessionFactory sf=con.buildSessionFactory();
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		
		TeamCreate tc=new TeamCreate();
		tc.setTeamadmin(email);
		tc.setTeammember(uemail);
		s.save(tc);
		t.commit();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con3=DriverManager.getConnection("jdbc:mysql://localhost:3306/maketeam","root","12345");
			PreparedStatement ps=con3.prepareStatement("delete from invite where ilemail=? and uemail=?");
			ps.setString(1, email);
			ps.setString(2, uemail);
			ps.executeUpdate();
		//s.close();
		out.print("you are added the team");
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
		/*Query q=s.createQuery("from SignupPojo where email=:ref or email=:ref1");
		q.setParameter("ref", email);
		q.setParameter("ref1", uemail);
		List l=q.list();
		Iterator itr=l.iterator();
		while(itr.hasNext()) {
			SignupPojo sp=(SignupPojo)itr.next();
			TeamPojo tp=new TeamPojo();
			tp.setEmail(email);
			tp.setSubject(sp.getSubject());
			s.save(tp);
			//t.commit();
			//s.close();
			StoreInvite si=new StoreInvite();
			si.setIlemail(email);
			s.delete(si);
			t.commit();
			s.close();
			
			out.print("You are added team");
			out.print("thank you");*/
			
			/*String subject1=sp.getSubject();
			System.out.println(subject1);
			
			if(subject1.equals("maths")) {
				
				
			}else if(subject1.equals("english")) {
				
			}else if(subject1.equals("physics")) {
				
			}else if(subject1.equals("computer")) {
				
			}else {
				
			}
			
			
			
		}*/
		
	}

}
