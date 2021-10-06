package com.MakeTeam.Pojo;

import javax.persistence.*;

@Entity
@Table(name="team")
public class TeamPojo {
	@Id
	@GeneratedValue
	private int id;
	private String email;
	private String subject;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	

}
