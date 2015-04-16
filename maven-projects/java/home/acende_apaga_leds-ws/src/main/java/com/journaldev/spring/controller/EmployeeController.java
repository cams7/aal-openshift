package com.journaldev.spring.controller;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.journaldev.jpa.data.Employee;
import com.journaldev.spring.service.EmployeeService;

/**
 * Handles requests for the Employee service.
 */
@RestController
@RequestMapping("/rest")
public class EmployeeController {

	private Logger logger = Logger
			.getLogger(EmployeeController.class.getName());

	@Autowired
	private EmployeeService service;

	@RequestMapping(value = EmpRestURIConstants.DUMMY_EMP, method = RequestMethod.GET)
	public @ResponseBody Employee getDummyEmployee() {
		logger.info("Start getDummyEmployee");
		Employee emp = new Employee();
		emp.setEmployeeName("Dummy");
		emp.setEmployeeSalary(4500);
		emp.setEmployeeHireDate(new Date());

		service.register(emp);

		return emp;
	}

	@RequestMapping(value = EmpRestURIConstants.GET_EMP, method = RequestMethod.GET)
	public @ResponseBody Employee getEmployee(@PathVariable("id") String empId) {
		logger.info("Start getEmployee. ID=" + empId);

		return service.findById(empId);
	}

	@RequestMapping(value = EmpRestURIConstants.GET_ALL_EMP, method = RequestMethod.GET)
	public @ResponseBody List<Employee> getAllEmployees() {
		return service.findByAll();
	}

	@RequestMapping(value = EmpRestURIConstants.CREATE_EMP, method = RequestMethod.POST)
	public @ResponseBody Employee createEmployee(@RequestBody Employee emp) {
		logger.info("Start createEmployee.");

		service.register(emp);

		return emp;
	}

	@RequestMapping(value = EmpRestURIConstants.DELETE_EMP, method = RequestMethod.PUT)
	public @ResponseBody Employee deleteEmployee(
			@PathVariable("id") String empId) {
		logger.info("Start deleteEmployee.");
		Employee emp = service.findById(empId);
		service.remove(empId);
		return emp;
	}

}