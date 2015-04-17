package br.com.cams7.sisbarc.aal.controler.rest;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cams7.sisbarc.aal.service.EmployeeService;
import br.com.cams7.webapp.controler.rest.BaseRest;

import com.journaldev.jpa.data.Employee;

/**
 * Handles requests for the Employee service.
 */
@RestController
@RequestMapping("/rest")
public class EmployeeController extends
		BaseRest<EmployeeService, Employee, String> {

	public EmployeeController() {
		super();
	}

	@RequestMapping(value = "/employee/dummy", method = RequestMethod.GET)
	public @ResponseBody Employee getDummyEmployee() {
		getLog().info("Start getDummyEmployee");

		Employee employee = new Employee();
		employee.setName("Dummy");
		employee.setSalary(4500);
		employee.setHireDate(new Date());

		getService().save(employee);
		return employee;
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public @ResponseBody Employee getEmployee(
			@PathVariable("id") String employeeId) {
		getLog().info("Start getEmployee(id = " + employeeId + ")");

		Employee employee = getService().findOne(employeeId);
		return employee;
	}

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getAllEmployees() {
		getLog().info("Start getAllEmployees");

		List<Employee> allEmployees = getService().findAll();
		return allEmployees;
	}

	@RequestMapping(value = "/employee/create", method = RequestMethod.POST)
	public @ResponseBody Employee createEmployee(@RequestBody Employee employee) {
		getLog().info("Start createEmployee");

		getService().save(employee);
		return employee;
	}

	@RequestMapping(value = "/employee/update/{id}", method = RequestMethod.PUT)
	public @ResponseBody Employee updateEmployee(
			@PathVariable("id") String employeeId,
			@RequestBody Employee employee) {
		getLog().info("Start updateEmployee");

		employee.setId(employeeId);
		employee = getService().update(employee);
		return employee;
	}

	@RequestMapping(value = "/employee/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Employee deleteEmployee(
			@PathVariable("id") String employeeId) {
		getLog().info("Start deleteEmployee");

		Employee employee = getService().remove(employeeId);
		return employee;
	}

}