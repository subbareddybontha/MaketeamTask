package com.MakeTeam.Pojo;

import javax.persistence.*;

@Entity
@Table(name="teamsubject")
public class TeamSubject {
	@Id
	@GeneratedValue
	private int id;
	private String maths;
	private String physics;
	private String english;
	private String computer;
	private String economice;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMaths() {
		return maths;
	}
	public void setMaths(String maths) {
		this.maths = maths;
	}
	public String getPhysics() {
		return physics;
	}
	public void setPhysics(String physics) {
		this.physics = physics;
	}
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public String getComputer() {
		return computer;
	}
	public void setComputer(String computer) {
		this.computer = computer;
	}
	public String getEconomice() {
		return economice;
	}
	public void setEconomice(String economice) {
		this.economice = economice;
	}
	
	

}

