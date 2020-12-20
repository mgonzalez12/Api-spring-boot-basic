package com.springboot.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.dao.IEmployeeDao;
import com.springboot.demo.entity.Employee;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private IEmployeeDao employeeDao;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployee(){
		return (List<Employee>) employeeDao.findAll();
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeDao.save(employee);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
		Employee employee = employeeDao.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee no exist with id: "+id));
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDeatils){
		Employee employee = employeeDao.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Employe not exist with id: "+id));
		
		employee.setFirstName(employeeDeatils.getFirstName());
		employee.setLastName(employeeDeatils.getLastName());
		employee.setEmailId(employeeDeatils.getEmailId());
		
		Employee updateEmployees = employeeDao.save(employee);
		return ResponseEntity.ok(updateEmployees);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = employeeDao.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id: "+id));
		
		employeeDao.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
