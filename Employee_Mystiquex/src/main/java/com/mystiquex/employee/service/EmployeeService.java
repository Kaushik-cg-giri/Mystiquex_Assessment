package com.mystiquex.employee.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mystiquex.employee.Exception.EmployeesNotFoundException;
import com.mystiquex.employee.entity.Employee;
import com.mystiquex.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepository repository;
	
	public Employee addEmployees(Employee emp) {
	
		logger.info("Saving employee: {}", emp.getName());
			repository.save(emp);
			return emp;
	}

	public List<Employee> fetchAllEmployees() {

		logger.debug("Retrieving all employees from DB");
		
		List<Employee> employees = repository.findAll();
		
		if(employees.isEmpty())
			throw new EmployeesNotFoundException("No Employees are present");
		
		return employees; 
	}

	public Employee fetchEmployeesById(long id) {
		
		logger.info("fetch employee with id :{}",id);
		Employee emp = repository.findById(id).orElseThrow(() -> new EmployeesNotFoundException("Employee Id "+ id +" Not Present in DB"));
		
		return emp;
	}

	public Employee updateEmployee(long id, Employee emp) {

		logger.debug("Update employee with id :{}",id );
		Employee employee = repository.findById(id).orElseThrow(() -> new EmployeesNotFoundException("Employee Id "+ id +" not present in DB"));
		
		//Employee obj = new Employee(id,emp.getName(),emp.getEmail(),emp.getDepartment(),emp.getDtofBirth());
		
		employee.setName(emp.getName());
		employee.setEmail(emp.getEmail());
		employee.setDepartment(emp.getDepartment());
		employee.setDtofBirth(emp.getDtofBirth());
		
		repository.save(employee);
		
		return employee;
	}

	public String deleteEmployee(long id) {
		
		logger.info("Delete employee with id : {}",id);
		Employee employee = repository.findById(id).orElseThrow(() ->new EmployeesNotFoundException("Employee Id "+ id +" not present"));
		
			repository.delete(employee);
			return "Employee Id "+id +" Deleted From DB";
	
	}

	public List<Employee> getEmployeeBySearch(String depart) {
		
		logger.info("Fetch employee with department :{}",depart);
		List<Employee> employees = repository.findByDepartment(depart);
		if(employees.isEmpty())
			throw new  EmployeesNotFoundException(depart+" Department No Employees are Present");
		return employees;
	}

	public Page<Employee> fetchAllEmployeesSorted(int pageNumber, int pageSize, String sortBy, String sortDir) {

		logger.debug("Fetch all employee in sorted order");
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
													: Sort.by(sortBy).descending();
		
		Pageable pagable = PageRequest.of(pageNumber,pageSize,sort);
		
		Page<Employee> employeeList =repository.findAll(pagable);
		
		if(employeeList.isEmpty())
			throw new EmployeesNotFoundException("No Employees are present");
		
		
		return employeeList;
	}




	

}
