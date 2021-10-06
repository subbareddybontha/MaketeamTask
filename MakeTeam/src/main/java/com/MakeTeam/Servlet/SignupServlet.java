package com.MakeTeam.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.MakeTeam.Pojo.SignupPojo;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		Long mobile=Long.parseLong(request.getParameter("mobile"));
		String subject=request.getParameter("subject");
		
		PrintWriter out=response.getWriter();
		//out.print(name+email+password+mobile+subject);
		
		Configuration con=new Configuration();
		con.configure("map.cfg.xml");
		SessionFactory sf=con.buildSessionFactory();
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		Query q=s.createQuery("from SignupPojo where email=:ref");
		q.setParameter("ref", email);
		List l=q.list();
		Iterator itr=l.iterator();
		if(itr.hasNext()) {
			out.print("<h4>you are already login</h4>");
			RequestDispatcher rd=request.getRequestDispatcher("signup.html");
			rd.include(request, response);
		}else {
		
		SignupPojo sp=new SignupPojo();
		sp.setName(name);
		sp.setEmail(email);
		sp.setPassword(password);
		sp.setMobile(mobile);
		sp.setSubject(subject);
		s.save(sp);
		t.commit();
		s.close();
		sf.close();
		out.print("<h4>Data Added Successful</h4>");
		RequestDispatcher rd=request.getRequestDispatcher("login.html");
		rd.include(request, response);
		}
	}

}
