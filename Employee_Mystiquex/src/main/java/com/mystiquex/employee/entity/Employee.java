package com.mystiquex.employee.entity;



import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "please provide employee name")
	private String name;
	
	@NotBlank(message = "please provide email")
	@Email(message = "Invalid email format")
	private String email;
	
	@NotBlank(message = "please provide department")
	private String department;
	
	
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	private Date dtofBirth;

	public Employee() {
		super();
	}

	

	public Employee(long id, @NotBlank(message = "please provide employee name") String name,
			@NotBlank(message = "please provide email") String email,
			@NotBlank(message = "please provide department") String department,
			@NotBlank(message = "please provide date of birth") Date dtofBirth) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.department = department;
		this.dtofBirth = dtofBirth;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Date getDtofBirth() {
		return dtofBirth;
	}

	public void setDtofBirth(Date dtofBirth) {
		this.dtofBirth = dtofBirth;
	}
	
	

}
