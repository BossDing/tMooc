package com.example.tmooc.entity;

import java.io.Serializable;
import java.util.Date;

public class Problem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int problem_id ;
	private int user_id ;
	private String problem_content ;
	private String problem_time ;
	public int getProblem_id() {
		return problem_id;
	}
	public void setProblem_id(int problem_id) {
		this.problem_id = problem_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getProblem_content() {
		return problem_content;
	}
	public void setProblem_content(String problem_content) {
		this.problem_content = problem_content;
	}
	public String getProblem_time() {
		return problem_time;
	}
	public void setProblem_time(String problem_time) {
		this.problem_time = problem_time;
	}
	public Problem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Problem(int problem_id, int user_id, String problem_content,
			String problem_time) {
		super();
		this.problem_id = problem_id;
		this.user_id = user_id;
		this.problem_content = problem_content;
		this.problem_time = problem_time;
	}
	public Problem(int user_id, String problem_content, String problem_time) {
		super();
		this.user_id = user_id;
		this.problem_content = problem_content;
		this.problem_time = problem_time;
	}
	@Override
	public String toString() {
		return "Problem [problem_id=" + problem_id + ", user_id=" + user_id
				+ ", problem_content=" + problem_content + ", problem_time="
				+ problem_time + "]";
	}
	
	
}
