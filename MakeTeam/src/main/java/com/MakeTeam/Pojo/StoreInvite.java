package com.MakeTeam.Pojo;

import javax.persistence.*;

@Entity
@Table(name="invite")
public class StoreInvite {
	
	@Id @GeneratedValue
	private int id;
	private String uemail;
	private String ilemail;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getIlemail() {
		return ilemail;
	}
	public void setIlemail(String ilemail) {
		this.ilemail = ilemail;
	}
	
	
	

}
