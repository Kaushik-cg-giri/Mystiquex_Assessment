package com.mystiquex.employee.controller;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mystiquex.employee.employeeDAO.EmployeeDAO;
import com.mystiquex.employee.entity.Employee;
import com.mystiquex.employee.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@PostMapping("employees")
	public ResponseEntity<Employee> addEmployees(@RequestBody Employee emp) {
		return new ResponseEntity<Employee>( service.addEmployees(emp),HttpStatus.OK) ;
		
	}
	
	@GetMapping("employees")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		return ResponseEntity.ok(service.fetchAllEmployees());
	}
	
	@GetMapping("employees/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable long id) {
		return new ResponseEntity<Employee>(service.fetchEmployeesById(id),HttpStatus.OK);
	}
	
	@PutMapping("employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee emp) {
		return new ResponseEntity<Employee>(service.updateEmployee(id,emp),HttpStatus.OK);
	}
	
	@DeleteMapping("employees/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
		return new ResponseEntity<String>(service.deleteEmployee(id),HttpStatus.OK);
	}
	
	@GetMapping("employees/search")
	public ResponseEntity<List<Employee>> getEmployee(@RequestParam("dept") String depart ) {
		return new ResponseEntity<List<Employee>>( service.getEmployeeBySearch(depart),HttpStatus.OK);
	}
	
	@GetMapping("employeesSorted")
	public Page<Employee> getAllEmployeesSorted(@RequestParam(defaultValue = "1") int pageNumber,@RequestParam(defaultValue = "1") int pageSize,
												@RequestParam(defaultValue = "id") String sortBy,@RequestParam(defaultValue = "asc", required = false) String sortDir) {
		return service.fetchAllEmployeesSorted(pageNumber,pageSize,sortBy,sortDir);
	}
}
