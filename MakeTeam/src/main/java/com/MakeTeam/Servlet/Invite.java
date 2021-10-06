package com.MakeTeam.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
import com.MakeTeam.Pojo.StoreInvite;
import com.MakeTeam.Pojo.TeamPojo;
import com.MakeTeam.Pojo.TeamSubject;

/**
 * Servlet implementation class Invite
 */
@WebServlet("/Invite")
public class Invite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String iemail=request.getParameter("iemail");
		PrintWriter out=response.getWriter();
		//out.println(iemail);
		
		ServletContext sc = getServletContext();
		sc.setAttribute("Email1", iemail);
		
		ServletContext sc1=getServletContext();
		String uemail=(String)sc1.getAttribute("Email");

		Configuration con=new Configuration();
		con.configure("map.cfg.xml");
		SessionFactory sf=con.buildSessionFactory();
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
			Query q1=s.createQuery("from StoreInvite where ilemail=:ref and uemail=:ref1");
			q1.setParameter("ref", iemail);
			q1.setParameter("ref1",uemail);
			List l1=q1.list();
			Iterator itr1=l1.iterator();
			if(itr1.hasNext()) {
				out.print("<h4>you are already invite this person</h4>");
				RequestDispatcher rd=request.getRequestDispatcher("InviteList");
				rd.include(request, response);

				
			}else {
		
		StoreInvite si=new StoreInvite();
		si.setIlemail(iemail);
		si.setUemail(uemail);
		s.save(si);
		t.commit();
		}
		
		
		
		
		
		
		
		out.println("<h4>Your Invitation was successfully initiated</h4>");
		out.print("<h5>Wait some time for response</h4>");
		RequestDispatcher rd=request.getRequestDispatcher("useroptions.html");
		rd.include(request, response);

	}

}


/*Query q=s.createQuery("from SignupPojo where email=:ref");
q.setParameter("ref", uemail);
//q.setParameter("ref1", uemail);
List l=q.list();
Iterator itr=l.iterator();
if(itr.hasNext()) {
	SignupPojo sp=(SignupPojo)itr.next();
	Query q1=s.createQuery("from TeamPojo where subject=:ref");
	q1.setParameter("ref", sp.getSubject());
	//q.setParameter("ref1", uemail);
	List l1=q1.list();
	Iterator itr1=l1.iterator();
	if(itr1.hasNext()) {
		Configuration con1=new Configuration();
		con1.configure("map.cfg.xml");
		SessionFactory sf1=con1.buildSessionFactory();
		Session s1=sf1.openSession();
		Transaction t1=s1.beginTransaction();
		StoreInvite si=new StoreInvite();
		si.setIlemail(iemail);
		si.setUemail(uemail);
		s1.save(si);
		t1.commit();
		
	}else {
		Query q2=s.createQuery("from SignupPojo where email=:ref");
		q2.setParameter("ref",uemail);
		List l2=q2.list();
		Iterator itr2=l2.iterator();
		while(itr2.hasNext()) {
			SignupPojo sp2=(SignupPojo)itr2.next();
			String subject1=sp2.getSubject();
			System.out.println(subject1);
			
			if(subject1.equals("maths")) {
				
				
			}else if(subject1.equals("english")) {
				
			}else if(subject1.equals("physics")) {
				
			}else if(subject1.equals("computer")) {
				
			}else {
				
			}
		}
		
	
	TeamSubject ts=new TeamSubject();
	
	s.save(ts);
	t.commit();
	Configuration con1=new Configuration();
	con1.configure("map.cfg.xml");
	SessionFactory sf1=con1.buildSessionFactory();
	Session s1=sf1.openSession();
	Transaction t1=s1.beginTransaction();
	StoreInvite si=new StoreInvite();
	si.setIlemail(iemail);
	si.setUemail(uemail);
	s1.save(si);
	t1.commit();
	}
	
}*/
