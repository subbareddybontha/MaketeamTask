package com.MakeTeam.Pojo;

import javax.persistence.*;

@Entity
@Table(name="team_table")
public class TeamCreate {
	@Id
	@GeneratedValue
	private int id;
	private String teamadmin;
	private String teammember;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTeamadmin() {
		return teamadmin;
	}
	public void setTeamadmin(String teamadmin) {
		this.teamadmin = teamadmin;
	}
	public String getTeammember() {
		return teammember;
	}
	public void setTeammember(String teammember) {
		this.teammember = teammember;
	}
	
	

}
