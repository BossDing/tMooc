package com.example.tmooc.entity;

import java.io.Serializable;
import java.util.Date;

public class Answer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int answer_id ;
	private int problem_id ;
	private int user_id ;
	private String answer_content ;
	private String answer_time ;
	public int getAnswer_id() {
		return answer_id;
	}
	public void setAnswer_id(int answer_id) {
		this.answer_id = answer_id;
	}
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
	public String getAnswer_content() {
		return answer_content;
	}
	public void setAnswer_content(String answer_content) {
		this.answer_content = answer_content;
	}
	public String getAnswer_time() {
		return answer_time;
	}
	public void setAnswer_time(String answer_time) {
		this.answer_time = answer_time;
	}
	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Answer(int answer_id, int problem_id, int user_id,
			String answer_content, String answer_time) {
		super();
		this.answer_id = answer_id;
		this.problem_id = problem_id;
		this.user_id = user_id;
		this.answer_content = answer_content;
		this.answer_time = answer_time;
	}
	public Answer(int problem_id, int user_id, String answer_content,
			String answer_time) {
		super();
		this.problem_id = problem_id;
		this.user_id = user_id;
		this.answer_content = answer_content;
		this.answer_time = answer_time;
	}
	@Override
	public String toString() {
		return "Answer [answer_id=" + answer_id + ", problem_id=" + problem_id
				+ ", user_id=" + user_id + ", answer_content=" + answer_content
				+ ", answer_time=" + answer_time + "]";
	}
	
	
}
