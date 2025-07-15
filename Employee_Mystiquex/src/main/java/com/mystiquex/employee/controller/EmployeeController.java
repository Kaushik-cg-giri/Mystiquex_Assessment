package com.mystiquex.employee.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mystiquex.employee.entity.Employee;
import com.mystiquex.employee.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {
	
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService service;
	
	@PostMapping("employees")
	public ResponseEntity<Employee> addEmployees(@RequestBody @Valid Employee emp) {
		logger.info("Request to add employees : {}",emp.getName());
		return new ResponseEntity<Employee>( service.addEmployees(emp),HttpStatus.CREATED) ;
		
	}
	
	@GetMapping("employees")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		logger.info("Fetch all employee details");
		return ResponseEntity.ok(service.fetchAllEmployees());
	}
	
	@GetMapping("employees/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable long id) {
		logger.info("Fetch employee with Id :{}",id);
		return new ResponseEntity<Employee>(service.fetchEmployeesById(id),HttpStatus.OK);
	}
	
	@PutMapping("employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody @Valid Employee emp) {
		logger.info("Update employee with Id :{}",id);
		return new ResponseEntity<Employee>(service.updateEmployee(id,emp),HttpStatus.OK);
	}
	
	@DeleteMapping("employees/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
		logger.info("Delete employee with Id :{}",id);
		return new ResponseEntity<String>(service.deleteEmployee(id),HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("employees/search")
	public ResponseEntity<List<Employee>> getEmployee(@RequestParam("dept") String depart ) {
		logger.info("Fetch employee with department :{}", depart);
		return new ResponseEntity<List<Employee>>( service.getEmployeeBySearch(depart),HttpStatus.OK);
	}
	
	@GetMapping("employeesSorted")
	public Page<Employee> getAllEmployeesSorted(@RequestParam(defaultValue = "1") int pageNumber,@RequestParam(defaultValue = "1") int pageSize,
												@RequestParam(defaultValue = "id") String sortBy,@RequestParam(defaultValue = "asc", required = false) String sortDir) {
		logger.info("Fetch employee in sorted order");
		return service.fetchAllEmployeesSorted(pageNumber,pageSize,sortBy,sortDir);
	}
}
