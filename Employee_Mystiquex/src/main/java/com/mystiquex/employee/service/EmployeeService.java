package com.mystiquex.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mystiquex.employee.Exception.EmployeesNotFoundException;
import com.mystiquex.employee.employeeDAO.EmployeeDAO;
import com.mystiquex.employee.entity.Employee;
import com.mystiquex.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	public Employee addEmployees(Employee emp) {
	
			repository.save(emp);
			return emp;
	}

	public List<Employee> fetchAllEmployees() {

		List<Employee> employees = repository.findAll();
		
		if(employees.isEmpty())
			throw new EmployeesNotFoundException("No Employees are present");
		
		return employees; 
	}

	public Employee fetchEmployeesById(long id) {
		
		Employee emp = repository.findById(id).orElseThrow(() -> new EmployeesNotFoundException("Employee Id "+ id +" Not Present in DB"));
		
		return emp;
	}

	public Employee updateEmployee(long id, Employee emp) {

		Employee employee = repository.findById(id).orElseThrow(() -> new EmployeesNotFoundException("Employee Id "+ id +" not present in DB"));
		
		Employee obj = new Employee(id,emp.getName(),emp.getEmail(),emp.getDepartment(),emp.getDtofBirth());
		
		repository.save(obj);
		
		return obj;
	}

	public String deleteEmployee(long id) {
		
		Employee employee = repository.findById(id).orElseThrow(() ->new EmployeesNotFoundException("Employee Id "+ id +" not present"));
		
			repository.delete(employee);
			return "Employee Id "+id +" Deleted From DB";
	
	}

	public List<Employee> getEmployeeBySearch(String depart) {
		
		List<Employee> employees = repository.findByDepartment(depart);
		if(employees.isEmpty())
			throw new  EmployeesNotFoundException(depart+" Department No Employees are Present");
		return employees;
	}

	public Page<Employee> fetchAllEmployeesSorted(int pageNumber, int pageSize, String sortBy, String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
													: Sort.by(sortBy).descending();
		
		Pageable pagable = PageRequest.of(pageNumber,pageSize,sort);
		
		Page<Employee> employeeList =repository.findAll(pagable);
		
		if(employeeList.isEmpty())
			throw new EmployeesNotFoundException("No Employees are present");
		
		
		return employeeList;
	}




	

}
