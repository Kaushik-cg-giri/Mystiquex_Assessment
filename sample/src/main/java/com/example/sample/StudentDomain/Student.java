package com.example.sample.StudentDomain;

public class Student {
	
	private int id;
	private String name;
	private long mark;
	
	
	public Student(int id, String name, long mark) {
		super();
		this.id = id;
		this.name = name;
		this.mark = mark;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMark() {
		return mark;
	}
	public void setMark(long mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", mark=" + mark + "]";
	}
	
	

}
