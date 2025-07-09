package com.mystiquex.employee.employeeDAO;

import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class EmployeeDAO {


	private long id;
	
	@NotBlank(message = "please provide employee name")
	private String name;
	
	@NotBlank(message = "please provide email")
	private String email;
	
	@NotBlank(message = "please provide department")
	private String department;
	
	@NotBlank(message = "please provide date of birth")
	private LocalDate dtofBirth;

	public EmployeeDAO() {
		super();
	}

	public EmployeeDAO(long id, @NotBlank(message = "please provide employee name") String name,
			@NotBlank(message = "please provide email") String email,
			@NotBlank(message = "please provide department") String department,
			@NotBlank(message = "please provide date of birth") LocalDate dtofBirth) {
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

	public LocalDate getDtofBirth() {
		return dtofBirth;
	}

	public void setDtofBirth(LocalDate dtofBirth) {
		this.dtofBirth = dtofBirth;
	}
	
	
}
