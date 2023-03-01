package com.learningProjectMyNotes.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MyNotes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(length = 5000, nullable = false)
	private String context;
	private Date date;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = new Date();
	}

	public MyNotes(int id, String title, String context, Date date) {
		super();
		this.id = id;
		this.title = title;
		this.context = context;
		this.date = new Date();
	}
	

	public MyNotes() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "TODOList [id=" + id + ", title=" + title + ", context=" + context + ", date=" + date + "]";
	}
	
	
}
